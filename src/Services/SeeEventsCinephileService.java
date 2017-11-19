package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Beans.Evenement;
import DAO.EventDAO;

/**
 * The class <code>SeeEventsCinephileService</code> is a service called by the
 * servlet to display the events of a user
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

public class SeeEventsCinephileService {

	@SuppressWarnings("finally")
	public JSONObject DisplayEventsCinephiles(int id_cinephile) throws Exception {

		EventDAO event_dao = new EventDAO();

		ArrayList<Evenement> events_organized = event_dao.GetEventsOrganizedByCinephile(id_cinephile);
		ArrayList<Evenement> events_joined = event_dao.GetEventsJoinedByCinephile(id_cinephile);

		JSONObject json_events_cinephile = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray array2 = new JSONArray();
		try {
			for (Evenement event : events_organized) {
				JSONObject event_JSON = new JSONObject();
				event_JSON.put("id", event.getEvent_id());
				event_JSON.put("title", event.getTitle());
				event_JSON.put("description", event.getDescription());
				event_JSON.put("place", event.getPlace());
				event_JSON.put("date", event.getDate());
				event_JSON.put("limit", event.getLimit());
				event_JSON.put("affiche", event.getSeance().getAffiche());
				event_JSON.put("cinephile", event.getCinephile().getUsername());
				array.put(event_JSON);
			}

			for (Evenement event : events_joined) {
				JSONObject event_JSON = new JSONObject();
				event_JSON.put("id", event.getEvent_id());
				event_JSON.put("title", event.getTitle());
				event_JSON.put("description", event.getDescription());
				event_JSON.put("place", event.getPlace());
				event_JSON.put("date", event.getDate());
				event_JSON.put("limit", event.getLimit());
				event_JSON.put("affiche", event.getSeance().getAffiche());
				event_JSON.put("cinephile", event.getCinephile().getUsername());
				array2.put(event_JSON);
			}

			json_events_cinephile = new JSONObject().put("events_organized", array);
			json_events_cinephile.put("events_joined", array2);
			json_events_cinephile.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return json_events_cinephile;
		}
	}

}
