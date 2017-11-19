package Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The class <code>AuthorizationFilter</code> implements <code>Filter</code> and
 * used to redirect a user to the connexion page if he is not connected to his
 * session
 *
 * TODO: See extensions in the report
 * 
 * 
 * <p>
 * Created on : October 15, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AuthorizationFilter implements Filter {

	public static final String USER_SESSION = "id_cinephile";
	public static final String SIGNIN_PAGE = "Login.html";
	public static final String SIGNUP_PAGE = "Signup.html";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// Getting the session;
		HttpSession session = request.getSession();
		if (request.getRequestURI().equals(request.getContextPath() + "/")
				&& session.getAttribute(USER_SESSION) == null) {
			filterChain.doFilter(request, response);
		} else if (request.getRequestURI().equals(request.getContextPath() + "/Signup.html")
				&& session.getAttribute(USER_SESSION) == null) {
			filterChain.doFilter(request, response);
		} else if (request.getRequestURI().equals(request.getContextPath() + "/Login.html")
				&& session.getAttribute(USER_SESSION) == null) {
			filterChain.doFilter(request, response);
		}
		// Checking if the user exists in the session;
		else if (session.getAttribute(USER_SESSION) == null) {
			// Redirect to connexion page;
			response.sendRedirect(SIGNIN_PAGE);
		} else {
			filterChain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}
}
