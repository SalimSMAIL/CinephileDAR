package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Beans.ListCinephile;
import DAO.ListDAO;

/**
 * The class <code>SeeCinephileListsService</code> is a service called by the
 * servlet to display the lists of a cinephile
 * 
 * TODO: some extensions
 * 
 * 
 * <p>
 * Created on : October 19, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class SeeCinephileListsService {

	@SuppressWarnings("finally")
	public JSONObject DisplayCinephileLists(int id_cinephile) throws Exception {

		ListDAO list_dao = new ListDAO();

		ArrayList<ListCinephile> cinephile_lists = list_dao.GetCinephileLists(id_cinephile);

		JSONObject json_lists_cinephile = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			for (ListCinephile list : cinephile_lists) {

				JSONObject list_JSON = new JSONObject();
				list_JSON.put("id", list.getList_id());
				list_JSON.put("name", list.getList_name());
				list_JSON.put("cinephile", list.getCinephile().getUsername());
				array.put(list_JSON);
			}

			json_lists_cinephile = new JSONObject().put("lists_cinephile", array);
			json_lists_cinephile.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return json_lists_cinephile;
		}
	}

}
