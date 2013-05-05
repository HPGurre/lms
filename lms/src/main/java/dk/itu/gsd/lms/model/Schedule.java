package dk.itu.gsd.lms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name = "schedule")
public class Schedule extends HibernateModel implements Serializable{
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@javax.persistence.ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "schedule_id")
	List<TimeRange> intervals;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightpolicy_id")
	private LightPolicy lightPolicy; 
	
//	
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@javax.persistence.ElementCollection
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id")
//	List<Calendar> holidays;
}
