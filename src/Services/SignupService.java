package Services;

import org.json.JSONException;
import org.json.JSONObject;

import Beans.Cinephile;
import DAO.CinephileDAO;
import Utils.Tools;

/**
 * The class <code>SignupService</code> is a service called by the servlet to
 * register users
 * 
 * TODO: some extensions
 * 
 * 
 * <p>
 * Created on : October 02, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class SignupService {

	public JSONObject CreateCinephile(String username, String email, String firstname, String lastname, String gender,
			String address, String description, String password) throws Exception {
		JSONObject cinephile_json = null;
		CinephileDAO cinephile_dao = new CinephileDAO();
		Cinephile existedUsername_cinephile = cinephile_dao.getUserByUsername(username);
		if (existedUsername_cinephile != null) {
			cinephile_json = new JSONObject().put("message", "Username exists");
			return cinephile_json;
		}
		Cinephile existedEmail_cinephile = cinephile_dao.getUserByUserEmail(email);
		if (existedEmail_cinephile != null) {
			cinephile_json = new JSONObject().put("message", "Email exists");
			return cinephile_json;
		}
		String salt = username.substring(0, 1) + firstname.substring(0, 1) + lastname.substring(0, 1);
		String password_hashed = Utils.Security.byteArrayToHexString(Utils.Security.computeHash(password + salt));
		Cinephile cinephile = new Cinephile(username, email, firstname, lastname, gender, address, description,
				password_hashed);
		cinephile_dao.AddCinephile(cinephile);
		cinephile_json = new JSONObject().put("id_cinephile", cinephile.getId());
		cinephile_json.put("message", 200);
		return cinephile_json;

	}
}
