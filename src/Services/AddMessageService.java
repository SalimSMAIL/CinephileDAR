package Services;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import Beans.Comment;
import Beans.Element;
import Beans.Message;
import DAO.CinephileDAO;
import DAO.MessageDAO;

/**
 * The class <code>AddMessageService</code> is a service called by the servlet
 * to add an new message
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

public class AddMessageService {

	public JSONObject AddMessage(String content, Date datetime, int id_cinephile_sender, int id_cinephile_receiver)
			throws JSONException {
		MessageDAO message_dao = new MessageDAO();
		CinephileDAO cinephile_dao = new CinephileDAO();

		Cinephile cinephile_sender = cinephile_dao.GetCinephileById(id_cinephile_sender);
		Cinephile cinephile_receiver = cinephile_dao.GetCinephileById(id_cinephile_receiver);
		Message message = new Message(content, datetime, cinephile_sender, cinephile_receiver);
		message_dao.AddMessage(message);
		if (message != null) {
			JSONObject message_json = new JSONObject();
			message_json.put("content", message.getContent_message());
			message_json.put("date", message.getDatetime_message());
			message_json.put("cinephile", message.getCinephile_sender().getUsername());
			message_json.put("response", 200);
			return message_json;
		} else
			return null;

	}
}
