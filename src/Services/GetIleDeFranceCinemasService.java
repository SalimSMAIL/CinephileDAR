package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Beans.Seance;
import DAO.SeanceDAO;

/**
 * The class <code>GetIleDeFranceCinemasService</code> is a service called by
 * the servlet to display maps of the cinemas in Paris
 * 
 * TODO: See extensions in the report
 * 
 * 
 * <p>
 * Created on : October 09, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class GetIleDeFranceCinemasService {

	public JSONObject GetCinemas() throws Exception {

		SeanceDAO seance_dao = new SeanceDAO();
		ArrayList<Seance> seances = seance_dao.GetSeances();

		JSONArray array = new JSONArray();
		JSONObject json_cinemas = null;
		try {
			for (Seance seance : seances) {
				JSONObject seance_json = new JSONObject();
				seance_json.put("id", seance.getId_seance());
				seance_json.put("movie", seance.getMovie());
				seance_json.put("affiche", seance.getAffiche());
				seance_json.put("place", seance.getPlace());
				seance_json.put("date", seance.getDatetime_seance());

				array.put(seance_json);
			}
			json_cinemas = new JSONObject();
			json_cinemas.put("seances", array);
			json_cinemas.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json_cinemas;

	}

}
