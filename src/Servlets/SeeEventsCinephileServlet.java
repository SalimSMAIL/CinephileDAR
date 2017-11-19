package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Services.SeeEventsCinephileService;

/**
 * The class <code>SeeEventsCinephileServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the events of the
 * cinephile by calling the service <code>SeeEventsCinephileService</code>
 * 
 * TODO: complete !
 */

public class SeeEventsCinephileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SeeEventsCinephileService see_events_cinephile_service;

	@Override
	public void init() throws ServletException {
		see_events_cinephile_service = new SeeEventsCinephileService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		JSONObject json_events = new JSONObject();
		HttpSession session = req.getSession();
		try {
			if (req.getParameter("id_other") != null && !req.getParameter("id_other").equals("")) {
				json_events = see_events_cinephile_service
						.DisplayEventsCinephiles(Integer.valueOf(req.getParameter("id_other").toString()));
			} else if (session.getAttribute("id_cinephile") != null
					&& !session.getAttribute("id_cinephile").equals("")) {
				json_events = see_events_cinephile_service
						.DisplayEventsCinephiles(Integer.valueOf(session.getAttribute("id_cinephile").toString()));
			} else {
				json_events = null;
			}
			resp.getWriter().print(json_events);

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
