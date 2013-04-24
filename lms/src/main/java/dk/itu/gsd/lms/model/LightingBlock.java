package dk.itu.gsd.lms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "Lightingblock")
public class LightingBlock extends HibernateModel implements Serializable{
	
	@Column(name = "name", unique= true)
	private String name;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightingblock_id")
	private List<AbstractRoom> rooms;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightingblock_id")
	private List<Floor> floors;
	
	@Column(name = "building")
	@JoinColumn(name = "lightingblock_id")
	private Building building;
	
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


	public List<Floor> getFloors() {
		return floors;
	}


	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}


	public Building getBuilding() {
		return building;
	}


	public void setBuilding(Building building) {
		this.building = building;
	}




	
	
	
	
	
	
//	public LightingBlock(List<AbstractRoom> rooms, List<Floor> floors, Building building) {
//		if (false/* Rooms are not adjacent */){//TODO Make the check
//			throw new IllegalArgumentException("Rooms are not adjacent");
//		}
//		if (false/* Rooms are not adjacent */){//TODO make the check
//			throw new IllegalArgumentException("Floors are not adjacent");
//		}
//		this.rooms = rooms;
//		this.floors = floors;
//		this.building = building;
//	}
}
