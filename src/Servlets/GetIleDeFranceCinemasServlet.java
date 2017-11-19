package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.GetIleDeFranceCinemasService;

/**
 * The class <code>GetIleDeFranceCinemasServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns all the cinemas of Paris
 * by calling the service <code>GetIleDeFranceCinemasService</code>
 * 
 * TODO: some extensions
 * 
 */

public class GetIleDeFranceCinemasServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	GetIleDeFranceCinemasService get_cinemas_ils_de_france_service;

	@Override
	public void init() throws ServletException {
		get_cinemas_ils_de_france_service = new GetIleDeFranceCinemasService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("application/json");
			resp.getWriter().print(get_cinemas_ils_de_france_service.GetCinemas());

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
