package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Services.SeeMoviesByGenreService;

/**
 * The class <code>SeeMoviesByGenreServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns all the movies by genres by
 * calling the service <code>SeeMoviesByGenreService</code>
 * 
 * TODO: complete !
 */

public class SeeMoviesByGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SeeMoviesByGenreService see_movies_by_genre_servie;

	@Override
	public void init() throws ServletException {
		see_movies_by_genre_servie = new SeeMoviesByGenreService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_movies_by_genre = new JSONObject();

		try {

			if (!request.getParameter("id_genre").equals("")) {
				json_movies_by_genre = see_movies_by_genre_servie
						.GetMoviesByGenre(Integer.valueOf(request.getParameter("id_genre")));
			} else {
				json_movies_by_genre = null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_movies_by_genre);

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
