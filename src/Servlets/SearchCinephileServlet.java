package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.json.JSONObject;

import Services.SearchCinephileService;

/**
 * The class <code>SearchCinephileServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the informations of a
 * cinephile we searched by calling the service
 * <code>SearchCinephileService</code>
 * 
 * TODO: Complete
 */

public class SearchCinephileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SearchCinephileService search_cinephile_service;

	@Override
	public void init() throws ServletException {
		search_cinephile_service = new SearchCinephileService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_search_result = new JSONObject();
		HttpSession session = request.getSession();
		try {
			if (!request.getParameter("query").equals("")) {
				json_search_result = search_cinephile_service.SearchCinephile(request.getParameter("query"),
						(int) session.getAttribute("id_cinephile"));
			} else {
				json_search_result = null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_search_result);

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			request.setAttribute("error", e); // remote debug
			response.getWriter().print(e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
