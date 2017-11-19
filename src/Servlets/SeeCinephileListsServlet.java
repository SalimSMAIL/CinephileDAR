package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Services.SeeCinephileListsService;

/**
 * The class <code>SeeCinephileListsServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns all the lists of the
 * cinephile by calling the service <code>SeeCinephileListsService</code>
 * 
 * TODO: complete
 */

public class SeeCinephileListsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SeeCinephileListsService see_cinephile_lists_service;

	@Override
	public void init() throws ServletException {
		see_cinephile_lists_service = new SeeCinephileListsService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		JSONObject json_lists = new JSONObject();
		HttpSession session = req.getSession();
		try {
			if (req.getParameter("id_other") != null && !req.getParameter("id_other").equals("")) {
				json_lists = see_cinephile_lists_service
						.DisplayCinephileLists(Integer.valueOf(req.getParameter("id_other").toString()));
			}

			else
				json_lists = see_cinephile_lists_service
						.DisplayCinephileLists(Integer.valueOf(session.getAttribute("id_cinephile").toString()));

			resp.getWriter().print(json_lists);
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
