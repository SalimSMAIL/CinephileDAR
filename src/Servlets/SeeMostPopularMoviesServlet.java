package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.SeeMostPopularMoviesService;

/**
 * The class <code>SeeMostPopularMoviesServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns all the popular movies by
 * calling the service <code>SeeMostPopularMoviesService</code>
 * 
 * TODO: complete !
 */

public class SeeMostPopularMoviesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	SeeMostPopularMoviesService most_popular_movies_service;

	@Override
	public void init() throws ServletException {
		most_popular_movies_service = new SeeMostPopularMoviesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(most_popular_movies_service.GetMostPopularMovies());

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
