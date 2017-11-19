package Services;

import java.util.Date; 

import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import Beans.Evenement;
import Beans.Seance;
import DAO.CinephileDAO;
import DAO.EventDAO;
import DAO.SeanceDAO;

/**
 * The class <code>AddEventService</code> is a service called by the
 * servlet to add an new event
 * 
 *  TODO: See extensions in the report
 * 
 * 
 * <p>
 * Created on : October 19, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AddEventService {
	
	public JSONObject AddEvent(String title, String  description, String place, Date datetime, int limit, int created_by, int seance_id)
    	    throws JSONException
    {
	 	EventDAO event_dao = new EventDAO(); 
	 	CinephileDAO cinephile_dao = new CinephileDAO();
	 	SeanceDAO seance_dao = new SeanceDAO();
	 	
	 	Cinephile cinephile = cinephile_dao.GetCinephileById(created_by); 
	 	Seance seance = seance_dao.GetSeanceById(seance_id); 
	 	
	 	Evenement event = new Evenement(title, description, place, datetime, limit, cinephile, seance); 
        
	 	event_dao.AddEvent(event);  
	 	event_dao.ParticipateEvent(created_by, event.getEvent_id());
        if(event!=null)
        {
        	JSONObject event_json = new JSONObject();
        	event_json.put("id_event", event.getEvent_id());
        	event_json.put("title", event.getTitle());
        	event_json.put("description", event.getDescription()); 
        	event_json.put("date", event.getDate());
        	event_json.put("limit", event.getLimit()); 
        	event_json.put("cinephile", event.getCinephile().getUsername());
        	event_json.put("affiche", event.getSeance().getAffiche());
        	event_json.put("response", 200);
            return event_json;
        }
        else return null; 
    	   
    }

}
