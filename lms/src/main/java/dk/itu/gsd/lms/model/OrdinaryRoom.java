package dk.itu.gsd.lms.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("O")
public class OrdinaryRoom extends AbstractRoom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
