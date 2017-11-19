package DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Beans.Seance;
import Utils.HibernateUtil;

/**
 * The class <code>SeanceDAO</code> is used to manages the seances
 *
 * TODO: See extensions in the report
 * 
 * 
 * <p>
 * Created on : October 11, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class SeanceDAO {

	/**
	 * get all the seances
	 * 
	 * @return seances an arraylist of all the seances
	 **/

	public ArrayList<Seance> GetSeances() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query requete = session.createQuery("select c from Seance c");
		@SuppressWarnings("unchecked")
		ArrayList<Seance> seances = (ArrayList<Seance>) requete.list();
		session.getTransaction().commit();
		return seances;
	}

	/**
	 * get a seance by Id
	 * 
	 * @param id
	 *            id of the seance
	 * @return seance an object seance
	 **/

	public Seance GetSeanceById(int id_seance) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Seance seance = session.get(Seance.class, id_seance);
		session.getTransaction().commit();
		return seance;
	}

}
