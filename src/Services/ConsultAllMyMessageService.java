package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import Beans.Comment;
import Beans.Message;
import DAO.MessageDAO;

/**
 * The class <code>ConsultAllMyMessageService</code> is a service called by the
 * servlet to consult all the message of a user
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

public class ConsultAllMyMessageService {

	/**
	 * A service to consult all message sent by a user
	 * 
	 * @param id_cinephile
	 * 
	 * @return json_message_sent
	 **/

	@SuppressWarnings("finally")
	public JSONObject DisplayMessagesCinephiles(int id_cinephile) throws Exception {

		MessageDAO message_dao = new MessageDAO();
		ArrayList<Message> messages_sent = message_dao.GetListOfMessageSent(id_cinephile);
		ArrayList<Message> messages_received = message_dao.GetListOfMessageReceived(id_cinephile);
		ArrayList<Cinephile> cinephiles = new ArrayList<Cinephile>();

		for (int i = 0; i < messages_sent.size(); i++) {
			if (!search(cinephiles, messages_sent.get(i).getCinephile_receiver().getId()))

				if (messages_sent.get(i).getCinephile_receiver().getId() != id_cinephile)
					cinephiles.add(messages_sent.get(i).getCinephile_receiver());

		}

		for (int i = 0; i < messages_received.size(); i++) {
			if (!search(cinephiles, messages_received.get(i).getCinephile_sender().getId()))

				if (messages_received.get(i).getCinephile_sender().getId() != id_cinephile)
					cinephiles.add(messages_received.get(i).getCinephile_sender());
		}

		JSONObject json_cinephiles = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			for (Cinephile cinephile : cinephiles) {
				JSONObject cinephiles_JSON = new JSONObject();
				cinephiles_JSON.put("id", cinephile.getId());
				cinephiles_JSON.put("username", cinephile.getUsername());

				array.put(cinephiles_JSON);
			}

			json_cinephiles = new JSONObject().put("cinephiles", array);
			json_cinephiles.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return json_cinephiles;
		}
	}

	public boolean search(ArrayList<Cinephile> cinephiles, int id) {
		boolean found = false;
		int i = 0;
		while (!found && i < cinephiles.size()) {
			if (cinephiles.get(i).getId() == id) {
				found = true;
				i++;
			} else
				i++;
		}
		return found;
	}

}
