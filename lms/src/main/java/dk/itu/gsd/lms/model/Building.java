package dk.itu.gsd.lms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "building")
public class Building extends HibernateModel implements Serializable{
	@Column(name = "security_mode")
	@Enumerated(EnumType.STRING)
	private SecurityMode securityMode;
	
	@Column(name = "energy_state")
	@Enumerated(EnumType.STRING)
	private EnergyState energyState;
	
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "foreign_building_id")
	private List<Floor> floors;
	
	@Column(name = "foreign_building_id", unique = true, nullable = false)
	private Long foreignBuildingID;
	
	public Building(){
		setForeignBuildingID(1L);
		energyState = EnergyState.ABUNDANT;
	}

	public Long getForeignBuildingID() {
		return foreignBuildingID;
	}

	public void setForeignBuildingID(Long foreignBuildingID) {
		this.foreignBuildingID = foreignBuildingID;
	}
}
