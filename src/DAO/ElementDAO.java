package DAO;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

import Beans.Element;
import Beans.TypeElement;
import Utils.HibernateUtil;

/**
 * The class <code>ElementDAO</code> is used to manages the elements
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

public class ElementDAO {

	/**
	 * Add a new element to the database
	 * 
	 * @param element
	 *            the element to add
	 * 
	 **/

	public void AddElement(Element element) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(element);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
		System.out.println("Element added \n");
	}

	/**
	 * Get an element by Id
	 * 
	 * @param id_element
	 *            the id of the element to get
	 * 
	 **/

	public Element GetElementById(int id_element) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Element element = session.get(Element.class, id_element);
		session.getTransaction().commit();
		return element;
	}

	/**
	 * Used in the task to delete periodically the movies an add the new ones
	 * 
	 **/

	public void deleteAllNewFilms() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// On recupere tous les elements de type NEW_FILM de la BD
		Query query = session.createQuery("from Element where element_type = :te ");
		query.setParameter("te", TypeElement.NEW_FILM);
		List list = query.list();
		// On supprime chaque element du resultat de la requete de la BD
		for (Object o : list) {
			if (o instanceof Element) {
				// On supprime cet element de la BD
				session.delete(o);
				System.out.println("element supprime");
			}
		}
		session.getTransaction().commit();
	}

}
