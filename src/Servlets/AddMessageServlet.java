package Servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Services.AddMessageService;

/**
 * The class <code>AddMessageServlet</code> extends <code>HttpServlet</code> is
 * a servlet which add a message by calling the service
 * <code>AddMessageService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AddMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AddMessageService add_message_service;

	@Override
	public void init() throws ServletException {
		add_message_service = new AddMessageService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			Map<String, String[]> map = req.getParameterMap();
			resp.setContentType("text/plain");
			HttpSession session = req.getSession();
			int id_cinephile_sender = (int) session.getAttribute("id_cinephile");

			String content = req.getParameter("content");
			String id_cincephile_receiver = req.getParameter("id_other");

			if (map.containsKey("content") && !content.equals("") && id_cinephile_sender != 0) {
				JSONObject message_json = this.add_message_service.AddMessage(content, new Date(), id_cinephile_sender,
						Integer.valueOf(id_cincephile_receiver));
				resp.getWriter().print(message_json);
			} else
				throw new Exception("Error");
		} catch (Exception e) {
			e.printStackTrace(); // local debug
			req.setAttribute("error", e); // remote debug
			resp.getWriter().print(e);
		}

	}
}
