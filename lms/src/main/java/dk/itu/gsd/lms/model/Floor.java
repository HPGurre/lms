package dk.itu.gsd.lms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "foreignkey_building_id")
	private Building building;
	
	@Column(name = "energy_usage_day", unique = true)
	private Float energyUsageLastDay;
	
	@Column(name = "energy_usage_month", unique = true)
	private Float energyUsageLastMonth;
	
	@Column(name = "energy_usage_week", unique = true)
	private Float energyUsageLastWeek;
	
	@Column(name = "foreign_floor_id", unique = true, nullable = false)
	private Long foreignFloorID;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "foreignkey_floor_id")
	private List<Room> rooms;

	public Building getBuilding() {
		return building;
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

	public Long getForeignFloorID() {
		return foreignFloorID;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setBuilding(Building building) {
		this.building = building;
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

	public void setForeignFloorID(Long foreignFloorID) {
		this.foreignFloorID = foreignFloorID;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
}
