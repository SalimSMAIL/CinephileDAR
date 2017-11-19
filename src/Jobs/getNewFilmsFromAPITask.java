package Jobs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import API.ThemoviedbApiAccess;
import Beans.Element;
import Beans.TypeElement;
import DAO.ElementDAO;

/**
 * The class <code>getNewFilmsFromAPITask</code> implements
 * <code>Runnable</code> used to update the database periodicly with the newest
 * movies and delete the oldest ( each 7 days)
 *
 * TODO: Optimization !
 * 
 * 
 * <p>
 * Created on : November 12, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class getNewFilmsFromAPITask implements Runnable {

	@Override
	public void run() {
		System.out.println("Mise a jour nouveaux films");

		try {
			ElementDAO dao = new ElementDAO();

			// Supprimer tous les films de la BD ayant le type NEW_FILM
			dao.deleteAllNewFilms();

			// Recuperer les nouveaux films de l'API
			JSONArray nouveaux_films = GetNewMovies();

			// Pour chaque resultat, ajouter le film dans la BD s'il n'y est pas deja
			if (!(nouveaux_films == null)) {
				for (int i = 0; i < nouveaux_films.length(); i++) {
					JSONObject film = nouveaux_films.getJSONObject(i);

					int id = film.getInt("id");
					Element element = dao.GetElementById(id);

					// On ajoute le film s'il n'est pas dans la BD
					if (element == null) {
						dao.AddElement(new Element(id, TypeElement.NEW_FILM));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONArray GetNewMovies() throws Exception {
		StringBuffer response = ThemoviedbApiAccess.GetResponseFromAPI(ThemoviedbApiAccess.NEW_FILMS);

		JSONObject json_new_films = null;
		JSONArray new_films = null;
		try {
			JSONObject j = new JSONObject(response.toString());
			if (j.getJSONArray("results").length() > 0) {
				new_films = j.getJSONArray("results");

				json_new_films = new JSONObject();
				json_new_films.put("new_films", new_films);
				return new_films;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new_films;
	}
}
