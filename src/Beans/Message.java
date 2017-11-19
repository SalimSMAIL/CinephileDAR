package Beans;

import java.util.Date;

import javax.persistence.*;

/**
 * The class <code>Message</code> implements <code>Serializable</code> This
 * entity represents the caracteristics of a Message and its getters/setters
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
@Table(name = "MESSAGE")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGE_ID")
	private int id_message;
	@Column(name = "CONTENT_MESSAGE")
	private String content_message;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME")
	private Date datetime_message;
	@ManyToOne(optional = false)
	private Cinephile cinephile_sender;
	@ManyToOne(optional = false)
	private Cinephile cinephile_receiver;

	public Message() {
		super();
	}

	public Message(String content_message, Date datetime_message) {
		super();
		this.content_message = content_message;
		this.datetime_message = datetime_message;
	}

	public Message(String content_message, Date datetime_message, Cinephile cinephile_sender,
			Cinephile cinephile_receiver) {
		super();
		this.content_message = content_message;
		this.datetime_message = datetime_message;
		this.cinephile_sender = cinephile_sender;
		this.cinephile_receiver = cinephile_receiver;
	}

	public int getId_message() {
		return id_message;
	}

	public void setId_message(int id_message) {
		this.id_message = id_message;
	}

	public String getContent_message() {
		return content_message;
	}

	public Date getDatetime_message() {
		return datetime_message;
	}

	public Cinephile getCinephile_sender() {
		return cinephile_sender;
	}

	public Cinephile getCinephile_receiver() {
		return cinephile_receiver;
	}

	public void setContent_message(String content_message) {
		this.content_message = content_message;
	}

	public void setDatetime_message(Date datetime_message) {
		this.datetime_message = datetime_message;
	}

	public void setCinephile_sender(Cinephile cinephile_sender) {
		this.cinephile_sender = cinephile_sender;
	}

	public void setCinephile_receiver(Cinephile cinephile_receiver) {
		this.cinephile_receiver = cinephile_receiver;
	}

}
