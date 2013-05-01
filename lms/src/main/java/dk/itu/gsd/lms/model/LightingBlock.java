package dk.itu.gsd.lms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "lightingblock")
public class LightingBlock extends HibernateModel implements Serializable {

	@Column(name = "name", unique = true)
	private String name;

	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightingblock_id")
	private List<AbstractRoom> rooms;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightingblock_id_policy")
	private LightPolicy lightPolicy;

	public LightPolicy getLightPolicy() {
		return lightPolicy;
	}

	public void setLightPolicy(LightPolicy lightPolicy) {
		this.lightPolicy = lightPolicy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<AbstractRoom> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "LightingBlock [name=" + name + ", rooms=" + rooms
				+ ", lightPolicy=" + lightPolicy + "]";
	}
}
