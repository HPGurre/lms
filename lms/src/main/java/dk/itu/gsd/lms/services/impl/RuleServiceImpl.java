package dk.itu.gsd.lms.services.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.EnergyState;
import dk.itu.gsd.lms.model.LuxRuleObject;
import dk.itu.gsd.lms.model.ScheduleRuleObject;
import dk.itu.gsd.lms.model.SecurityRuleObject;
import dk.itu.gsd.lms.model.TimeRangeLabel;
import dk.itu.gsd.lms.services.RoomService;
import dk.itu.gsd.lms.services.RuleService;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private StatelessKnowledgeSession ksession;
	
	@Autowired
	private StatelessKnowledgeSession ksession1;
	
	@Autowired
	private StatelessKnowledgeSession ksession2;

	public ScheduleRuleObject getRoomSchedulePolicy(AbstractRoom room) {
		Calendar now = Calendar.getInstance(Locale.ENGLISH);
		String strDateFormat = "EEEE";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat, Locale.ENGLISH);
		
		ScheduleRuleObject sro = new ScheduleRuleObject();
		sro.setDay(sdf.format(now.getTime()).toUpperCase());
		sro.setRoomType(room.whatRoomAmI());
		sro.setTimeRange(TimeRangeLabel.getLabelFromCalendar(now).toString());

		ksession.execute(Arrays.asList(new Object[] { sro }));

		return sro;
	}

	@Override
	public LuxRuleObject getRoomLightingPolicy(AbstractRoom room, double currentLight) {
		
		LuxRuleObject lro = new LuxRuleObject();
		lro.setCurrentLux(currentLight);
		lro.setRoomActivity(room.getActivityMode().getDisplayName().toUpperCase());
		lro.setEnergyState(EnergyState.ABUNDANT.getDisplayName().toUpperCase());//FIXME INSERT CORRECT ENERGYSTATE

		ksession1.execute(Arrays.asList(new Object[] { lro }));
		
		return lro;
	}
	
	@Override
	public SecurityRuleObject getRoomSecurityPolicy(AbstractRoom room) {

		SecurityRuleObject sro = new SecurityRuleObject();
		sro.setRoomId(room.getForeignRoomID());
		sro.setTimeRange(TimeRangeLabel.getLabelFromCalendar(Calendar.getInstance(Locale.ENGLISH)).toString());

		ksession2.execute(Arrays.asList(new Object[] { sro }));
		
		return sro;
	}

}
