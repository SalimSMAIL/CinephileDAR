package DAO;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Beans.Cinephile;
import Utils.HibernateUtil;

/**
 * The class <code>CinephileDAO</code> is used to manages the cinephiles of the
 * web application
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

public class CinephileDAO {

	/**
	 * Add a new cinephile to the database
	 * 
	 * @param cinephile
	 *            the new user to register
	 * 
	 **/

	public void AddCinephile(Cinephile cinephile) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(cinephile);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
		System.out.println("n Cinephile added \n");
	}

	/**
	 * Get all cinephiles
	 * 
	 * @return cinephiles an arraylist of all the cinephiles
	 * 
	 **/

	public ArrayList<Cinephile> GetAllCinephiles() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query requete = session.createQuery("select c from Cinephile c");
		ArrayList<Cinephile> cinephiles = (ArrayList<Cinephile>) requete.list();
		session.getTransaction().commit();
		return cinephiles;
	}

	/**
	 * Get a cinephile by id
	 * 
	 * @param id_cinephile
	 *            the id of the cinephile
	 * @return cinephile an object Cinephile
	 **/
	
	public Cinephile GetCinephileById(int id_cinephile) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Cinephile cinephile = session.get(Cinephile.class, id_cinephile);
		session.getTransaction().commit();
		return cinephile;
	}

	/**
	 * Update cinephile's information
	 * 
	 * @param cinephile
	 *            the cinephile to update
	 **/
	
	public void UpdateCinephile(Cinephile cinephile) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(cinephile);
		session.getTransaction().commit();
	}

	/**
	 * get a user by his e-mail
	 * 
	 * @param email
	 *            the email of the user we want to get
	 * @return user an object Cinephile
	 **/

	public Cinephile getUserByUserEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Cinephile user = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Cinephile c where c.email='" + email + "'");
			user = (Cinephile) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;

	}

	/**
	 * get a user by his username
	 * 
	 * @param username
	 *            the username of the user we want to get
	 * @return user an object Cinephile
	 **/
	
	public Cinephile getUserByUsername(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Cinephile user = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Cinephile c where c.username='" + username + "'");
			user = (Cinephile) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;

	}

	/**
	 * get the id of a user
	 * 
	 * @param email
	 *            the email of the id_user we want to get
	 * @return id the id of the Cinephile we wanted
	 **/

	public static int getCinephileId(String email)

	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int id = 0;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("select user.id from Cinephile user where user.email='" + email + "'");
			id = (int) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	/**
	 * Update a user
	 * 
	 * @param olduser
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param email
	 * @param password
	 * @param description
	 * @param gender
	 * @param username
	 **/

	public void updateUser(Cinephile olduser, String firstname, String lastname, String address, String email,
			String password, String description, String gender, String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		if (!firstname.equals(""))
			olduser.setFirstname(firstname);
		if (!lastname.equals(""))
			olduser.setLastname(lastname);
		if (!address.equals(""))
			olduser.setAdress(address);
		if (!email.equals(""))
			olduser.setEmail(email);
		if (!password.equals(""))
			olduser.setPassword(password);
		if (!description.equals(""))
			olduser.setDescription(description);
		if (!gender.equals(""))
			olduser.setGender(gender);
		if (!username.equals(""))
			olduser.setUsername(username);
		session.saveOrUpdate(olduser);
		tx.commit();

	}

}
