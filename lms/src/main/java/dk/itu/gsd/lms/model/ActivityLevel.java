package dk.itu.gsd.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Activity_Level")
public class ActivityLevel extends HibernateModel implements Serializable{
	
	
	@Column(name="min_act")
	private int minAct;
	
	@Column(name=("max_act"))
	private int maxAct;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "lightpolicy_id_act")
//	private LightPolicy lightPolicy;
//	

}
