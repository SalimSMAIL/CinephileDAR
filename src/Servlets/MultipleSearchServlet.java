package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Services.MultipleSearchService;

/**
 * The class <code>MultipleSearchServlet</code> extends <code>HttpServlet</code>
 * is a servlet which returns the results of searching a movie, star , by
 * calling the service <code>MultipleSearchService</code>
 * 
 * TODO: some extensions
 * 
 */

public class MultipleSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MultipleSearchService multiple_search_service;

	@Override
	public void init() throws ServletException {
		multiple_search_service = new MultipleSearchService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_search_result = new JSONObject();

		try {
			if (!request.getParameter("query").equals("")) {
				json_search_result = multiple_search_service.GetResultsFromQuerySearch(request.getParameter("query"));
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
		doGet(request, response);
	}

}
