package dk.itu.gsd.lms.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;
import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.Device;
import dk.itu.gsd.lms.model.EnergyState;
import dk.itu.gsd.lms.services.DeviceService;
import dk.itu.gsd.lms.services.RoomService;

/**
 * gain * wattage = value in watt
 * 
 * @author HP
 * 
 */



@Service("roomService")
public class RoomServiceImpl implements RoomService {
	public static int LIGHTING_WATTAGE = 60;

	@Autowired
	RoomAdapter roomAdapter;
	
	@Autowired
	private DeviceService deviceService;

	@Autowired
	RoomDao roomDao;

	private static Long convertTimestampStringToSeconds(String timestamp) {
		Long result = 0l;
		String format = "yyyy-MM-dd'T'HH:mm:ss";
		try {
			result = new SimpleDateFormat(format).parse(timestamp).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result / 1000;
	}

	@Override
	public Float getEnergyUsageByDay(AbstractRoom room) {
		Float roomEnergyUsage = 0.0f;
		Long time = (long) 0;
		Long time2 = (long) 0;
		Long duration = (long) 0;
		Calendar today = Calendar.getInstance();
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_YEAR, -1);
		
		// look up room devices.
		for (Device device : room.getDevices()) { // loop over all devices in
													// room
			Float deviceEnergyUsage = 0.0f;
			String id = device.getForeignDeviceId();

			if (id.contains("light")) {
				List<MeasurementDto> measurements = roomAdapter.getDeviceEnergyUsageByPeriod(id, "gain",tomorrow, today);
				for (MeasurementDto measurementDto : measurements) { // loop
																		// over
																		// all
																		// measurements
																		// (15s)
																		// during
																		// the
																		// day
					if (measurements.indexOf(measurementDto) < measurements.size() - 1) {
						time = convertTimestampStringToSeconds(measurementDto.getTimestamp());
						time2 = convertTimestampStringToSeconds(measurements.get(measurements.indexOf(measurementDto) + 1).getTimestamp());
						duration = time2 - time;
						deviceEnergyUsage += Float.parseFloat(measurementDto.getValue()) * LIGHTING_WATTAGE * duration; // calculate
																														// energy
																														// used
																														// during
																														// 15s
																														// for
																														// a
																														// single
																														// device
						//System.out.println("(D) Energy used by device " + id + " in time " + measurementDto.getTimestamp() + " t=" + time + ": gain = "
						//		+ measurementDto.getValue() + " Energy = " + deviceEnergyUsage + " Ws" + " in " + duration + " seconds");
					}
				}
			}
			roomEnergyUsage += deviceEnergyUsage;
		}
		return roomEnergyUsage / 1000 / 3600; // this is in kWh
	}

	@Override
	public Float getEnergyUsageByWeek(AbstractRoom room) {
		Float roomEnergyUsage = 0.0f;
		Long time = (long) 0;
		Long time2 = (long) 0;
		Long duration = (long) 0;
		Calendar today = Calendar.getInstance();
		Calendar sevenDaysAgo = Calendar.getInstance();
		sevenDaysAgo.add(Calendar.DAY_OF_YEAR, -7);
		System.out.println("date: " + today.getTime() + "  " + sevenDaysAgo.getTime());
		// look up room devices.
		for (Device device : room.getDevices()) { // loop over all devices in
													// room
			Float deviceEnergyUsage = 0.0f;
			String id = device.getForeignDeviceId();

			if (id.contains("light")) {
				List<MeasurementDto> measurements = roomAdapter.getDeviceEnergyUsageByPeriod(id, "gain", sevenDaysAgo ,today);
				//List<MeasurementDto> measurements = roomAdapter.getDeviceEnergyUsageByNumber(id, "gain", sevenDaysAgo, 27);
				for (MeasurementDto measurementDto : measurements) { // loop
																		// over
																		// all
																		// measurements
																		// (15s)
																		// during
																		// the
																		// day
					if (measurements.indexOf(measurementDto) < measurements.size() - 1) {
						time = convertTimestampStringToSeconds(measurementDto.getTimestamp());
						time2 = convertTimestampStringToSeconds(measurements.get(measurements.indexOf(measurementDto) + 1).getTimestamp());
						duration = time2 - time;
						deviceEnergyUsage += Float.parseFloat(measurementDto.getValue()) * LIGHTING_WATTAGE * duration; // calculate
																														// energy
																														// used
																														// during
																														// 15s
																														// for
																														// a
																														// single
																														// device
						//System.out.println("(W) Energy used by device " + id + " in time " + measurementDto.getTimestamp() + " t=" + time + ": gain = "
						//		+ measurementDto.getValue() + " Energy = " + deviceEnergyUsage + " Ws" + " in " + duration + " seconds");
					}
				}
			}
			roomEnergyUsage += deviceEnergyUsage;
		}
		return (roomEnergyUsage / 1000 / 3600); // this is in kWh
	}

	@Override
	public Float getEnergyUsageByMonth(AbstractRoom room) {
		Float roomEnergyUsage = 0.0f;
		Long time = (long) 0;
		Long time2 = (long) 0;
		Long duration = (long) 0;
		Calendar today = Calendar.getInstance();
		Calendar firstDayofMonth = Calendar.getInstance();
		firstDayofMonth.add(Calendar.DAY_OF_MONTH, -30);
		// look up room devices.
		for (Device device : room.getDevices()) { // loop over all devices in
													// room
			Float deviceEnergyUsage = 0.0f;
			String id = device.getForeignDeviceId();

			if (id.contains("light")) {
				List<MeasurementDto> measurements = roomAdapter.getDeviceEnergyUsageByPeriod(id, "gain", firstDayofMonth ,today);
				for (MeasurementDto measurementDto : measurements) { // loop
																		// over
																		// all
																		// measurements
																		// (15s)
																		// during
																		// the
																		// day
					
					if (measurements.indexOf(measurementDto) < measurements.size() - 1) {
						time = convertTimestampStringToSeconds(measurementDto.getTimestamp());
						time2 = convertTimestampStringToSeconds(measurements.get(measurements.indexOf(measurementDto) + 1).getTimestamp());
						duration = time2 - time;
						deviceEnergyUsage += Float.parseFloat(measurementDto.getValue()) * LIGHTING_WATTAGE * duration; // calculate
																														// energy
																														// used
																														// during
																														// 15s
																														// for
																														// a
																														// single
																														// device
						//System.out.println("(M) Energy used by device " + id + " in time " + measurementDto.getTimestamp() + " t=" + time + ": gain = "
						//		+ measurementDto.getValue() + " Energy = " + deviceEnergyUsage + " Ws" + " in " + duration + " seconds");
					}
				}
			}
			roomEnergyUsage += deviceEnergyUsage;
		}
		return (roomEnergyUsage / 1000 / 3600); // this is in kWh //FIXME is it always 30.
	}
	
	public int getActivityLevel(AbstractRoom room) {
		int activityLevel = -1;

		Calendar now = Calendar.getInstance();
		Calendar fiveMinsAgo = Calendar.getInstance();
		fiveMinsAgo.add(Calendar.MINUTE, -5);
		// look up room devices.
		for (Device device : room.getDevices()) { // loop over all devices in
													// room
			String id = device.getForeignDeviceId();

			//if (id.contains("light")) {
			List<MeasurementDto> measurements = roomAdapter.getDeviceEnergyUsageByPeriod(id, "gain", fiveMinsAgo ,now);
				
			if(activityLevel < 1 && measurements.size() == 0) {activityLevel = 0;}
			else {activityLevel = 1;}
		}
				
		return activityLevel; // integer indicating activity in room = 0 if no activity.
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
		int noOfLamps = 0;		//number of lamps in room
		EnergyState energyState = EnergyState.NORMAL;
		int activityLevel = 0; 	//activity level of room
		int lightingBlockId = 0; //lighting block id
		
		Calendar now = Calendar.getInstance();

		
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
						
				//get window size from building simulator
				windowSize = 5.0f;
				
				//retrieve sun model from building simulator
				sun = 1.0f;
				
				//retrieve cloud model from building simulator
				cloud = 0.2f;
				
				//retrieve value of blind state
				List<MeasurementDto> measurements = roomAdapter.getDeviceEnergyUsageByNumber(id, "setpoint", now, 1);
				blindState = Float.parseFloat(measurements.get(0).getValue());
				System.out.println("Blind state = " + blindState);
				
				
				
				//update luminance from sun/cloud model through window
				lmSun = lmSun + blindState * windowSize * sun * (1f-cloud); //luminous flux from windows (lm)
			}

		}
		
		//get number of lamps in room
		for (Device device : room.getDevices()) { // loop over all devices in room
			String id = device.getForeignDeviceId();
			if (id.contains("light")) {
				noOfLamps = noOfLamps+1;
			}
		}
		
		//retrieve minimum luminous flux (lumens) level required by policy model
		// first, get energy state of building
		energyState = EnergyState.NORMAL;
		//then, get activity level
		activityLevel = this.getActivityLevel(room);
		//and get lighting block id
		lightingBlockId = 1;
		// finally, look up minimum luminance value in table
		minRoomLum = 300.0f;
		
		//calculate minimum luminous flux (lumens) to be supplied by each lamp
		minLampLum = Math.max(0f, minRoomLum - lmSun) / noOfLamps;
		
		//convert from luminous flux (lumens) to power (W) 
		minPower =  minLampLum / efficacyLamp;
		
		System.out.println("no of lamps = "+ noOfLamps);
		System.out.println("luminousity of sun-cloud-blinds = "+ lmSun);
		
		//return minimum power required by each lamp in the room in W
		return minPower;
	}
	
	//Toggle state of lamps to fulfil minimum lux level in the room
	public void setLightsAccordingToPolicy(AbstractRoom room) {
		int wattage = 0;
		float gain = 0f;
		float minPower = 0f;
		
//		Calendar now = Calendar.getInstance();
		
		minPower = this.getLampMinPower(room);
		//
		for (Device device : room.getDevices()) { // loop over all devices in room
			String id = device.getForeignDeviceId();
			if (id.contains("light")) {
				//retrieve value of max power (wattage)
				wattage = 60;
				gain = Math.min(1f ,minPower/wattage) ;
				System.out.println("Lamp state = " + gain);
				//change state of lamp
				deviceService.toggleLight(id, gain);
/**				
** Retrieving measured after set the state: Not working yet.
** My guess is duration of time. currently implemented as retrieving right after the state is changed.
*/				
//				List<MeasurementDto> measurements = roomAdapter.getDeviceEnergyUsageByNumber(id, "gain", now,1);
//				float lampstate = Float.parseFloat(measurements.get(0).getValue());
//				System.out.println("Measured Lamp state = " + lampstate);
			}
		}
		
	}

	@Override
	public AbstractRoom getRoomData(Long roomId) {
		return roomDao.find(roomId);
	}

	@Override
	public List<AbstractRoom> findAllRooms() {
		return roomDao.findAll();
	}

}
