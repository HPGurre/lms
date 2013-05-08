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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "building")
public class Building extends HibernateModel implements Serializable{
	
	@Column(name = "energy_state")
	@Enumerated(EnumType.STRING)
	private EnergyState energyState;
	
	@Column(name = "energy_usage_day", unique = true)
	private Float energyUsageLastDay;
	
	@Column(name = "energy_usage_month", unique = true)
	private Float energyUsageLastMonth;
	
	@Column(name = "energy_usage_week", unique = true)
	private Float energyUsageLastWeek;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "foreignkey_building_id")
	private List<Floor> floors;
	
	@Column(name = "foreign_building_id", unique = true, nullable = false)
	private Long foreignBuildingID;
	
	public Building(){
		setForeignBuildingID(1L);
		setEnergyState(EnergyState.ABUNDANT);
	}

	public EnergyState getEnergyState() {
		return energyState;
	}

	public Float getEnergyUsageLastDay() {
		return energyUsageLastDay;
	}

	public Float getEnergyUsageLastMonth() {
		return energyUsageLastMonth;
	}

	public Float getEnergyUsageLastWeek() {
		return energyUsageLastWeek;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public Long getForeignBuildingID() {
		return foreignBuildingID;
	}

	public void setEnergyState(EnergyState energyState) {
		this.energyState = energyState;
	}

	public void setEnergyUsageLastDay(Float energyUsageLastDay) {
		this.energyUsageLastDay = energyUsageLastDay;
	}

	public void setEnergyUsageLastMonth(Float energyUsageLastMonth) {
		this.energyUsageLastMonth = energyUsageLastMonth;
	}

	public void setEnergyUsageLastWeek(Float energyUsageLastWeek) {
		this.energyUsageLastWeek = energyUsageLastWeek;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public void setForeignBuildingID(Long foreignBuildingID) {
		this.foreignBuildingID = foreignBuildingID;
	}
}
