package dk.itu.gsd.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name = "device")
@XmlRootElement
public class Device extends HibernateModel implements Serializable{

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "foreign_room_id")
	private AbstractRoom room;
	
	@Column(name = "foreign_device_id", unique = true, nullable = false)
	private String foreignDeviceId;

	public String getForeignDeviceId() {
		return foreignDeviceId;
	}

	public void setForeignDeviceId(String foreignDeviceId) {
		this.foreignDeviceId = foreignDeviceId;
	}

}
