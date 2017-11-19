package Servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Services.SignupService;
import Services.UpdateProfileService;

/**
 * The class <code>UpdateProfileServlet</code> extends <code>HttpServlet</code>
 * is a servlet to update the profil of the user by calling the service
 * <code>UpdateProfileService</code>
 * 
 * TODO: complete !
 */

public class UpdateProfileServlet extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private UpdateProfileService update_profile_service;

	@Override
	public void init() throws ServletException {
		update_profile_service = new Services.UpdateProfileService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			Map<String, String[]> map = req.getParameterMap();

			resp.setContentType("text/plain");

			HttpSession session = req.getSession();

			int id_cinephile = Integer.valueOf(session.getAttribute("id_cinephile").toString());
			JSONObject jo = update_profile_service.updateUser(id_cinephile, req.getParameter("firstname"),
					req.getParameter("lastname"), req.getParameter("address"), req.getParameter("email").toLowerCase(),
					req.getParameter("password"), req.getParameter("description"), req.getParameter("gender"),
					req.getParameter("username"));

			resp.getWriter().print(jo);
		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e);
		}
	}

}
