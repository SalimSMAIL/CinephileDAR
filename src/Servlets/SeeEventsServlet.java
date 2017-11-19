package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Services.SeeEventsService;

/**
 * The class <code>SeeEventsServlet</code> extends <code>HttpServlet</code> is a
 * servlet which returns all the events of the database by calling the service
 * <code>SeeEventsService</code>
 * 
 * TODO: complete !
 */

public class SeeEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SeeEventsService see_events_service;

	@Override
	public void init() throws ServletException {
		see_events_service = new SeeEventsService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json_events = new JSONObject();
		try {

			json_events = see_events_service.DisplayEvents();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json_events);

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
