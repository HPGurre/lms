package dk.itu.gsd.lms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@XmlRootElement
@Table(name = "lightpolicy")
public class LightPolicy extends HibernateModel implements Serializable{
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightingblock_id_policy")
	private LightingBlock lightingBlock;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightpolicy_id")
	private ActivityLevel activiLevel;
	
	@Column(name = "min_Lux")
	private int minLux;
	
	@Column(name = "Mac_lux")
	private int maxLux;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lightpolicy_id")
	private Schedule schedule; 

}
