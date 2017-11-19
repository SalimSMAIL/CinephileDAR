import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;

import Beans.Cinephile;
import Beans.Comment;
import Beans.Element;
import Beans.Evenement;
import Beans.Message;
import Beans.TypeElement;
import DAO.CinephileDAO;
import DAO.CommentDAO;
import DAO.ElementDAO;
import DAO.EventDAO;
import DAO.ListDAO;
import DAO.MessageDAO;
import Services.AddMessageService;

public class Test {

	public static void main(String[] args) throws JSONException {

		/*
		 * CommentDAO comment_dao = new CommentDAO(); ArrayList<Comment> comments_list=
		 * new ArrayList<Comment>(); comments_list =
		 * comment_dao.GetListOfCommentsElement(211672); for (int i=0;
		 * i<comments_list.size(); i++) {
		 * System.out.println(comments_list.get(i).getContent()); }
		 */

		// AddMessageService add = new AddMessageService();
		// MessageDAO m = new MessageDAO();
		// CinephileDAO c = new CinephileDAO();
		// Cinephile c1 = c.GetCinephileById(8);
		// Cinephile c2 = c.GetCinephileById(17);

		/*
		 * ArrayList<Message> messages = m.GetMessagesGetweenTwoCinephiles(21, 17);
		 * System.out.println("taillllle = " + messages.size()); for (int i=0;
		 * i<messages.size(); i++) {
		 * System.out.println(messages.get(i).getContent_message()); }
		 */

		// add.AddMessage("Hello", new Date(), c1.getId(), c2.getId());

		// TODO Auto-generated method stub
		/*
		 * ElementDAO element_dao = new ElementDAO();
		 * 
		 * Element element = element_dao.GetElementById(4); if (element==null) {
		 * element_dao.AddElement(new Element(4,TypeElement.MOVIE)); element =
		 * element_dao.GetElementById(4); }
		 * 
		 * CinephileDAO cinephile_dao = new CinephileDAO();
		 * 
		 * Cinephile cinephile = cinephile_dao.GetCinephileByUsername("username");
		 * 
		 * CommentDAO comment_dao = new CommentDAO(); Comment comment = new
		 * Comment("content comment", new Date(), cinephile, element);
		 * comment_dao.AddComment(comment);
		 */

		ListDAO list_dao = new ListDAO();
		ArrayList<Element> elements = list_dao.GetListElements(3);

		for (int i = 0; i < elements.size(); i++) {
			System.out.println(elements.get(i).getElement_type());
		}
		/*
		 * CinephileDAO cinephile_dao = new CinephileDAO(); EventDAO event_dao = new
		 * EventDAO(); Cinephile cinephile = cinephile_dao.GetCinephileById(1);
		 * Evenement event = event_dao.GetEventById(4);
		 * 
		 * //Evenement event = event_dao.GetEventById(4); boolean vrai
		 * =event_dao.IsHeParticipating(cinephile.getId(),event.getEvent_id());
		 */
	}

}
