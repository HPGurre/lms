package dk.itu.gsd.lms.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("B")
public class Bathroom extends AbstractRoom {

	@Override
	public String whatRoomAmI() {
		return "Bathroom";
	}

}
