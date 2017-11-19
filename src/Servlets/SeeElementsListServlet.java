package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Services.SeeElementsListService;

/**
 * The class <code>SeeElementsListServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns the elements of the list
 * by calling the service <code>SeeElementsListService</code>
 * 
 * TODO: complete !
 */

public class SeeElementsListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SeeElementsListService see_elements_list_service;

	@Override
	public void init() throws ServletException {
		see_elements_list_service = new SeeElementsListService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		JSONObject json_elements = new JSONObject();
		try {
			if (req.getParameter("id_list") != null)

			{
				json_elements = see_elements_list_service
						.DisplayListElements(Integer.valueOf(req.getParameter("id_list")));
				resp.getWriter().print(json_elements);
			} else
				throw new Exception("Erreur");

		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
