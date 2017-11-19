package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.SeeTopRatedMoviesService;

/**
 * The class <code>SeeTopRatedMoviesServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the notation of the
 * movies by calling the service <code>SeeTopRatedMoviesService</code>
 * 
 * TODO: complete !
 */

public class SeeTopRatedMoviesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	SeeTopRatedMoviesService top_rated_movies_service;

	@Override
	public void init() throws ServletException {
		top_rated_movies_service = new SeeTopRatedMoviesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(top_rated_movies_service.GetTopRatedMovies());

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
