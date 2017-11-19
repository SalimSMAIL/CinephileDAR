package Services;

import java.util.Date; 

import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import Beans.Comment;
import Beans.Element;
import Beans.TypeElement;
import DAO.CinephileDAO;
import DAO.CommentDAO;
import DAO.ElementDAO;

/**
 * The class <code>AddCommentService</code> is a service called by the servlet to add Comment
 *
 * TODO: Complete !
 * 
 * 
 * <p>
 * Created on : October 19, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AddCommentService {

	public JSONObject AddComment(String content, Date datetime, int id_cinephile , int id_element, TypeElement type)
    	    throws JSONException
    {
	 	CommentDAO comment_dao = new CommentDAO(); 
	 	CinephileDAO cinephile_dao = new CinephileDAO(); 
	 	ElementDAO element_dao = new ElementDAO(); 
	 	
	 	Cinephile cinephile = cinephile_dao.GetCinephileById(id_cinephile); 
	 	Element element = element_dao.GetElementById(id_element); 
		if (element==null)
			{
				element_dao.AddElement(new Element(id_element, type));
				element = element_dao.GetElementById(id_element); 
			}
	 	Comment comment = new Comment(content, datetime, cinephile, element); 
        comment_dao.AddComment(comment);       
        if(comment!=null)
        {
        	JSONObject comment_json = new JSONObject();
        	comment_json.put("id_comment", comment.getComment_id());
        	comment_json.put("content", comment.getContent());
        	comment_json.put("date", comment.getDate_time());
        	comment_json.put("cinephile", comment.getCinephile().getUsername());
        	comment_json.put("element", comment.getElement());
        	comment_json.put("response", 200);
            return comment_json;
        }
        else return null; 
    	   
    }
}
