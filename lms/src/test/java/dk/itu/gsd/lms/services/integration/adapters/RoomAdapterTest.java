package dk.itu.gsd.lms.services.integration.adapters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/application-context.xml" })
public class RoomAdapterTest {

	
	@Autowired
	private RoomAdapter roomAdapter;

	@Test
	public void test() {
		roomAdapter.getDeviceEnergyUsageByDay("room-1-light-2-production");
	}
}
