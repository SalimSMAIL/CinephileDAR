package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Beans.TypeElement;
import Services.AddElementToListService;

/**
 * The class <code>AddElementToListServlet</code> extends
 * <code>HttpServlet</code> is a servlet which add an element to a list by
 * calling the service <code>AddElementToListService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AddElementToListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AddElementToListService add_element_to_list_service;

	@Override
	public void init() throws ServletException {
		add_element_to_list_service = new AddElementToListService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

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

			if (req.getParameter("id_list") != null) {
				JSONObject element_list_json = this.add_element_to_list_service.AddElementToList(
						Integer.valueOf(req.getParameter("id_list")), Integer.valueOf(req.getParameter(element_id)),
						type_element);
				resp.getWriter().print(element_list_json);
			} else
				throw new Exception("Error");

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}

	}
}