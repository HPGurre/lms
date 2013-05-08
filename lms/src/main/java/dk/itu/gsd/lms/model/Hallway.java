package dk.itu.gsd.lms.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("H")
public class Hallway extends Room {

	@Override
	public String whatRoomAmI() {
		return "HALLWAY";
	}

}
