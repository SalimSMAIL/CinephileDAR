package Services;

import org.json.JSONObject;

import Beans.Cinephile;
import DAO.CinephileDAO;

/**
 * The class <code>UpdateProfileService</code> is a service called by the
 * servlet to update a user
 * 
 * TODO: some extensions
 * 
 * 
 * <p>
 * Created on : October 17, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class UpdateProfileService {
	public JSONObject updateUser(int id, String firstname, String lastname, String address, String email, String password, String description, String gender,String username)
			throws Exception
	{
		String salt = null;
		JSONObject cinephile_json= new JSONObject(); 
		CinephileDAO cinephile_dao = new CinephileDAO(); 
		Cinephile olduser = cinephile_dao.GetCinephileById(id);
		Cinephile existedUsername_cinephile = cinephile_dao.getUserByUsername(username);
		Cinephile existedEmail_cinephile = cinephile_dao.getUserByUserEmail(email);
		if (existedUsername_cinephile!=null) 
	 	{
	 		cinephile_json = new JSONObject().put("message", "Username exists");
	 		return cinephile_json;
	 	}
		
		if (existedEmail_cinephile!=null) 
	 	{
	 		cinephile_json = new JSONObject().put("message", "Email exists");
	 		return cinephile_json;
	 	}
		
		if(!password.equals(""))
			{
			if(username.equals("")&& !firstname.equals("") && !lastname.equals("")) {
				salt= olduser.getUsername().substring(0,1) + firstname.substring(0,1)+lastname.substring(0,1); 		
			}
			if(username.equals("")&& firstname.equals("") && !lastname.equals("")) {
				salt= olduser.getUsername().substring(0,1) +olduser.getFirstname().substring(0,1)+lastname.substring(0,1); 			
			}
			if(username.equals("")&& firstname.equals("") && lastname.equals("")) {
				salt= olduser.getUsername().substring(0,1) + olduser.getFirstname().substring(0,1)+olduser.getLastname().substring(0,1); 
			}
			if(!username.equals("")&& firstname.equals("") && !lastname.equals("")) {
				salt= username.substring(0,1) + olduser.getFirstname().substring(0,1)+lastname.substring(0,1); 
			}
			if(username.equals("")&& firstname.equals("") && !lastname.equals("")) {
				salt= olduser.getUsername().substring(0,1) + olduser.getFirstname().substring(0,1)+lastname.substring(0,1); 
			}
			if(!username.equals("")&& !firstname.equals("") && !lastname.equals("")) {
				salt= username.substring(0,1) + firstname.substring(0,1)+lastname.substring(0,1); 
			}
			if(!username.equals("")&& !firstname.equals("") && lastname.equals("")) {
				salt= username.substring(0,1) + firstname.substring(0,1)+olduser.getLastname().substring(0,1); 
			}
			if(!username.equals("")&& firstname.equals("") && lastname.equals("")) {
				salt= username.substring(0,1) + olduser.getFirstname().substring(0,1)+olduser.getLastname().substring(0,1); 
			}
			password=Utils.Security.byteArrayToHexString(Utils.Security.computeHash(password+salt));
			}
		
		cinephile_dao.updateUser(olduser, firstname, lastname, address, email, password,description,gender,username);
		cinephile_json.put("message", 200);
		return cinephile_json;
		

	}
}
