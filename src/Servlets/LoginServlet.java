package Servlets;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Beans.Cinephile;
import Services.LoginService;

/**
 * The class <code>LoginServlet</code> extends <code>HttpServlet</code> is a
 * servlet to log users by calling the service <code>LoginService</code>
 * 
 * TODO: Complete !
 * 
 */

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private LoginService login_service;

	public LoginServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		login_service = new LoginService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Map<String, String[]> map = request.getParameterMap();
			HttpSession session = request.getSession();
			response.setContentType("text/plain");

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if (map.containsKey("email") && !email.equals("") && map.containsKey("password") && !password.equals("")) {
				JSONObject cinephile_json = login_service.LoginCinephile(email, password);
				String me = cinephile_json.get("message").toString();
				if (!me.equals("Invalid password") && !me.equals("Invalid email")) {
					session.setAttribute("id_cinephile", cinephile_json.get("id_user"));
				}

				response.getWriter().print(cinephile_json);

			} else

			{
				response.getWriter().print("404 wrong URL");
			}

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			request.setAttribute("error", e); // remote debug
			response.getWriter().print(e);
		}

	}
}
