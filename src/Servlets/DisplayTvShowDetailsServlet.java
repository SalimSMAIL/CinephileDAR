package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Services.DisplayTvshowDetailsService;

/**
 * The class <code>DisplayTvShowDetailsServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the details of a TvShow
 * by calling the service <code>DisplayTvshowDetailsService</code>
 * 
 * TODO: some extensions
 * 
 */

public class DisplayTvShowDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DisplayTvshowDetailsService display_tvshow_details_service;

	@Override
	public void init() throws ServletException {
		display_tvshow_details_service = new DisplayTvshowDetailsService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_details_tvshow = new JSONObject();

		try {
			if (!request.getParameter("id_tvshow").equals("")) {
				json_details_tvshow = display_tvshow_details_service
						.DisplayTvShowDetails(Integer.valueOf(request.getParameter("id_tvshow")));
			} else {
				json_details_tvshow = null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_details_tvshow);

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
