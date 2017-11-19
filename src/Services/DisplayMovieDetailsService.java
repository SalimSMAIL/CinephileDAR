package Services;

import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;

/**
 * The class <code>DisplayMovieDetailsService</code> is a service called by the
 * servlet to display the details of a movie
 * 
 * TODO: See extensions in the report
 * 
 * 
 * <p>
 * Created on : October 05, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class DisplayMovieDetailsService {

	public JSONObject DisplayMovieDetails(int id_movie) throws Exception {
		StringBuffer response = ThemoviedbApiAccess
				.GetResponseFromAPI(ThemoviedbApiAccess.AllMovieDetailsURL(id_movie));
		StringBuffer recommendations = ThemoviedbApiAccess
				.GetResponseFromAPI(ThemoviedbApiAccess.MovieRecommendationsURl(id_movie));
		StringBuffer trailers = ThemoviedbApiAccess.GetResponseFromAPI(ThemoviedbApiAccess.GetMovieTrailer(id_movie));

		JSONObject json_movie_details = null;
		try {
			json_movie_details = new JSONObject(response.toString());
			json_movie_details.put("recommendations",
					new JSONObject(recommendations.toString()).getJSONArray("results"));
			json_movie_details.put("trailers", new JSONObject(trailers.toString()).getJSONArray("results"));
			json_movie_details.put("response", 200);

			return json_movie_details;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json_movie_details;
	}

}
