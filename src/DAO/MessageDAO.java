package DAO;

import java.util.ArrayList;

import org.hibernate.Session;

import Beans.Cinephile;
import Beans.Comment;
import Beans.Element;
import Beans.Message;
import Utils.HibernateUtil;

/**
 * The class <code>MessageDAO</code> is used to manages the messages between
 * users
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

public class MessageDAO {

	/**
	 * Add a new message to the database
	 * 
	 * @param message
	 *            the message to add
	 * 
	 **/

	public void AddMessage(Message message) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(message);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
		System.out.println("Message added \n");
	}

	/**
	 * Get a message by id
	 * 
	 * @param id_message
	 *            the id of the message to get
	 * @return message an object Message
	 **/

	public Message GetMessageById(int id_message) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Message message = session.get(Message.class, id_message);
		session.getTransaction().commit();
		return message;
	}

	/**
	 * Get all the messages sent by a user
	 * 
	 * @param id_cinephile_sender
	 *            the id of the cinephile sender
	 * @param id_cinephile_receiver
	 *            the id of the cinephile receiver
	 * 
	 * @return messages_list an arraylist of all the message sent by the user
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<Message> GetMessagesSent(int id_cinephile_sender, int id_cinephile_receiver) {
		Session session = null;
		ArrayList<Message> messages_list = new ArrayList<Message>();
		try {

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			messages_list = (ArrayList<Message>) session.createQuery("from Message c where " + "(c.cinephile_sender='"
					+ id_cinephile_sender + "' and " + "c.cinephile_receiver='" + id_cinephile_receiver + "') ").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return messages_list;
	}

	/**
	 * Get all the messages received by a user
	 * 
	 * @param id_cinephile_sender
	 *            the id of the cinephile sender
	 * @param id_cinephile_receiver
	 *            the id of the cinephile receiver
	 * 
	 * @return messages_list an arraylist of all the message received by the user
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<Message> GetMessagesReceived(int id_cinephile_sender, int id_cinephile_receiver) {
		Session session = null;
		ArrayList<Message> messages_list = new ArrayList<Message>();
		try {

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			messages_list = (ArrayList<Message>) session.createQuery("from Message c where (c.cinephile_sender='"
					+ id_cinephile_receiver + "' and " + "c.cinephile_receiver='" + id_cinephile_sender + "')").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return messages_list;
	}

	/**
	 * Get list of message sent by the user
	 * 
	 * @param id_cinephile
	 *            the sender
	 * @return message_sent an arraylist of the messages sent
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<Message> GetListOfMessageSent(int id_cinephile) {
		Session session = null;
		ArrayList<Message> message_sent = new ArrayList<Message>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			message_sent = (ArrayList<Message>) session
					.createQuery("Select c.messages_sent from Cinephile c where c.cinephile_id='" + id_cinephile + "'")
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return message_sent;

	}

	/**
	 * Get list of message received by the user
	 * 
	 * @param id_cinephile
	 *            the receiver
	 * @return message_received an arraylist of all the message received
	 **/

	@SuppressWarnings("unchecked")
	public ArrayList<Message> GetListOfMessageReceived(int id_cinephile) {
		Session session = null;
		ArrayList<Message> message_received = new ArrayList<Message>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			message_received = (ArrayList<Message>) session
					.createQuery(
							"Select c.messages_received from Cinephile c where c.cinephile_id='" + id_cinephile + "'")
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return message_received;

	}

}
