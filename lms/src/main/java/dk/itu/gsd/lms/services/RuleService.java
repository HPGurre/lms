package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.Room;
import dk.itu.gsd.lms.model.RuleLux;
import dk.itu.gsd.lms.model.RuleSchedule;
import dk.itu.gsd.lms.model.RuleSecurity;

public interface RuleService {

	public RuleSchedule getRoomSchedulePolicy(Room room);
	
	public RuleLux getRoomLightingPolicy(Room room, double currentLux);

	public RuleSecurity getRoomSecurityPolicy(Room room);
}
