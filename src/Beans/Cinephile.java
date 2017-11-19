package Beans;

import java.util.List;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The class <code>Cinephile</code> implements <code>Serializable</code> This
 * entity represents the caracteristics of a Cinephile and its getter/
 * setters
 *
 * TODO:
 * Add birth date
 * 
 * <p>
 * Created on : September 30, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

@Entity
@Table(name = "CINEPHILE")
public class Cinephile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CINEPHILE_ID")
	private int cinephile_id;
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;
	@Column(name = "FIRSTNAME")
	private String firstname;
	@Column(name = "LASTNAME")
	private String lastname;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "ADRESS")
	private String adress;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "PASSWORD")
	private String password;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cinephile", fetch = FetchType.LAZY)
	private List<Comment> cinephile_comments;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cinephile", fetch = FetchType.LAZY)
	private List<ListCinephile> cinephile_lists;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cinephile", fetch = FetchType.LAZY)
	private List<Evenement> cinephile_events;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cinephile_sender", fetch = FetchType.LAZY)
	private List<Message> messages_sent;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cinephile_receiver", fetch = FetchType.LAZY)
	private List<Message> messages_received;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EVENT_CINEPHILE", joinColumns = { @JoinColumn(name = "CINEPHILE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "EVENT_ID") })
	private List<Evenement> joined_events;

	public Cinephile() {

	}

	public Cinephile(String username, String email, String firstname, String lastname, String gender, String adress,
			String description, String password) {
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.adress = adress;
		this.description = description;
		this.password = password;
	}

	public int getId() {
		return cinephile_id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getGender() {
		return gender;
	}

	public String getAdress() {
		return adress;
	}

	public String getDescription() {
		return description;
	}

	public String getPassword() {
		return password;
	}

	public List<Comment> getCinephile_comments() {
		return cinephile_comments;
	}

	public List<ListCinephile> getCinephile_lists() {
		return cinephile_lists;
	}

	public List<Evenement> getCinephile_events() {
		return cinephile_events;
	}

	public List<Message> getMessages_sent() {
		return messages_sent;
	}

	public List<Message> getMessages_received() {
		return messages_received;
	}

	public List<Evenement> getJoined_events() {
		return joined_events;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCinephile_comments(List<Comment> cinephile_comments) {
		this.cinephile_comments = cinephile_comments;
	}

	public void setCinephile_lists(List<ListCinephile> cinephile_lists) {
		this.cinephile_lists = cinephile_lists;
	}

	public void setCinephile_events(List<Evenement> cinephile_events) {
		this.cinephile_events = cinephile_events;
	}

	public void setMessages_sent(List<Message> messages_sent) {
		this.messages_sent = messages_sent;
	}

	public void setMessages_received(List<Message> messages_received) {
		this.messages_received = messages_received;
	}

	public void setJoined_events(List<Evenement> joined_events) {
		this.joined_events = joined_events;
	}

}
