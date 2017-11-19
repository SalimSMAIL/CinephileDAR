package Beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The class <code>Element</code> implements <code>Serializable</code> This
 * entity represents the caracteristics of an Element and its getters/setters
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
@Table(name = "ELEMENT")
public class Element implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ELEMENT_ID")
	private int element_id;
	@Enumerated(EnumType.ORDINAL)
	private TypeElement element_type;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "element", fetch = FetchType.LAZY)
	private List<Comment> element_comments;
	@ManyToMany(mappedBy = "elements")
	private List<ListCinephile> lists;

	public Element() {

	}

	public Element(int element_id, TypeElement element_type) {
		super();
		this.element_id = element_id;
		this.element_type = element_type;
	}

	public int getElement_id() {
		return element_id;
	}

	public TypeElement getElement_type() {
		return element_type;
	}

	public List<Comment> getElement_comments() {
		return element_comments;
	}

	public List<ListCinephile> getLists() {
		return lists;
	}

	public void setElement_type(TypeElement element_type) {
		this.element_type = element_type;
	}

	public void setElement_comments(List<Comment> element_comments) {
		this.element_comments = element_comments;
	}

	public void setLists(List<ListCinephile> lists) {
		this.lists = lists;
	}

}
