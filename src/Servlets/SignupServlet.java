package Servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.json.JSONObject;

import Services.SignupService;

/**
 * The class <code>SignupServlet</code> extends
 * <code>HttpServlet</code> is a servlet to sign in by calling the service
 * <code>SignupService</code>
 * 
 * TODO: complete !
 */

public class SignupServlet extends HttpServlet {

	private SignupService inscription_service;

	@Override
	public void init() throws ServletException {
		inscription_service = new Services.SignupService();
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

			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String gender = req.getParameter("gender");
			String description = req.getParameter("description");
			String address = req.getParameter("address");

			if (map.containsKey("username") && !username.equals("") && map.containsKey("email") && !email.equals("")
					&& map.containsKey("password") && !password.equals("") && map.containsKey("firstname")
					&& !firstname.equals("") && map.containsKey("lastname") && !lastname.equals("")
					&& map.containsKey("gender") && !gender.equals("") && map.containsKey("description")
					&& !description.equals("") && map.containsKey("address") && !address.equals("")) {
				session.setAttribute("username", username);
				JSONObject cinephile_json = this.inscription_service.CreateCinephile(username, email.toLowerCase(), firstname,
						lastname, gender, address, description, password);

				resp.getWriter().print(cinephile_json);
			} else
				throw new Exception("Error");
		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}

	}

}
