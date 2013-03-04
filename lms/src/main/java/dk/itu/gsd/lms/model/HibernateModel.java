package dk.itu.gsd.lms.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class HibernateModel implements Serializable {

	/**
	 * Hibernate version control number.
	 */
	@Version
	@Column(name = "hbm_version")
	private int hbmVersion;

	/**
	 * Persist id;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	/**
	 * Time of object creation
	 */
	@Column(name = "created")
	private Calendar created;

	/**
	 * Time of object update
	 */
	@Column(name = "updated")
	private Calendar updated;

	public HibernateModel() {
		this.created = Calendar.getInstance();
	}

	/**
	 * Gets the time of creation
	 * 
	 * @return created
	 */
	public Calendar getCreated() {
		return created;
	}

	/**
	 * Sets the time of creation
	 * 
	 * @param created the time of creation
	 */
	public void setCreated(Calendar created) {
		this.created = created;
	}

	/**
	 * Gets the time of the update
	 * 
	 * @return updated
	 */
	public Calendar getUpdated() {
		return Calendar.getInstance();
	}

	/**
	 * Sets the time of creation
	 * 
	 * @param updated the time of the update
	 */
	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

	/**
	 * Gets the hbmVersion.
	 * 
	 * @return the hbmVersion
	 */
	public int getHbmVersion() {
		return hbmVersion;
	}

	/**
	 * Sets the hbmVersion.
	 * 
	 * @param hbmVersion the hbmVersion to set
	 */
	public void setHbmVersion(int hbmVersion) {
		this.hbmVersion = hbmVersion;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
