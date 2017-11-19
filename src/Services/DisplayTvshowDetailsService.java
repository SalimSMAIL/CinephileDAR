package Services;

import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;

/**
 * The class <code>DisplayTvshowDetailsService</code> is a service called by the
 * servlet to display the details of a Tvshow
 * 
 * TODO: See extensions in the report
 * 
 * 
 * <p>
 * Created on : October 19, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class DisplayTvshowDetailsService {

	public JSONObject DisplayTvShowDetails(int id_movie) throws Exception {
		StringBuffer response = ThemoviedbApiAccess
				.GetResponseFromAPI(ThemoviedbApiAccess.AllTvShowDetailsURL(id_movie));
		StringBuffer recommendations = ThemoviedbApiAccess
				.GetResponseFromAPI(ThemoviedbApiAccess.TvShowRecommendationsURl(id_movie));
		StringBuffer trailers = ThemoviedbApiAccess.GetResponseFromAPI(ThemoviedbApiAccess.GetTvShowTrailer(id_movie));

		JSONObject json_tvshow_details = null;
		try {
			json_tvshow_details = new JSONObject(response.toString());
			json_tvshow_details.put("recommendations",
					new JSONObject(recommendations.toString()).getJSONArray("results"));
			json_tvshow_details.put("trailers", new JSONObject(trailers.toString()).getJSONArray("results"));
			json_tvshow_details.put("response", 200);

			return json_tvshow_details;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json_tvshow_details;
	}

}
