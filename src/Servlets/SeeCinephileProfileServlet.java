package Servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Services.SeeCinephileProfileService;

/**
 * The class <code>SeeCinephileProfileServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the informations of the
 * profile of the user by calling the service
 * <code>SeeCinephileProfileService</code>
 * 
 * TODO: Complete 
 */

public class SeeCinephileProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SeeCinephileProfileService cinephile_profile_service;

	@Override
	public void init() throws ServletException {
		cinephile_profile_service = new SeeCinephileProfileService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		JSONObject jo = new JSONObject();

		HttpSession session = req.getSession();
		try {
			Map<String, String[]> map = req.getParameterMap();
			if (session.getAttribute("id_cinephile") != null && !session.getAttribute("id_cinephile").equals("")) {
				jo = cinephile_profile_service
						.GetCinephileProfile(Integer.valueOf(String.valueOf(session.getAttribute("id_cinephile"))));

			} else {
				jo = null;
			}

			resp.getWriter().print(jo);

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}
	}

}
