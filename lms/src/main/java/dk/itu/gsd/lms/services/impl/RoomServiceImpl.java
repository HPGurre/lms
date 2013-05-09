package dk.itu.gsd.lms.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.BuildingDao;
import dk.itu.gsd.lms.dao.FloorDao;
import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.integration.consumed.building.DeviceAdapter;
import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;
import dk.itu.gsd.lms.integration.consumed.building.model.MeasurementDto;
import dk.itu.gsd.lms.model.EnergyState;
import dk.itu.gsd.lms.model.Room;
import dk.itu.gsd.lms.model.Device;
import dk.itu.gsd.lms.model.RuleLux;
import dk.itu.gsd.lms.model.RuleSchedule;
import dk.itu.gsd.lms.model.RuleSecurity;
import dk.itu.gsd.lms.services.DeviceService;
import dk.itu.gsd.lms.services.RoomService;
import dk.itu.gsd.lms.services.RuleService;


/**
 * gain * wattage = value in watt
 * 
 */

@Service("roomService")
public class RoomServiceImpl implements RoomService {
	private static Logger logger = Logger.getLogger(RoomServiceImpl.class);
	public static int LIGHTING_WATTAGE = 60;

	@Autowired private RoomAdapter roomAdapter;
	@Autowired private DeviceAdapter deviceAdapter;
	@Autowired private DeviceService deviceService;
	@Autowired private RoomDao roomDao;
	@Autowired private FloorDao floorDao;
	@Autowired private BuildingDao buildingDao;
	@Autowired private RuleService ruleService;

	@Override
	public Float getEnergyUsageByDay(Room room) {
		Float roomEnergyUsage = 0.0f;
		Long time = (long) 0;
		Long time2 = (long) 0;
		Long duration = (long) 0;
		Calendar today = Calendar.getInstance();
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_YEAR, -1);

		// look up room devices.
		for (Device device : room.getDevices()) { 
			// loop over all devices in room
			Float deviceEnergyUsage = 0.0f;
			String id = device.getForeignDeviceId();

			if (id.contains("light")) {
				List<MeasurementDto> measurements = deviceAdapter.getDeviceEnergyUsageByPeriod(id, "gain", tomorrow, today);
				for (MeasurementDto measurementDto : measurements) { 
					
					// loop overall measurements(15s) during the day
					if (measurements.indexOf(measurementDto) < measurements.size() - 1) {
						time = convertTimestampStringToSeconds(measurementDto.getTimestamp());
						time2 = convertTimestampStringToSeconds(measurements.get(measurements.indexOf(measurementDto) + 1).getTimestamp());
						duration = time2 - time;
						deviceEnergyUsage += Float.parseFloat(measurementDto.getValue()) * LIGHTING_WATTAGE * duration; 
						
						// calculate energy used during 15s for a single device
						// System.out.println("(D) Energy used by device " + id
						// + " in time " + measurementDto.getTimestamp() + " t="
						// + time + ": gain = "
						// + measurementDto.getValue() + " Energy = " +
						// deviceEnergyUsage + " Ws" + " in " + duration +
						// " seconds");
					}
				}
			}
			roomEnergyUsage += deviceEnergyUsage;
		}
		return roomEnergyUsage / 1000 / 3600; // this is in kWh
	}

	@Override
	public Float getEnergyUsageByWeek(Room room) {
		Float roomEnergyUsage = 0.0f;
		Long time = (long) 0;
		Long time2 = (long) 0;
		Long duration = (long) 0;
		Calendar today = Calendar.getInstance();
		Calendar sevenDaysAgo = Calendar.getInstance();
		sevenDaysAgo.add(Calendar.DAY_OF_YEAR, -7);

		// look up room devices.
		for (Device device : room.getDevices()) { // loop over all devices in
													// room
			Float deviceEnergyUsage = 0.0f;
			String id = device.getForeignDeviceId();

			if (id.contains("light")) {
				List<MeasurementDto> measurements = deviceAdapter.getDeviceEnergyUsageByPeriod(id, "gain", sevenDaysAgo, today);
				// List<MeasurementDto> measurements =
				// roomAdapter.getDeviceEnergyUsageByNumber(id, "gain",
				// sevenDaysAgo, 27);
				for (MeasurementDto measurementDto : measurements) { 
					// loop over all measurements (15s) during the day
					if (measurements.indexOf(measurementDto) < measurements.size() - 1) {
						time = convertTimestampStringToSeconds(measurementDto.getTimestamp());
						time2 = convertTimestampStringToSeconds(measurements.get(measurements.indexOf(measurementDto) + 1).getTimestamp());
						duration = time2 - time;
						deviceEnergyUsage += Float.parseFloat(measurementDto.getValue()) * LIGHTING_WATTAGE * duration; 
						
						
						// calculate energy used during 15s for a single device
						// System.out.println("(W) Energy used by device " + id
						// + " in time " + measurementDto.getTimestamp() + " t="
						// + time + ": gain = "
						// + measurementDto.getValue() + " Energy = " +
						// deviceEnergyUsage + " Ws" + " in " + duration +
						// " seconds");
					}
				}
			}
			roomEnergyUsage += deviceEnergyUsage;
		}
		return (roomEnergyUsage / 1000 / 3600); // this is in kWh
	}

	@Override
	public Float getEnergyUsageByMonth(Room room) {
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
				List<MeasurementDto> measurements = deviceAdapter.getDeviceEnergyUsageByPeriod(id, "gain", firstDayofMonth, today);
				for (MeasurementDto measurementDto : measurements) { 
					// loop over all measurements (15s) during the day

					if (measurements.indexOf(measurementDto) < measurements.size() - 1) {
						time = convertTimestampStringToSeconds(measurementDto.getTimestamp());
						time2 = convertTimestampStringToSeconds(measurements.get(measurements.indexOf(measurementDto) + 1).getTimestamp());
						duration = time2 - time;
						deviceEnergyUsage += Float.parseFloat(measurementDto.getValue()) * LIGHTING_WATTAGE * duration; 
						
						// calculate energy used during 15s for a single device
						// System.out.println("(M) Energy used by device " + id
						// + " in time " + measurementDto.getTimestamp() + " t="
						// + time + ": gain = "
						// + measurementDto.getValue() + " Energy = " +
						// deviceEnergyUsage + " Ws" + " in " + duration +
						// " seconds");
					}
				}
			}
			roomEnergyUsage += deviceEnergyUsage;
		}
		return (roomEnergyUsage / 1000 / 3600); // this is in kWh 
	}

	// TODO Do we need this method, Pu? YES
	// Explanation: return the adjustment of power level (gain) for each lamp in the room in order to
	// fulfil minimum luminance level in the room.
	 public double getLampAdjustment(Room room) {
		  double absPowerAdjustment = 0.0f; // extra luminance supplied by lamps (can be negative!)
		 
		 double minRoomLum = 0.0f; // minimum luminance in room based on policy
		 // model (lumens [lm])
		 
		 int noOfLamps = 0; // number of lamps in room
		 EnergyState energyState = EnergyState.NORMAL;
		 //int activityLevel = 0; // activity level of room
		 double adjustPowerFactor = 0d;
		 double roomsize = 5d; //5 m2
	
		 Calendar now = Calendar.getInstance();
	
	 // set luminous efficacy
	 // source: http://www.rapidtables.com/calc/light/how-lux-to-watt.htm
	 // Light type Typical luminous efficacy(lumens/watt)
	 // Tungsten incandescent light bulb 12.5-17.5 lm/W
	 // Halogen lamp 16-24 lm/W
	 // Fluorescent lamp 45-75 lm/W
	 // LED lamp 30-90 lm/W
	 // Metal halide lamp 75-100 lm/W
	 // High pressure sodium vapor lamp 85-150 lm/W
	 // Low pressure sodium vapor lamp 100-200 lm/W
	 // Mercury vapor lamp 35-65 lm/W
	 double efficacyLamp = 45f; // Assuming value based on table above: efficacy of lamps in room [lm/W]
	 
	 // get number of lamps in room
	 for (Device device : room.getDevices()) { // loop over all devices in
		 // room
		 String id = device.getForeignDeviceId();
	 	if (id.contains("light")) {
	 		noOfLamps = noOfLamps + 1;
	 	}
	 }
	
	 // retrieve minimum luminous flux (lumens) level required by policy model first, get energy state of building
	 energyState = EnergyState.NORMAL;	 
	 
	 // Get the current light for the room
 	 MeasurementDto light = roomAdapter.getCurrentRoomLight(room.getForeignRoomID());
	 Float actualLight = Float.parseFloat(light.getValue());  // assume this is lumens (= k photons) = how much light get through window + light from lamps

	// Look up if the current light level should be adjusted.
	RuleLux rule = ruleService.getRoomLightingPolicy(room, Float.parseFloat(light.getValue()));

	// finally, look up minimum lux value in table and multiply by room size to get minimum luminance requirement (in lumens = lux*m2)
	minRoomLum = rule.getRecommendedLux() * roomsize;			//lumens
	
	 // calculate extra light to be supplied by each lamp
	 absPowerAdjustment = (minRoomLum - actualLight) / noOfLamps;
	
	 // convert from luminous flux (lumens) to power (W) -- assume all lamps have max power = 60 W
	 adjustPowerFactor = absPowerAdjustment / efficacyLamp / 60.0f ;
	
	 System.out.println("no of lamps = " + noOfLamps);
	 System.out.println("Actual light = " + actualLight);
	 System.out.println("Min light = " + minRoomLum);
	 System.out.println("absPowerAdjustment = " + absPowerAdjustment);
	 System.out.println("adjustPowerFactor = " + adjustPowerFactor);
	 // Note: return minimum power required by each lamp in the room in W
	 return adjustPowerFactor;
	 }
	
	@Override
	public Room getRoomData(Long roomId) {
		return roomDao.find(roomId);
	}

	@Override
	public void updateRoomMeasurementdata() {

		for (Room room : roomDao.findAll()) {
			//FIXME These methods time out. I don't know where the error is.Pu: I don't see where you mean by time out? I run the measurement quite fine.
			room.setEnergyUsageLastDay(getEnergyUsageByDay(room));
			room.setEnergyUsageLastWeek(getEnergyUsageByWeek(room));
			room.setEnergyUsageLastMonth(getEnergyUsageByMonth(room));
			roomDao.save(room);

		}
	}

	@Override
	public void turnOffLight() {
		// Go through each room in the building
		for (Room room : roomDao.findAll()) {

			// If lighting is not allowed we turn of the light and continue to
			// next room
			RuleSecurity securityPolicy = ruleService.getRoomSecurityPolicy(room);
			if (!securityPolicy.getIsLightAllowed()) {
				for (Device theDevice : room.getDevices()) {
					if (theDevice.getForeignDeviceId().contains("light")) {
						deviceService.turnOffLight(theDevice.getForeignDeviceId());
					}
				}
				continue;
			}

			// We want to know if there has been any room activity
			boolean roomActivity = false;
			// hence we look at each device and see if there has been any activity in the last x minutes, where x is taken from the schedule.
			for (Device device : room.getDevices()) {
				boolean hasActivityOccured = false;
				RuleSchedule policy = ruleService.getRoomSchedulePolicy(room);
				int minutesBeforeLightShouldTurnOff = policy.getTimeoutLimit();

				// Lights have different output (gain, state)we need to look at.
				if (device.getForeignDeviceId().contains("light")) {
					hasActivityOccured = deviceService.hasRegistreredActivity(device.getForeignDeviceId(), "gain", minutesBeforeLightShouldTurnOff);
					// hasActivityOccured can be overridden, so store the result now.
					if (hasActivityOccured) {
						roomActivity = true;
					}
					hasActivityOccured = deviceService.hasRegistreredActivity(device.getForeignDeviceId(), "state", minutesBeforeLightShouldTurnOff);
				}
				// Blinds have only gain state to look at.
				if (device.getForeignDeviceId().contains("blind")) {
					hasActivityOccured = deviceService.hasRegistreredActivity(device.getForeignDeviceId(), "state", minutesBeforeLightShouldTurnOff);

				}
				if (hasActivityOccured) {
					roomActivity = true;
					break;
				}
			}

			// if there has not been any room Activity then we shut off the lights.
			if (!roomActivity) {
				logger.debug(String.format("Turning of lights in room %s", room.getForeignRoomID()));
				for (Device theDevice : room.getDevices()) {
					if (theDevice.getForeignDeviceId().contains("light")) {
						deviceService.turnOffLight(theDevice.getForeignDeviceId());
					}
				}
			}
		}
	}

	@Override
	public void adjustLight() {
		// Go though each room
		
		for (Room room : roomDao.findAll()) {

			// If lighting is not allowed we turn of the light and continue to
			// next room
			RuleSecurity securityPolicy = ruleService.getRoomSecurityPolicy(room);
			if (!securityPolicy.getIsLightAllowed()) {
				for (Device theDevice : room.getDevices()) {
					if (theDevice.getForeignDeviceId().contains("light")) {
						deviceService.turnOffLight(theDevice.getForeignDeviceId());
					}
				}
				continue;
			}

			// Get the current light for the room
			MeasurementDto light = roomAdapter.getCurrentRoomLight(room.getForeignRoomID());
			
			// Double adjustmentRatio 
			Double lampAdjustment = (double) this.getLampAdjustment(room); // FIXME Pu: done! Do correct calculation here. We need to find out by how much we should adjust the light in the room

			// Look up if the current light level should be adjusted.
			RuleLux rule = ruleService.getRoomLightingPolicy(room, Float.parseFloat(light.getValue()));

			// In case it needs to be adjusted
			if (rule.getShouldAdjustLight()) {
				//Double recommendedLight = rule.getRecommendedLux();			//lux
				//minimumLight = recommendedLight * roomsize;
				//Double actualLight = Double.parseDouble(light.getValue());  //lumens or photons = how much light get through the room
				
				for (Device device : room.getDevices()) {
					if (device.getForeignDeviceId().contains("light")) {
						MeasurementDto currentGainMeasurement = deviceAdapter.getLatestDeviceMeasurement(device.getForeignDeviceId(), "gain");

						Double newGainValue = Double.valueOf(currentGainMeasurement.getValue()) + lampAdjustment; //increase or decrease gain value
						newGainValue = Math.min(1d, Math.max(0d, newGainValue)); //make sure value is between 0 and 1
						deviceAdapter.adjustLight(device.getForeignDeviceId(), newGainValue);  //adjust lamp setting
						logger.debug(String.format("Adjusting lamp %s from %f to %f ", device.getForeignDeviceId(), Double.valueOf(currentGainMeasurement.getValue()), newGainValue ));
					}

				}
				//wait a sec or two
				try {
				    Thread.sleep(2000);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				// Get the current light for the room
			 	light = roomAdapter.getCurrentRoomLight(room.getForeignRoomID());
				Float actualLight = Float.parseFloat(light.getValue()); 
				logger.debug(String.format("Light in room %s is now %f lumens", room.getForeignRoomID(), actualLight ));
				// In case it needs NOT to be adjusted we ignore
			} else {
				// do nothing...
			}
		}
	}
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

}
