package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;
import Beans.Cinephile;
import Beans.Comment;
import DAO.CinephileDAO;

/**
 * The class <code>SearchCinephileService</code> is a service called by the
 * servlet to search a specific cinephile
 * 
 * TODO: some extensions
 * 
 * 
 * <p>
 * Created on : October 15, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class SearchCinephileService {

	@SuppressWarnings("finally")
	public JSONObject SearchCinephile(String search_query, int id_cinephile) throws Exception {

		CinephileDAO cinephile_dao = new CinephileDAO();
		ArrayList<Cinephile> cinephiles_list = cinephile_dao.GetAllCinephiles();
		ArrayList<Cinephile> cinephiles_search_results = new ArrayList<Cinephile>();

		for (int i = 0; i < cinephiles_list.size(); i++) {
			if (cinephiles_list.get(i).getUsername().contains(search_query)
					|| cinephiles_list.get(i).getEmail().contains(search_query)
					|| cinephiles_list.get(i).getLastname().contains(search_query)
					|| cinephiles_list.get(i).getFirstname().contains(search_query))

				if (cinephiles_list.get(i).getId() != id_cinephile)
					cinephiles_search_results.add(cinephiles_list.get(i));
		}

		JSONObject json_cinephile_results = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			for (Cinephile cinephile : cinephiles_search_results) {
				JSONObject cinephileJSON = new JSONObject();
				cinephileJSON.put("id", cinephile.getId());
				cinephileJSON.put("firstname", cinephile.getFirstname());
				cinephileJSON.put("lastname", cinephile.getLastname());
				cinephileJSON.put("username", cinephile.getUsername());
				cinephileJSON.put("description", cinephile.getDescription());
				cinephileJSON.put("gender", cinephile.getGender());
				cinephileJSON.put("address", cinephile.getAdress());
				array.put(cinephileJSON);
			}

			json_cinephile_results = new JSONObject().put("cinephiles", array);
			json_cinephile_results.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return json_cinephile_results;
		}
	}

}
