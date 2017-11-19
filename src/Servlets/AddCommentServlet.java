package Servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Beans.TypeElement;
import Services.AddCommentService;

/**
 * The class <code>AddCommentServlet</code> extends <code>HttpServlet</code> is
 * a servlet which add a comment by calling the service
 * <code>AddCommentService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AddCommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AddCommentService add_comment_service;

	@Override
	public void init() throws ServletException {
		add_comment_service = new AddCommentService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			Map<String, String[]> map = req.getParameterMap();
			resp.setContentType("text/plain");

			HttpSession session = req.getSession();
			int id_cinephile = (int) session.getAttribute("id_cinephile");

			String content = req.getParameter("content");
			TypeElement type_element = null;
			String element_id = "";

			if (req.getParameter("id_movie") != null && !req.getParameter("id_movie").equals("")) {
				element_id = "id_movie";
				type_element = TypeElement.MOVIE;
			} else if (req.getParameter("id_tvshow") != null && !req.getParameter("id_tvshow").equals("")) {
				element_id = "id_tvshow";
				type_element = TypeElement.TVSHOW;
			} else if (req.getParameter("id_person") != null && !req.getParameter("id_person").equals("")) {
				element_id = "id_person";
				type_element = TypeElement.STAR;
			}

			if (map.containsKey("content") && !content.equals("") && id_cinephile != 0) {
				JSONObject comment_json = this.add_comment_service.AddComment(content, new Date(), id_cinephile,
						Integer.valueOf(req.getParameter(element_id)), type_element);
				resp.getWriter().print(comment_json);
			} else
				throw new Exception("Error");
		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}

	}

}