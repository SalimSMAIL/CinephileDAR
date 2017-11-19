package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import Beans.Evenement;
import Beans.Seance;
import DAO.EventDAO;
import DAO.SeanceDAO;

/**
 * The class <code>SeeEventsService</code> is a service called by the servlet to
 * display all the existing events
 * 
 * TODO: some extensions
 * 
 * 
 * <p>
 * Created on : October 03, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class SeeEventsService {

	@SuppressWarnings("finally")
	public JSONObject DisplayEvents() throws Exception {

		EventDAO event_dao = new EventDAO();
		ArrayList<Evenement> events_list = new ArrayList<Evenement>();

		JSONObject json_events = new JSONObject();
		JSONArray array = new JSONArray();

		try {
			events_list = event_dao.GetAllEvents();
			for (Evenement event : events_list) {
				JSONObject event_json = new JSONObject();
				event_json.put("id_event", event.getEvent_id());
				event_json.put("title", event.getTitle());
				event_json.put("description", event.getDescription());
				event_json.put("place", event.getPlace());
				event_json.put("date", event.getDate());
				event_json.put("limit", event.getLimit());
				event_json.put("cinephile", event.getCinephile().getUsername());
				event_json.put("affiche", event.getSeance().getAffiche());
				array.put(event_json);
			}

			json_events = new JSONObject().put("events", array);
			json_events.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return json_events;
		}
	}

}
