package dk.itu.gsd.lms.services.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.model.ScheduleRuleObject;
import dk.itu.gsd.lms.model.TimeRangeLabel;
import dk.itu.gsd.lms.services.RuleService;


@Service("ruleService")
public class RuleServiceImpl implements RuleService{

	@Autowired 
	private StatelessKnowledgeSession ksession;

	public int getTimeout() {
		ScheduleRuleObject sro = new ScheduleRuleObject();
		Calendar today = Calendar.getInstance(Locale.ENGLISH);
		
		sro.setDay("MONDAY");
		sro.setRoomType("BATHROOM");
		sro.setTimeRange(TimeRangeLabel.EVENING.name().toUpperCase());
		
		ksession.execute( Arrays.asList( new Object[]{sro} ) );
		// TODO Auto-generated method stub
		return 0;
	}

}
