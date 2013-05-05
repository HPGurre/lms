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
	@JoinColumn(name = "foreignkey_room_id")
	private List<Device> devices;
	
	@Override
	public String toString() {
		return "AbstractRoom [securityMode=" + securityMode + ", devices="
				+ devices + ", floor=" + floor + ", foreignRoomID="
				+ foreignRoomID + ", energyUsageLastDay=" + energyUsageLastDay
				+ ", energyUsageLastWeek=" + energyUsageLastWeek
				+ ", energyUsageLastMonth=" + energyUsageLastMonth + "]";
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "foreignkey_floor_id")
	private Floor floor;
	
	@Column(name = "foreign_room_id", unique = true, nullable = false)
	private Float foreignRoomID;
	
	@Column(name = "energy_usage_day", unique = true)
	private Float energyUsageLastDay;
	
	@Column(name = "energy_usage_week", unique = true)
	private Float energyUsageLastWeek;
	
	@Column(name = "energy_usage_month", unique = true)
	private Float energyUsageLastMonth;
	
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

	public Float getForeignRoomID() {
		return foreignRoomID;
	}

	public void setForeignRoomID(Float foreignRoomID) {
		this.foreignRoomID = foreignRoomID;
	}

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

	
}
