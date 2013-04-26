package dk.itu.gsd.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "timeranges")
public class TimeRange extends HibernateModel implements Serializable{
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;
	
	@Column(name = "day_of_week")
	private int dayOfWeek;
	
	@Column(name = "begin")
	private int begin;
	
	@Column(name = "end")
	private int end;
	
	@Column(name = "time_range_label")
	@Enumerated(EnumType.STRING)
	private TimeRangeLabel timeRangeLabel;
	
}
