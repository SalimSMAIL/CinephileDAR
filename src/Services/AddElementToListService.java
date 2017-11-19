package Services;

import org.json.JSONException;
import org.json.JSONObject;

import Beans.Element;
import Beans.TypeElement;
import DAO.ElementDAO;
import DAO.ListDAO;

/**
 * The class <code>AddElementToListService</code> is a service called by the
 * servlet to add an element to a list 
 * 
 * TODO: Complete !
 * 
 * 
 * <p>
 * Created on : October 19, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class AddElementToListService {

	public JSONObject AddElementToList(int id_list, int id_element, TypeElement type) throws JSONException {
		ListDAO list_dao = new ListDAO();
		ElementDAO element_dao = new ElementDAO();

		Element element = element_dao.GetElementById(id_element);
		if (element == null) {
			element_dao.AddElement(new Element(id_element, type));
			element = element_dao.GetElementById(id_element);
		}

		JSONObject list_json = new JSONObject();
		if (list_dao.IsElementExistsInTheList(element.getElement_id(), id_list)) {
			list_json.put("response", "element existe deja");
		} else {
			list_dao.AddElementToList(id_element, id_list);
			list_json.put("response", 200);
		}

		return list_json;

	}
}
