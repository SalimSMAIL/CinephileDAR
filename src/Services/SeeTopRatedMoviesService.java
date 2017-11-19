package Services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;

/**
 * The class <code>SeeTopRatedMoviesService</code> is a service called by the
 * servlet to display the notation of each movie
 * 
 * TODO: some extensions
 * 
 * 
 * <p>
 * Created on : October 11, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class SeeTopRatedMoviesService {

	public JSONObject GetTopRatedMovies() throws Exception {
		StringBuffer response = ThemoviedbApiAccess.GetResponseFromAPI(ThemoviedbApiAccess.TOP_RATED_FILMS);

		JSONObject json_top_rated_movies = null;
		try {
			JSONObject j = new JSONObject(response.toString());
			if (j.getJSONArray("results").length() > 0) {
				JSONArray popular_movies = j.getJSONArray("results");

				json_top_rated_movies = new JSONObject();
				json_top_rated_movies.put("top_movies", popular_movies);
				json_top_rated_movies.put("response", 200);
				return json_top_rated_movies;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json_top_rated_movies;

	}

}
