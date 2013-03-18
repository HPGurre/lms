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

@Entity
@Table(name = "floor")
public class Floor extends HibernateModel {
	@Column(name = "security_mode")
	@Enumerated(EnumType.STRING)
	private SecurityMode securityMode;
	
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "foreign_floor_id")
	private List<Room> rooms;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "foreign_building_id")
	private Building building;
	
	
	@Column(name = "foreign_floor_id", unique = true, nullable = false)
	private Long foreignFloorID;
	
}
