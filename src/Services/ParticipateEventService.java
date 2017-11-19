package Services;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import Beans.Evenement;
import DAO.CinephileDAO;
import DAO.EventDAO;

/**
 * The class <code>ParticipateEventService</code> is a service called by the
 * servlet for participation in events by the users
 * 
 * TODO: some extensions
 * 
 * 
 * <p>
 * Created on : October 07, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class ParticipateEventService {

	public JSONObject ParticipateEvent(int id_cinephile, int id_event) throws JSONException {
		CinephileDAO cinephile_dao = new CinephileDAO();
		Cinephile cinephile = cinephile_dao.GetCinephileById(id_cinephile);
		EventDAO event_dao = new EventDAO();
		Evenement event = event_dao.GetEventById(id_event);

		ArrayList<Cinephile> participants = event_dao.GetEventParticipants(id_event);

		if (event != null && cinephile != null) {
			JSONObject event_json = new JSONObject();
			if (event_dao.IsHeParticipating(id_cinephile, id_event)) {
				event_json.put("response", "deja inscrit a cet event");
			} else if ((participants.size() + 1) > event.getLimit()) {

				event_json.put("response", "plus de place");
			} else {
				event_dao.ParticipateEvent(id_cinephile, id_event);
				event_json.put("response", 200);
			}

			return event_json;
		} else
			return null;
	}

}
