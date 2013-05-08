package dk.itu.gsd.lms.services.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.model.Room;
import dk.itu.gsd.lms.model.EnergyState;
import dk.itu.gsd.lms.model.RuleLux;
import dk.itu.gsd.lms.model.RuleSchedule;
import dk.itu.gsd.lms.model.RuleSecurity;
import dk.itu.gsd.lms.model.TimeOfDay;
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

	public RuleSchedule getRoomSchedulePolicy(Room room) {
		Calendar now = Calendar.getInstance(Locale.ENGLISH);
		String strDateFormat = "EEEE";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat, Locale.ENGLISH);
		
		RuleSchedule sro = new RuleSchedule();
		sro.setDay(sdf.format(now.getTime()).toUpperCase());
		sro.setRoomType(room.whatRoomAmI());
		sro.setTimeRange(TimeOfDay.getTimeOfDayFromCalendar(now).toString());

		ksession.execute(Arrays.asList(new Object[] { sro }));

		return sro;
	}

	@Override
	public RuleLux getRoomLightingPolicy(Room room, double currentLight) {
		
		RuleLux lro = new RuleLux();
		lro.setCurrentLux(currentLight);
		lro.setRoomActivity(room.getActivityMode().getDisplayName().toUpperCase());
		lro.setEnergyState(EnergyState.ABUNDANT.getDisplayName().toUpperCase());//FIXME INSERT CORRECT ENERGYSTATE

		ksession1.execute(Arrays.asList(new Object[] { lro }));
		
		return lro;
	}
	
	@Override
	public RuleSecurity getRoomSecurityPolicy(Room room) {

		RuleSecurity sro = new RuleSecurity();
		sro.setRoomId(room.getForeignRoomID());
		sro.setTimeRange(TimeOfDay.getTimeOfDayFromCalendar(Calendar.getInstance(Locale.ENGLISH)).toString());

		ksession2.execute(Arrays.asList(new Object[] { sro }));
		
		return sro;
	}

}
