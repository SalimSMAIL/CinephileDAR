package Services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;

/**
 * The class <code>SeeMoviesByGenreService</code> is a service called by the
 * servlet to display all the movies by genre
 * 
 * TODO: some extensions
 * 
 * 
 * <p>
 * Created on : October 13, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class SeeMoviesByGenreService {

	public JSONObject GetMoviesByGenre(int id_genre) throws Exception {
		StringBuffer response = ThemoviedbApiAccess.GetResponseFromAPI(ThemoviedbApiAccess.MoviesByGenre(id_genre));

		JSONObject json_movies_genre = null;
		try {
			JSONObject j = new JSONObject(response.toString());
			if (j.getJSONArray("results").length() > 0) {
				JSONArray popular_movies = j.getJSONArray("results");

				json_movies_genre = new JSONObject();
				json_movies_genre.put("movies_genre", popular_movies);
				json_movies_genre.put("response", 200);
				return json_movies_genre;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json_movies_genre;

	}

}
