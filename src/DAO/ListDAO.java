package DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Beans.Element;
import Beans.ListCinephile;
import Utils.HibernateUtil;

/**
 * The class <code>ListDAO</code> is used to manages all the lists of a
 * cinephile
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

public class ListDAO {

	/**
	 * Add a new list to the database
	 * 
	 * @param list
	 *            the list to add
	 * 
	 **/

	public void AddList(ListCinephile list) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(list);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
	}

	/**
	 * Get a specific list by id
	 * 
	 * @param id_list
	 *            the id of the list to get
	 * @return list an object ListCinephile
	 **/

	public ListCinephile GetListById(int id_list) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ListCinephile list = session.get(ListCinephile.class, id_list);
		session.getTransaction().commit();
		return list;
	}

	/**
	 * Get all the list of a user
	 * 
	 * @param id_cinephile
	 *            the id of the user
	 * @return lists an arraylist of all the lists of the user
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<ListCinephile> GetCinephileLists(int id_cinephile) {
		Session session = null;
		ArrayList<ListCinephile> lists = new ArrayList<ListCinephile>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			lists = (ArrayList<ListCinephile>) session
					.createQuery(
							"Select c.cinephile_lists from Cinephile c where c.cinephile_id='" + id_cinephile + "'")
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return lists;
	}

	/**
	 * Get all the element that contains a list
	 * 
	 * @param id_list
	 *            the id of the list to get all its elements
	 * @return elements an arraylist of all the elements of the list
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<Element> GetListElements(int id_list) {
		Session session = null;
		ArrayList<Element> elements = new ArrayList<Element>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			elements = (ArrayList<Element>) session
					.createQuery("Select c.elements from ListCinephile c where c.list_id='" + id_list + "'").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return elements;
	}

	/**
	 * Add a new element to a list
	 * 
	 * @param id_element
	 *            the id of the element to add
	 * @param id_list
	 *            the id of the list which will contain the element to add
	 * 
	 **/

	public void AddElementToList(int id_element, int id_list) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			@SuppressWarnings("deprecation")
			Query query = session
					.createSQLQuery("Insert INTO LIST_ELEMENT(ELEMENT_ID, LIST_ID) VALUES (:value1, :value2)");
			query.setParameter("value1", id_element);
			query.setParameter("value2", id_list);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * Verify if a specific element exists in a specific list
	 * 
	 * @param id_element
	 *            the id of the element to verify
	 * @param id_list
	 *            the id of the list
	 * @return true in the case where the element exists in this list
	 * 
	 **/

	@SuppressWarnings("finally")
	public boolean IsElementExistsInTheList(int id_element, int id_list) {
		boolean exists = false;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			Query query = session.createSQLQuery(
					"SELECT * From LIST_ELEMENT WHERE ELEMENT_ID=" + id_element + " AND LIST_ID=" + id_list);
			ArrayList<Object> res = (ArrayList<Object>) query.getResultList();
			if (res.size() > 0)
				exists = true;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
			return exists;
		}
	}

}
