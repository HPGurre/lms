package dk.itu.gsd.lms.integration.consumed.building.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;
import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;

@Service("roomAdapter")
public class RoomAdapterImpl extends AbstractAdapter implements RoomAdapter {

	public List<MeasurementDto> getDeviceEnergyUsageByDay(String deviceId,
			String type) {
		String todayAsString = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(Calendar.getInstance().getTime());
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_YEAR, 1);
		String tomorrowAsString = new SimpleDateFormat("yyyy-MM-dd HH:mm")
		.format(tomorrow.getTime());
		
		System.out.println(todayAsString);
		System.out.println(tomorrowAsString);

		// The following URL is an example of what is constructed
		// http://gsd.itu.dk/api/user/measurement/?uuid=room-1-light-2-state
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("format", "json");
		// params.add("timestamp__gte", todayAsString);
		// params.add("timestamp__lte", "Tommorrow...");
		params.add("limit", "40");
		params.add("uuid", String.format("%s-%s", deviceId, type));
		// params.add("uuid", "room-1-light-2-gain");
		params.add("bid", LIGHTING_BID);

		String path = "measurement/";
		String response = resource.path(path).queryParams(params)
				.get(String.class);

		// describe this.
		List<MeasurementDto> result = new ArrayList<MeasurementDto>();
		Gson gson = new Gson();
		MeasurementDto obj = null;

		JsonParser parser = new JsonParser();
		JsonObject e = (JsonObject) parser.parse(response);

		JsonArray array = (JsonArray) e.get("objects");
		for (JsonElement jsonElement : array) {
			result.add(gson.fromJson(jsonElement, MeasurementDto.class));
		}

		return result;
	}

	@Override
	public void getDeviceEnergyUsageByMonth(Long roomId, String deviceId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void getDeviceEnergyUsageByWeek(Long roomId, String deviceId) {
		// TODO Auto-generated method stub
	}
	public List<MeasurementDto> getDeviceEnergyUsageByPeriod(String deviceId,
			String type, Calendar startDate, Calendar endDate) {
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		String startDateAsString = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(startDate.getTime());
		
		endDate.set(Calendar.HOUR_OF_DAY, 0);
		endDate.set(Calendar.MINUTE, 0);
		String endDateAsString = new SimpleDateFormat("yyyy-MM-dd HH:mm")
		.format(endDate.getTime());
		
		System.out.println(startDateAsString);
		System.out.println(endDateAsString);

		// The following URL is an example of what is constructed
		// http://gsd.itu.dk/api/user/measurement/?uuid=room-1-light-2-state
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("format", "json");
		params.add("timestamp__gte", startDateAsString); 
		params.add("timestamp__lt", endDateAsString);
		params.add("uuid", String.format("%s-%s", deviceId, type));
		params.add("bid", LIGHTING_BID);

		String path = "measurement/";
		String response = resource.path(path).queryParams(params)
				.get(String.class);

		List<MeasurementDto> result = new ArrayList<MeasurementDto>();
		Gson gson = new Gson();

		JsonParser parser = new JsonParser();
		JsonObject e = (JsonObject) parser.parse(response);

		JsonArray array = (JsonArray) e.get("objects");
		for (JsonElement jsonElement : array) {
			result.add(gson.fromJson(jsonElement, MeasurementDto.class));
		}

		return result;
	}
}
