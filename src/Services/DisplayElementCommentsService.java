package Services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import Beans.Comment;
import DAO.CinephileDAO;
import DAO.CommentDAO;

/**
 * The class <code>DisplayElementCommentsService</code> is a service called by
 * the servlet to display the comments in the web page
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

public class DisplayElementCommentsService {

	@SuppressWarnings("finally")
	public JSONObject DisplayElementComments(int id_element) throws Exception {

		CommentDAO comment_dao = new CommentDAO();
		ArrayList<Comment> comments_list = new ArrayList<Comment>();
		JSONObject json_element_comments = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			comments_list = comment_dao.GetListOfCommentsElement(id_element);
			for (Comment comment : comments_list) {
				JSONObject commentJSON = new JSONObject();
				Cinephile cinephile = comment.getCinephile();
				commentJSON.put("id", comment.getComment_id());
				commentJSON.put("content", comment.getContent());
				commentJSON.put("date", comment.getDate_time());
				commentJSON.put("cinephile", cinephile.getUsername());

				array.put(commentJSON);
			}

			json_element_comments = new JSONObject().put("comments", array);
			json_element_comments.put("response", 200);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			return json_element_comments;
		}
	}

}
