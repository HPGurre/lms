package dk.itu.gsd.lms.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("O")
public class OrdinaryRoom extends Room {
	private static final long serialVersionUID = 2872206990743675505L;

	@Override
	public String whatRoomAmI() {
		return "ORDINARY";
	}

}
