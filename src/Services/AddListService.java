package Services;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import Beans.Evenement;
import Beans.ListCinephile;
import Beans.Seance;
import DAO.CinephileDAO;
import DAO.EventDAO;
import DAO.ListDAO;
import DAO.SeanceDAO;

/**
 * The class <code>AddListService</code> is a service called by the servlet to
 * add an new list
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

public class AddListService {

	public JSONObject AddList(String name_list, int created_by) throws JSONException {
		ListDAO list_dao = new ListDAO();
		CinephileDAO cinephile_dao = new CinephileDAO();

		Cinephile cinephile = cinephile_dao.GetCinephileById(created_by);

		ListCinephile list_cinephile = new ListCinephile(name_list, cinephile);

		list_dao.AddList(list_cinephile);

		if (list_cinephile != null) {
			JSONObject list_json = new JSONObject();
			list_json.put("id_list", list_cinephile.getList_id());
			list_json.put("name", list_cinephile.getList_name());
			list_json.put("cinephile", list_cinephile.getCinephile().getUsername());
			list_json.put("response", 200);
			return list_json;
		} else
			return null;

	}

}
