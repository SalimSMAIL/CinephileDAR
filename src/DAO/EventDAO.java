package DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Beans.Cinephile;
import Beans.Evenement;
import Utils.HibernateUtil;

/**
 * The class <code>EventDAO</code> is used to manages the events of the web
 * application
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

public class EventDAO {

	/**
	 * Add a new event to the database
	 * 
	 * @param event
	 *            the new event to register
	 * 
	 **/

	public void AddEvent(Evenement event) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(event);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
		System.out.println("Event added \n");
	}

	/**
	 * Get all the events stored in the database
	 * 
	 * @return events an arraylist of all the events
	 **/

	public ArrayList<Evenement> GetAllEvents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query requete = session.createQuery("select c from Evenement c");
		@SuppressWarnings("unchecked")
		ArrayList<Evenement> events = (ArrayList<Evenement>) requete.list();
		session.getTransaction().commit();
		return events;
	}

	/**
	 * Get an event by id
	 * 
	 * @param id_event
	 *            the id of the event to get
	 * @return event an object Evenement
	 **/

	public Evenement GetEventById(int id_event) {
		Session session = null;
		Evenement event = new Evenement();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			event = session.get(Evenement.class, id_event);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return event;
	}

	/**
	 * Get the events organized by a user
	 * 
	 * @param id_cinephile
	 *            the id of the user to get all his organized events
	 * @return events an arraylist of Evenements
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<Evenement> GetEventsOrganizedByCinephile(int id_cinephile) {
		Session session = null;
		ArrayList<Evenement> events = new ArrayList<Evenement>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			events = (ArrayList<Evenement>) session
					.createQuery(
							"Select c.cinephile_events from Cinephile c where c.cinephile_id='" + id_cinephile + "'")
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return events;
	}

	/**
	 * Get events joined by cinephile
	 * 
	 * @param id_cinephile
	 *            the id of the cinephile
	 * @return events an arraylist of Evenement
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<Evenement> GetEventsJoinedByCinephile(int id_cinephile) {
		Session session = null;
		ArrayList<Evenement> events = new ArrayList<Evenement>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			events = (ArrayList<Evenement>) session
					.createQuery("Select c.joined_events from Cinephile c where c.cinephile_id='" + id_cinephile + "'")
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return events;
	}

	/**
	 * Get all the participants of an event
	 * 
	 * @param id_event
	 *            the id of the event
	 * @return cinephiles an arraylist of all the cinephiles who have participated
	 *         to the event
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<Cinephile> GetEventParticipants(int id_event) {
		Session session = null;
		ArrayList<Cinephile> cinephiles = new ArrayList<Cinephile>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			cinephiles = (ArrayList<Cinephile>) session
					.createQuery("Select c.cinephiles from Evenement c where c.event_id='" + id_event + "'").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return cinephiles;
	}

	/**
	 * Verify if the user participate or no to an event
	 * 
	 * @param id_cinephile
	 *            the id of the cinephile
	 * @param id_event
	 *            the id of the event
	 * @return true in the case of the cinephile have participated to this event
	 **/

	@SuppressWarnings({ "unchecked", "finally" })
	public boolean IsHeParticipating(int id_cinephile, int id_event) {

		boolean participating = false;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			Query query = session.createSQLQuery(
					"SELECT * From EVENT_CINEPHILE WHERE CINEPHILE_ID=" + id_cinephile + " AND EVENT_ID=" + id_event);
			ArrayList<Object> res = (ArrayList<Object>) query.getResultList();
			if (res.size() > 0)
				participating = true;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
			return participating;
		}

	}

	/**
	 * Add a participant to an event
	 * 
	 * @param id_cinephile
	 *            the id of the participant
	 * @param id_event
	 *            the id of the event
	 **/

	public void ParticipateEvent(int id_cinephile, int id_event) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			@SuppressWarnings("deprecation")
			Query query = session
					.createSQLQuery("Insert INTO EVENT_CINEPHILE(CINEPHILE_ID, EVENT_ID) VALUES (:value1, :value2)");
			query.setParameter("value1", id_cinephile);
			query.setParameter("value2", id_event);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

}
