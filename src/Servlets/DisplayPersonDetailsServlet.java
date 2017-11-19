package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Services.DisplayPersonDetailsService;

/**
 * The class <code>DisplayPersonDetailsServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the details of a star by
 * calling the service <code>DisplayPersonDetailsService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class DisplayPersonDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DisplayPersonDetailsService display_person_details_service;

	@Override
	public void init() throws ServletException {
		display_person_details_service = new DisplayPersonDetailsService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_details_person = new JSONObject();

		try {
			if ((!request.getParameter("id_person").equals("")) && (!request.getParameter("name_person").equals(""))) {
				json_details_person = display_person_details_service.DisplayPersonDetails(
						Integer.valueOf(request.getParameter("id_person")), request.getParameter("name_person"));
			} else {
				json_details_person = null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_details_person);

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
