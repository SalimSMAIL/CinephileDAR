package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Services.DisplayMessagesBetweenTwoCinephilesService;

/**
 * The class <code>DisplayMessagesBetweenTwoCinephilesServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns messages between two
 * users by calling the service
 * <code>DisplayMessagesBetweenTwoCinephilesService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class DisplayMessagesBetweenTwoCinephilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DisplayMessagesBetweenTwoCinephilesService display_messages_between_two_cinephiles_service;

	@Override
	public void init() throws ServletException {
		display_messages_between_two_cinephiles_service = new DisplayMessagesBetweenTwoCinephilesService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_messages = new JSONObject();
		try {
			HttpSession session = request.getSession();
			if (request.getParameter("id_other") != null && !request.getParameter("id_other").equals("")) {
				System.out.println(request.getParameter("id_other"));
				json_messages = display_messages_between_two_cinephiles_service.DisplayMessages(
						(int) session.getAttribute("id_cinephile"), Integer.valueOf(request.getParameter("id_other")));
			} else {
				json_messages = null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_messages);

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
