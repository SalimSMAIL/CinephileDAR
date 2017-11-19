package Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Beans.TypeElement;
import Services.AddCommentService;
import Services.AddEventService;

/**
 * The class <code>AddEventServlet</code> extends <code>HttpServlet</code> is a
 * servlet which add a new event by calling the service
 * <code>AddEventService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AddEventServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddEventService add_event_service;

	@Override
	public void init() throws ServletException {
		add_event_service = new AddEventService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			HttpSession session = req.getSession();
			int id_cinephile = (int) session.getAttribute("id_cinephile");

			Map<String, String[]> map = req.getParameterMap();

			String title = req.getParameter("title");
			String description = req.getParameter("description");
			String place = req.getParameter("place");
			String date = req.getParameter("date");
			String limit = req.getParameter("limit");

			String[] parts = date.split(" /");
			String id_seance = parts[0]; // 004
			String movie = parts[1]; // 034556
			String date_seance = parts[2];

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			DateFormat formatter;
			Date datetime;
			formatter = new SimpleDateFormat("dd-MMM-yy HH:mm");
			datetime = sdf.parse(date_seance);

			if (map.containsKey("title") && !title.equals("") && map.containsKey("description")
					&& !description.equals("") && map.containsKey("place") && !place.equals("")
					&& map.containsKey("date") && !date.equals("") && map.containsKey("limit") && !limit.equals("")) {
				JSONObject event_json = this.add_event_service.AddEvent(title, description, place, datetime,
						Integer.valueOf(limit), Integer.valueOf(id_cinephile), Integer.valueOf(id_seance));
				resp.getWriter().print(event_json);
			} else
				throw new Exception("Error");
		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}

	}

}