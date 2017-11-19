package Services;

import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;

/**
 * The class <code>MultipleSearchService</code> is a service called by the servlet to
 * search movies, stars or tvshow
 * 
 * TODO: Complete
 * 
 * 
 * <p>
 * Created on : October 19, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class MultipleSearchService {

	public JSONObject GetResultsFromQuerySearch(String search_query) throws Exception {
		StringBuffer response = ThemoviedbApiAccess
				.GetResponseFromAPI(ThemoviedbApiAccess.GetMultipleSearchResult(search_query));

		JSONObject json_search_response = null;
		try {
			json_search_response = new JSONObject(response.toString());
			json_search_response.put("response", 200);

			return json_search_response;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json_search_response;
	}

}
