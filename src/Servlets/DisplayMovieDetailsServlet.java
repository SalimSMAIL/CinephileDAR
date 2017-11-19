package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Services.DisplayMovieDetailsService;

/**
 * The class <code>DisplayMovieDetailsServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the details of a movie
 * by calling the service <code>DisplayMovieDetailsService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class DisplayMovieDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DisplayMovieDetailsService display_movie_details_service;

	@Override
	public void init() throws ServletException {
		display_movie_details_service = new DisplayMovieDetailsService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_details_movie = new JSONObject();

		try {

			if (!request.getParameter("id_movie").equals("")) {
				json_details_movie = display_movie_details_service
						.DisplayMovieDetails(Integer.valueOf(request.getParameter("id_movie")));
			} else {
				json_details_movie = null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_details_movie);

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
