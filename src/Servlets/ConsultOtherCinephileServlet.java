package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import Services.ConsultOtherCinephileService;

/**
 * The class <code>ConsultOtherCinephileServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the informations of the
 * user we have searched by calling the service
 * <code>ConsultOtherCinephileService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class ConsultOtherCinephileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConsultOtherCinephileService consult_other_service;

	@Override
	public void init() throws ServletException {
		consult_other_service = new ConsultOtherCinephileService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		JSONObject jo = new JSONObject();

		try {
			if (!req.getParameter("id_other").equals("")) {
				jo = consult_other_service
						.GetCinephileProfile(Integer.valueOf(req.getParameter("id_other").toString()));

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

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
