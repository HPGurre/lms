package dk.itu.gsd.lms.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "lightpolicy")
public class LightPolicy extends HibernateModel implements Serializable{
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightingblock_id")
	private LightingBlock lightingBlock;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "activitylevel_id")
	private ActivityLevel activityLevel;
	
	@Column(name = "min_lux")
	private int minLux;
	
	@Column(name = "max_lux")
	private int maxLux;
	
	@Column(name = "energyState")
	private EnergyState energyState;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightpolicy_id")
	private Schedule schedule;
	
}
