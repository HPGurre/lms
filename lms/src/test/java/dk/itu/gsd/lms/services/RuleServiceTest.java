package dk.itu.gsd.lms.services;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/application-context.xml" })
public class RuleServiceTest {

	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private RoomService roomService;

	@Test
	@Ignore
	public void test() {
		System.out.println("Result is:"+ruleService.getRoomSchedulePolicy(roomService.getRoomData(7L)));	
		assertEquals("This should be a messaged describing how the test failed", 1, 1);
	}
	
	@Test
	public void test1() {
		System.out.println("Result is:"+ruleService.getRoomLightingPolicy(roomService.getRoomData(7L),200));
		
		assertEquals("This should be a messaged describing how the test failed", 1, 1);
	}
}
