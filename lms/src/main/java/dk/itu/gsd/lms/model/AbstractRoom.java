package dk.itu.gsd.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
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
	
	//private List<Room> adjecentRooms;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "foreign_floor_id")
	private Floor floor;
	
	@Column(name = "foreign_room_id", unique = true, nullable = false)
	private Long foreignRoomID;

}
