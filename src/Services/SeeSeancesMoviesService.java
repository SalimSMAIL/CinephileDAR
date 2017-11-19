package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Beans.Seance;
import DAO.SeanceDAO;

/**
 * The class <code>SeeSeancesMoviesService</code> is a service called by the
 * servlet to display all seances of watching a movie
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

public class SeeSeancesMoviesService {

	@SuppressWarnings("finally")
	public JSONObject DisplaySeances() throws Exception {

		SeanceDAO seance_dao = new SeanceDAO();
		ArrayList<Seance> seances_list = new ArrayList<Seance>();

		JSONObject json_seances = new JSONObject();
		JSONArray array = new JSONArray();

		try {
			seances_list = seance_dao.GetSeances();
			for (Seance seance : seances_list) {
				JSONObject seance_json = new JSONObject();
				seance_json.put("id_seance", seance.getId_seance());
				seance_json.put("movie", seance.getMovie());
				seance_json.put("affiche", seance.getAffiche());
				seance_json.put("place", seance.getPlace());
				seance_json.put("date", seance.getDatetime_seance());
				array.put(seance_json);
			}

			json_seances = new JSONObject().put("seances", array);
			json_seances.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return json_seances;
		}
	}

}
