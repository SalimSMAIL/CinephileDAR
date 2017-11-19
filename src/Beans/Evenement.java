package Beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * The class <code>Evenement</code> implements <code>Serializable</code> This
 * entity represents the caracteristics of an Evenement and its getters/setters
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
@Table(name = "EVENT")
public class Evenement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EVENT_ID")
	private int event_id;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "PLACE")
	private String place;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME")
	private Date date;
	@Column(name = "LIMITE")
	private int limit;

	@ManyToOne(optional = false)
	@JoinColumn(name = "CINEPHILE_ID")
	private Cinephile cinephile;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SEANCE_ID")
	private Seance seance;

	@ManyToMany(mappedBy = "joined_events")
	private List<Cinephile> cinephiles;

	public Evenement() {

	}

	public Evenement(String title, String description, String place, Date date, int limit, Cinephile cinephile,
			Seance seance) {
		super();
		this.title = title;
		this.description = description;
		this.place = place;
		this.date = date;
		this.limit = limit;
		this.cinephile = cinephile;
		this.seance = seance;
	}

	public String getTitle() {
		return title;
	}

	public String getPlace() {
		return place;
	}

	public Date getDate() {
		return date;
	}

	public int getLimit() {
		return limit;
	}

	public Cinephile getCinephile() {
		return cinephile;
	}

	public List<Cinephile> getCinephiles() {
		return cinephiles;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setCinephile(Cinephile cinephile) {
		this.cinephile = cinephile;
	}

	public void setCinephiles(List<Cinephile> cinephiles, Cinephile cinephile) {
		this.cinephiles.add(cinephile);
	}

	public int getEvent_id() {
		return event_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

}
