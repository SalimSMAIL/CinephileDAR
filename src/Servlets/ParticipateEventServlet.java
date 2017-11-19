package Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Services.AddEventService;
import Services.ParticipateEventService;

/**
 * The class <code>ParticipateEventServlet</code> extends
 * <code>HttpServlet</code> is a servlet which permit to a user to participate
 * to an evenement by calling the service <code>ParticipateEventService</code>
 * 
 * TODO: some extensions
 * 
 */

public class ParticipateEventServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ParticipateEventService participate_event_service;

	@Override
	public void init() throws ServletException {
		participate_event_service = new ParticipateEventService();
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

			if (req.getParameter("id_event") != null) {

				JSONObject event_json = this.participate_event_service.ParticipateEvent(Integer.valueOf(id_cinephile),
						Integer.valueOf(req.getParameter("id_event")));
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