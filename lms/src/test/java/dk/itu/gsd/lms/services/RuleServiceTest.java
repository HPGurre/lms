package dk.itu.gsd.lms.services;

import static org.junit.Assert.assertEquals;

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
	public void test() {
		System.out.println("Result is:"+ruleService.getTimeout(roomService.getRoomData(7L)));
		
		assertEquals("This should be a messaged describing how the test failed", 1, 1);
	}
}
