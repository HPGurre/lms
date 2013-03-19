package dk.itu.gsd.lms.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/application-context.xml" })
public class BuildingServiceTest {

	@Autowired
	private BuildingService buildingService;

	@Test
	public void test() {
		assertEquals("This should be a messaged escribing how the test failed", 1, 1);
	}
}
