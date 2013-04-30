package dk.itu.gsd.lms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name = "floor")
public class Floor extends HibernateModel {
	@Column(name = "security_mode")
	@Enumerated(EnumType.STRING)
	private SecurityMode securityMode;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "foreign_floor_id")
	private List<AbstractRoom> rooms;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "foreign_building_id")
	private Building building;
	
	@Column(name = "energy_usage_day", unique = true)
	private Float energyUsageLastDay;
	
	@Column(name = "energy_usage_week", unique = true)
	private Float energyUsageLastWeek;
	
	@Column(name = "energy_usage_month", unique = true)
	private Float energyUsageLastMonth;
	
	public Float getEnergyUsageLastDay() {
		return energyUsageLastDay;
	}

	public void setEnergyUsageLastDay(Float energyUsageLastDay) {
		this.energyUsageLastDay = energyUsageLastDay;
	}

	public Float getEnergyUsageLastWeek() {
		return energyUsageLastWeek;
	}

	public void setEnergyUsageLastWeek(Float energyUsageLastWeek) {
		this.energyUsageLastWeek = energyUsageLastWeek;
	}

	public Float getEnergyUsageLastMonth() {
		return energyUsageLastMonth;
	}

	public void setEnergyUsageLastMonth(Float energyUsageLastMonth) {
		this.energyUsageLastMonth = energyUsageLastMonth;
	}

	@Column(name = "foreign_floor_id", unique = true, nullable = false)
	private Long foreignFloorID;

	public SecurityMode getSecurityMode() {
		return securityMode;
	}

	public void setSecurityMode(SecurityMode securityMode) {
		this.securityMode = securityMode;
	}

	public List<AbstractRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<AbstractRoom> rooms) {
		this.rooms = rooms;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Long getForeignFloorID() {
		return foreignFloorID;
	}

	public void setForeignFloorID(Long foreignFloorID) {
		this.foreignFloorID = foreignFloorID;
	}
	
}
