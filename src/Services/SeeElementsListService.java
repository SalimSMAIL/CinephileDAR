package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;
import Beans.ListCinephile;
import Beans.TypeElement;
import Beans.Element;
import DAO.ListDAO;

/**
 * The class <code>SeeElementsListService</code> is a service called by the
 * servlet to display the elements of a specific list
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

public class SeeElementsListService {

	@SuppressWarnings("finally")
	public JSONObject DisplayListElements(int id_list) throws Exception {

		ListDAO list_dao = new ListDAO();

		ArrayList<Element> elements = list_dao.GetListElements(id_list);
		ListCinephile list = list_dao.GetListById(id_list);
		StringBuffer response = null;
		JSONObject json_elements = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			for (Element element : elements) {

				if (element.getElement_type() == TypeElement.MOVIE) {
					response = ThemoviedbApiAccess
							.GetResponseFromAPI(ThemoviedbApiAccess.AllMovieDetailsURL(element.getElement_id()));
				} else if (element.getElement_type() == TypeElement.TVSHOW) {
					response = ThemoviedbApiAccess
							.GetResponseFromAPI(ThemoviedbApiAccess.AllTvShowDetailsURL(element.getElement_id()));
				} else if (element.getElement_type() == TypeElement.STAR) {
					response = ThemoviedbApiAccess
							.GetResponseFromAPI(ThemoviedbApiAccess.AllPersonDetailsURL(element.getElement_id()));
				}

				JSONObject element_JSON = new JSONObject(response.toString());
				element_JSON.put("id", element.getElement_id());

				array.put(element_JSON);
			}

			json_elements = new JSONObject().put("elements", array);
			json_elements.put("list", list.getList_name());
			json_elements.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return json_elements;
		}
	}

}
