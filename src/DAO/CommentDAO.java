package DAO;

import java.util.ArrayList;

import org.hibernate.Session;

import Beans.Comment;
import Utils.HibernateUtil;

/**
 * The class <code>CommentDAO</code> is used to manages the comments of the web
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

public class CommentDAO {

	/**
	 * Add a new comment to the database
	 * 
	 * @param comment
	 *            the comment to add
	 * 
	 **/

	public void AddComment(Comment comment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(comment);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
		System.out.println("Comment added \n");
	}

	/**
	 * Get list of comments by id_element
	 * 
	 * @param id_element
	 *            the id of the element to get all this comments
	 * @return comments_lists an arraylist of Comment
	 **/
	@SuppressWarnings("unchecked")
	public ArrayList<Comment> GetListOfCommentsElement(int id_element) {
		Session session = null;
		ArrayList<Comment> comments_list = new ArrayList<Comment>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			comments_list = (ArrayList<Comment>) session
					.createQuery("from Comment c where c.element.element_id='" + id_element + "'").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return comments_list;

	}

}
