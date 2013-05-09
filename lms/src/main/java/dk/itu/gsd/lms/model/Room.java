package dk.itu.gsd.lms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Inheritance
@DiscriminatorColumn(name="Room_type")
@Table(name = "room")
public abstract class Room extends HibernateModel implements Serializable{
	
	@Column(name = "activity_mode")
	@Enumerated(EnumType.STRING)
	private ActivityMode activityMode;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "foreignkey_room_id")
	private List<Device> devices; 
	
	@Column(name = "energy_usage_day", unique = true)
	private Float energyUsageLastDay;

	@Column(name = "energy_usage_month", unique = true)
	private Float energyUsageLastMonth;

	@Column(name = "energy_usage_week", unique = true)
	private Float energyUsageLastWeek;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "foreignkey_floor_id")
	private Floor floor;

	@Column(name = "foreign_room_id", unique = true, nullable = false)
	private String foreignRoomID;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "lightingblock_id")
	private LightingBlock lightingblock;
	
	public ActivityMode getActivityMode() {
		return activityMode;
	}

	public List<Device> getDevices() {
		return devices;
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
	
	public Floor getFloor() {
		return floor;
	}
	
	public String getForeignRoomID() {
		return foreignRoomID;
	}
	
	public LightingBlock getLightingblock() {
		return lightingblock;
	}

	public void setActivityMode(ActivityMode activityMode) {
		this.activityMode = activityMode;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
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

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public void setForeignRoomID(String foreignRoomID) {
		this.foreignRoomID = foreignRoomID;
	}

	public void setLightingblock(LightingBlock lightingblock) {
		this.lightingblock = lightingblock;
	}

	public abstract String whatRoomAmI();

	
}
