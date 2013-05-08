package dk.itu.gsd.lms.services.integration.adapters;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.itu.gsd.lms.integration.consumed.building.DeviceAdapter;
import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/application-context.xml" })
public class RoomAdapterTest {

	
	@Autowired
	private DeviceAdapter deviceAdapter;

	@Test
	public void test() {
		Calendar t = Calendar.getInstance();
		t.add(Calendar.DAY_OF_YEAR, 3);
		List<MeasurementDto> result = deviceAdapter.getDeviceEnergyUsageByPeriod("room-1-light-2", "production", Calendar.getInstance(), t );
	System.out.println("SIZE:"+result.size());
	}
}
