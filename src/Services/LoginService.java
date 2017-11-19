package Services;

import org.json.JSONObject;

import Beans.Cinephile;
import DAO.CinephileDAO;

/**
 * The class <code>LoginService</code> is a service called by the servlet to log
 * a user
 * 
 * TODO: Complete
 * 
 * 
 * <p>
 * Created on : October 02, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class LoginService {

	Cinephile cinephile = null;

	CinephileDAO cinephile_dao = new CinephileDAO();

	public JSONObject LoginCinephile(String email, String password) throws Exception {

		cinephile = cinephile_dao.getUserByUserEmail(email);
		if (cinephile == null || !cinephile.getEmail().toLowerCase().equals(email.toLowerCase())) {
			return new JSONObject().put("message", "Invalid email");
		}
		String salt = cinephile.getUsername().substring(0, 1) + cinephile.getFirstname().substring(0, 1)
				+ cinephile.getLastname().substring(0, 1);
		System.out.println("salt = " + salt);
		String password_hashed = Utils.Security.byteArrayToHexString(Utils.Security.computeHash(password + salt));
		if (!cinephile.getPassword().equals(password_hashed)) {
			return new JSONObject().put("message", "Invalid password");
		} else {
			JSONObject jo = new JSONObject().put("id_user", CinephileDAO.getCinephileId(email));
			jo.put("message", 200);
			return jo;

		}

	}

}
