package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.SeeMostPopularTvShowsService;

/**
 * The class <code>SeeMostPopularTvShowsServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns all the popular TvShows
 * by calling the service <code>SeeMostPopularTvShowsService</code>
 * 
 * TODO: complete !
 */

public class SeeMostPopularTvShowsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	SeeMostPopularTvShowsService most_popular_tvshows_service;

	@Override
	public void init() throws ServletException {
		most_popular_tvshows_service = new SeeMostPopularTvShowsService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("application/json");
			resp.getWriter().print(most_popular_tvshows_service.GetMostPopularMovies());

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
