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
import Services.AddListService;

/**
 * The class <code>AddListServlet</code> extends <code>HttpServlet</code> is a
 * servlet which add a new list for a user by calling the service
 * <code>AddListService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AddListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddListService add_list_service;

	@Override
	public void init() throws ServletException {
		add_list_service = new AddListService();
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

			String name = req.getParameter("name");

			if (map.containsKey("name")) {
				JSONObject list_json = this.add_list_service.AddList(name, Integer.valueOf(id_cinephile));
				resp.getWriter().print(list_json);
			} else
				throw new Exception("Error");
		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}

	}

}