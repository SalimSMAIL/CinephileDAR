package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Beans.TypeElement;
import Services.DisplayElementCommentsService;

/**
 * The class <code>DisplayElementCommentsServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the elements of a comment
 * by calling the service <code>DisplayElementCommentsService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class DisplayElementCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DisplayElementCommentsService display_element_comments_service;

	@Override
	public void init() throws ServletException {
		display_element_comments_service = new DisplayElementCommentsService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_comments_element = new JSONObject();
		try {

			if (request.getParameter("id_movie") != null && !request.getParameter("id_movie").equals("")) {
				json_comments_element = display_element_comments_service
						.DisplayElementComments(Integer.valueOf(request.getParameter("id_movie")));
			} else if (request.getParameter("id_tvshow") != null && !request.getParameter("id_tvshow").equals("")) {
				json_comments_element = display_element_comments_service
						.DisplayElementComments(Integer.valueOf(request.getParameter("id_tvshow")));
			} else if (request.getParameter("id_person") != null && !request.getParameter("id_person").equals("")) {
				json_comments_element = display_element_comments_service
						.DisplayElementComments(Integer.valueOf(request.getParameter("id_person")));
			} else {
				json_comments_element = null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_comments_element);

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			request.setAttribute("error", e); // remote debug
			response.getWriter().print(e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
