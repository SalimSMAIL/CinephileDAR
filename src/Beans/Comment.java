package Beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The class <code>Comment</code> implements <code>Serializable</code> This
 * entity represents the caracteristics of a Comment and its getters/setters
 *
 * TODO: complete!
 * 
 * 
 * <p>
 * Created on : September 30, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMMENT_ID")
	private int comment_id;
	@Column(name = "CONTENT")
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME")
	private Date date_time;
	@ManyToOne(optional = false)
	@JoinColumn(name = "CINEPHILE_ID")
	private Cinephile cinephile;
	@ManyToOne(optional = false)
	@JoinColumn(name = "ELEMENT_ID")
	private Element element;

	public Comment() {

	}

	public Comment(String content, Date date_time) {
		super();
		this.content = content;
		this.date_time = date_time;
	}

	public Comment(String content, Date date_time, Cinephile cinephile, Element element) {
		this.content = content;
		this.date_time = date_time;
		this.cinephile = cinephile;
		this.element = element;
	}

	public int getComment_id() {
		return comment_id;
	}

	public String getContent() {
		return content;
	}

	public Date getDate_time() {
		return date_time;
	}

	public Cinephile getCinephile() {
		return cinephile;
	}

	public Element getElement() {
		return element;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	public void setCinephile(Cinephile cinephile) {
		this.cinephile = cinephile;
	}

	public void setElement(Element element) {
		this.element = element;
	}

}
