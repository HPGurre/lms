package dk.itu.gsd.lms.integration.consumed.building.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;

@Service("roomAdapter")
public class RoomAdapterImpl extends AbstractAdapter implements RoomAdapter {

	public void getDeviceEnergyUsageByDay(String deviceId) {
		String todayAsString = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		todayAsString = todayAsString +" 00:00";
		
		// The following URL is an example of what is constructed
		// http://gsd.itu.dk/api/user/measurement/?uuid=room-1-light-2-state
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("format", "json");
		params.add("timestamp__gte", todayAsString);
		//params.add("timestamp__lte", "Tommorrow...");
		params.add("limit", "10");
		params.add("uuid", deviceId);
		params.add("bid", LIGHTING_BID);
		
		String path = "measurement/";
		String response = resource.path(path).queryParams(params).get(String.class);
		System.out.println(response.toString());
		//return 2L;
	}
	
	@Override
	public void getDeviceEnergyUsageByMonth(Long roomId, String deviceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDeviceEnergyUsageByWeek(Long roomId, String deviceId) {
		// TODO Auto-generated method stub
		
	}
}
