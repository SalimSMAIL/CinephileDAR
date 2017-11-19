package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import Services.ConsultAllMyMessageService;

/**
 * The class <code>ConsultMessagesServlet</code> extends
 * <code>HttpServlet</code> is a servlet which returns all the message of a user
 * by calling the service <code>ConsultAllMyMessageService</code>
 * 
 * TODO: some extensions
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class ConsultMessagesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConsultAllMyMessageService consultmymessage_service;

	@Override
	public void init() throws ServletException {
		consultmymessage_service = new ConsultAllMyMessageService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		JSONObject jo_message_received = new JSONObject();
		HttpSession session = req.getSession();
		try {
			if (session.getAttribute("id_cinephile") != null && !session.getAttribute("id_cinephile").equals("")) {
				jo_message_received = consultmymessage_service
						.DisplayMessagesCinephiles(Integer.valueOf(session.getAttribute("id_cinephile").toString()));
			} else {
				jo_message_received = null;
			}
			resp.getWriter().print(jo_message_received);

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
