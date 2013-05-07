package dk.itu.gsd.lms.services;

import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.LuxRuleObject;

public interface RuleService {

	public int getRoomTimeout(AbstractRoom room);
	
	public LuxRuleObject getRoomRecommendedLux(AbstractRoom room);
}
