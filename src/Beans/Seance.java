package Beans;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The class <code>Seance</code> implements <code>Serializable</code> This
 * entity represents the caracteristics of a Seance and its getters/setters
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
@Table(name = "SEANCE")
public class Seance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_SEANCE")
	private int id_seance;
	@Column(name = "MOVIE")
	private String movie;
	@Column(name = "AFFICHE")
	private String affiche;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME")
	private Date datetime_seance;
	@Column(name = "PLACE")
	private String place;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seance", fetch = FetchType.LAZY)
	private List<Evenement> events;

	public int getId_seance() {
		return id_seance;
	}

	public String getMovie() {
		return movie;
	}

	public String getAffiche() {
		return affiche;
	}

	public Date getDatetime_seance() {
		return datetime_seance;
	}

	public String getPlace() {
		return place;
	}

	public List<Evenement> getEvents() {
		return events;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}

	public void setDatetime_seance(Date datetime_seance) {
		this.datetime_seance = datetime_seance;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setEvents(List<Evenement> events) {
		this.events = events;
	}

}
