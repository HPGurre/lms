package dk.itu.gsd.lms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Activity_Level")
public class ActivityLevel extends HibernateModel{
	
	
	@Column(name="min_act")
	private int minAct;
	
	@Column(name=("max_act"))
	private int maxAct;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightpolicy_id_act")
	private LightPolicy lightPolicy;
	

}
