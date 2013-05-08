package dk.itu.gsd.lms.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/application-context.xml" })
public class DeviceServiceTest {

	@Autowired
	private DeviceService deviceService;

	@Test
	public void test() {
		assertEquals("This should be a messaged describing how the test failed", 1, 1);
		Boolean result = deviceService.adjustLight("room-1-light-2", 0.777f);
		assertEquals("failed to set the light", true, result);
	}
	@Test(expected=IllegalArgumentException.class)
	public void test1() {
		assertEquals("This should be a messaged describing how the test failed", 1, 1);
		deviceService.adjustLight("room-1-blind-2", 0.777f);
		fail("A exception should have been thrown");
	}
}
