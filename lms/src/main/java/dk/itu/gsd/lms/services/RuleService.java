package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.LuxRuleObject;
import dk.itu.gsd.lms.model.ScheduleRuleObject;
import dk.itu.gsd.lms.model.SecurityRuleObject;

public interface RuleService {

	public ScheduleRuleObject getRoomSchedulePolicy(AbstractRoom room);
	
	public LuxRuleObject getRoomLightingPolicy(AbstractRoom room, double currentLux);

	public SecurityRuleObject getRoomSecurityPolicy(AbstractRoom room);
}
