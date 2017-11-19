package Services;

import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;

/**
 * The class <code>DisplayPersonDetailsService</code> is a service called by the
 * servlet to display the details of a star
 * 
 * TODO: See extensions in the report
 * 
 * 
 * <p>
 * Created on : October 06, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class DisplayPersonDetailsService {

	public JSONObject DisplayPersonDetails(int id_person, String name_person) throws Exception {
		StringBuffer response = ThemoviedbApiAccess
				.GetResponseFromAPI(ThemoviedbApiAccess.AllPersonDetailsURL(id_person));
		StringBuffer knownfor = ThemoviedbApiAccess
				.GetResponseFromAPI(ThemoviedbApiAccess.AllPersonKnownForURL(name_person));

		JSONObject json_person_details = null;
		try {
			json_person_details = new JSONObject(response.toString());
			json_person_details.put("knownfor", new JSONObject(knownfor.toString()).getJSONArray("results"));
			json_person_details.put("response", 200);

			return json_person_details;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json_person_details;
	}

}
