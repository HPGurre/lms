package dk.itu.gsd.lms.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("O")
public class OrdinaryRoom extends AbstractRoom {

	@Override
	public String whatRoomAmI() {
		return "ORDINARY";
	}

}
