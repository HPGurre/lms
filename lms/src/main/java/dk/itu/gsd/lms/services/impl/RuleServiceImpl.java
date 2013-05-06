package dk.itu.gsd.lms.services.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.ScheduleRuleObject;
import dk.itu.gsd.lms.model.TimeRangeLabel;
import dk.itu.gsd.lms.services.RoomService;
import dk.itu.gsd.lms.services.RuleService;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {
	
	@Autowired
	private RoomService roomService;

	@Autowired
	private StatelessKnowledgeSession ksession;

	public int getTimeout(AbstractRoom room) {
		Calendar now = Calendar.getInstance(Locale.ENGLISH);
		String strDateFormat = "EEEE";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat, Locale.ENGLISH);

		ScheduleRuleObject sro = new ScheduleRuleObject();
		sro.setDay(sdf.format(now.getTime()).toUpperCase());
		sro.setRoomType(room.whatRoomAmI());
		sro.setTimeRange(TimeRangeLabel.getLabelFromCalendar(now).toString());

		ksession.execute(Arrays.asList(new Object[] { sro }));
		//roomService.getRoomData(1l).get
		return sro.getTimeoutLimit();
	}


}
