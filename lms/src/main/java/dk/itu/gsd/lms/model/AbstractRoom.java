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
public abstract class AbstractRoom extends HibernateModel implements Serializable{
	@Column(name = "security_mode")
	@Enumerated(EnumType.STRING)
	private SecurityMode securityMode;
	
	//TODO We need to decide if we want this.
	//private List<Room> adjecentRooms;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "foreign_room_id")
	private List<Device> devices;
	
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "foreign_floor_id")
	private Floor floor;
	
	@Column(name = "foreign_room_id", unique = true, nullable = false)
	private Long foreignRoomID;
	
	@Column(name = "energy_usage_day", unique = true, nullable = false)
	private double energyUsageLastDay;
	
	@Column(name = "energy_usage_week", unique = true, nullable = false)
	private double energyUsageLastWeek;
	
	public SecurityMode getSecurityMode() {
		return securityMode;
	}

	public void setSecurityMode(SecurityMode securityMode) {
		this.securityMode = securityMode;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Long getForeignRoomID() {
		return foreignRoomID;
	}

	public void setForeignRoomID(Long foreignRoomID) {
		this.foreignRoomID = foreignRoomID;
	}

	public double getEnergyUsageLastDay() {
		return energyUsageLastDay;
	}

	public void setEnergyUsageLastDay(double energyUsageLastDay) {
		this.energyUsageLastDay = energyUsageLastDay;
	}

	public double getEnergyUsageLastWeek() {
		return energyUsageLastWeek;
	}

	public void setEnergyUsageLastWeek(double energyUsageLastWeek) {
		this.energyUsageLastWeek = energyUsageLastWeek;
	}

	public double getEnergyUsageLastMonth() {
		return energyUsageLastMonth;
	}

	public void setEnergyUsageLastMonth(double energyUsageLastMonth) {
		this.energyUsageLastMonth = energyUsageLastMonth;
	}

	@Column(name = "energy_usage_month", unique = true, nullable = false)
	private double energyUsageLastMonth;
}
