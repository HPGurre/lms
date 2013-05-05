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
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.Device;

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
		// params.add("timestamp__lte", "Tomorrow...");
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
		
		System.out.println("START " + startDateAsString);
		System.out.println("END " + endDateAsString);

		// The following URL is an example of what is constructed
		// http://gsd.itu.dk/api/user/measurement/?uuid=room-1-light-2-state
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("format", "json");
		params.add("timestamp__gte", startDateAsString); 
		params.add("timestamp__lt", endDateAsString);
		params.add("limit", "178560");  //max 31 days if every 15 seconds!
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
	
	//return measurements by number of measurements
	public List<MeasurementDto> getDeviceEnergyUsageByNumber(String deviceId,
			String type, Calendar startDate, int noOfMeasurements) {
		
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		String startDateAsString = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(startDate.getTime());
		System.out.println("START " + startDateAsString);
		// The following URL is an example of what is constructed
		// http://gsd.itu.dk/api/user/measurement/?uuid=room-1-light-2-state
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("format", "json");
		params.add("timestamp__gte", startDateAsString); 
		//params.add("timestamp__lt", endDateAsString);
		params.add("limit", String.format("%s", noOfMeasurements+1));
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
	
	//return the power requirements for each lamp in the room in order to 
	//Fulfil minimum lux level in the room
	public float getLampMinPower(AbstractRoom room) {
		float minPower = 0.0f;  //return value
		float minLampLum = 0.0f; //minimum luminance supplied by lamps
		float lmSun = 0.0f;	//luminance of sun
		float sun = 0.0f;	//value from sun model
		float cloud = 0.0f;	//value from cloud model
		float minRoomLum = 0.0f; // minimum luminance in room based on policy model (lumens [lm])
		float windowSize = 0.0f; //size of windows in room
		float blindState = 0.0f; //state of window blinds
		
		// set luminous efficacy
		//source: http://www.rapidtables.com/calc/light/how-lux-to-watt.htm
//		Light type	Typical	luminous efficacy(lumens/watt)
//		Tungsten incandescent light bulb	12.5-17.5 lm/W
//		Halogen lamp	16-24 lm/W
//		Fluorescent lamp	45-75 lm/W
//		LED lamp	30-90 lm/W
//		Metal halide lamp	75-100 lm/W
//		High pressure sodium vapor lamp	85-150 lm/W
//		Low pressure sodium vapor lamp	100-200 lm/W
//		Mercury vapor lamp	35-65 lm/W
		float efficacyLamp = 45f; //efficacy of lamps in room [lm/W]
		
		//assume no blinds
				
		//retrieve value of sun model
		
		//retrieve value of cloud model
		
		for (Device device : room.getDevices()) { // loop over all devices in
			// room
			
			String id = device.getForeignDeviceId();

			if (id.contains("blind")) {
						
				//get window size
				windowSize = 5.0f;
				
				//retrieve value of blind state
				blindState = 1.0f;
				
				//update luminance from sun/cloud model through window
				lmSun = lmSun + blindState * windowSize * sun * cloud; //luminous flux from windows (lm)
			}

		}
		
		//retrieve minimum luminous flux (lumens) level required by policy model
		minRoomLum = 1.0f;
		
		//calculate minimum luminous flux (lumens) to be supplied by lamps
		minLampLum = minRoomLum - lmSun;
		
		//convert from luminous flux (lumens) to power
		minPower =  minLampLum / efficacyLamp;
		
		//return minimum power required by all lamps in the room in W
		return minPower;
	}
}
