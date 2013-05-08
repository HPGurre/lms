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
import dk.itu.gsd.lms.model.AbstractRoom;
import dk.itu.gsd.lms.model.Device;
import dk.itu.gsd.lms.model.LuxRuleObject;
import dk.itu.gsd.lms.model.ScheduleRuleObject;
import dk.itu.gsd.lms.model.SecurityRuleObject;
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

	@Autowired
	RoomAdapter roomAdapter;

	@Autowired
	DeviceAdapter deviceAdapter;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	RoomDao roomDao;
	@Autowired
	FloorDao floorDao;
	@Autowired
	BuildingDao buildingDao;

	@Autowired
	RuleService ruleService;

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
				List<MeasurementDto> measurements = deviceAdapter.getDeviceEnergyUsageByPeriod(id, "gain", tomorrow, today);
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
	public Float getEnergyUsageByWeek(AbstractRoom room) {
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
				List<MeasurementDto> measurements = deviceAdapter.getDeviceEnergyUsageByPeriod(id, "gain", firstDayofMonth, today);
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
		return (roomEnergyUsage / 1000 / 3600); // this is in kWh //FIXME is it
												// always 30.
	}

	// TODO Do we need this method, Pu?
	// public int getActivityLevel(AbstractRoom room) {
	// int activityLevel = -1;
	//
	// Calendar now = Calendar.getInstance();
	// Calendar fiveMinsAgo = Calendar.getInstance();
	// fiveMinsAgo.add(Calendar.MINUTE, -5);
	// // look up room devices.
	// for (Device device : room.getDevices()) { // loop over all devices in
	// // room
	// String id = device.getForeignDeviceId();
	//
	// // if (id.contains("light")) {
	// List<MeasurementDto> measurements =
	// deviceAdapter.getDeviceEnergyUsageByPeriod(id, "gain", fiveMinsAgo, now);
	//
	// if (activityLevel < 1 && measurements.size() == 0) {
	// activityLevel = 0;
	// } else {
	// activityLevel = 1;
	// }
	// }
	//
	// return activityLevel; // integer indicating activity in room = 0 if no
	// // activity.
	// }

	// TODO Do we need this method, Pu?
	// return the power requirements for each lamp in the room in order to
	// Fulfil minimum lux level in the room
	// public float getLampMinPower(AbstractRoom room) {
	// float minPower = 0.0f; // return value
	// float minLampLum = 0.0f; // minimum luminance supplied by lamps
	// float lmSun = 0.0f; // luminance of sun
	// float sun = 0.0f; // value from sun model
	// float cloud = 0.0f; // value from cloud model
	// float minRoomLum = 0.0f; // minimum luminance in room based on policy
	// // model (lumens [lm])
	// float windowSize = 0.0f; // size of windows in room
	// float blindState = 0.0f; // state of window blinds
	// int noOfLamps = 0; // number of lamps in room
	// EnergyState energyState = EnergyState.NORMAL;
	// int activityLevel = 0; // activity level of room
	// int lightingBlockId = 0; // lighting block id
	//
	// Calendar now = Calendar.getInstance();
	//
	// // set luminous efficacy
	// // source: http://www.rapidtables.com/calc/light/how-lux-to-watt.htm
	// // Light type Typical luminous efficacy(lumens/watt)
	// // Tungsten incandescent light bulb 12.5-17.5 lm/W
	// // Halogen lamp 16-24 lm/W
	// // Fluorescent lamp 45-75 lm/W
	// // LED lamp 30-90 lm/W
	// // Metal halide lamp 75-100 lm/W
	// // High pressure sodium vapor lamp 85-150 lm/W
	// // Low pressure sodium vapor lamp 100-200 lm/W
	// // Mercury vapor lamp 35-65 lm/W
	// float efficacyLamp = 45f; // efficacy of lamps in room [lm/W]
	//
	// // assume no blinds
	//
	// // retrieve value of sun model
	//
	// // retrieve value of cloud model
	//
	// for (Device device : room.getDevices()) { // loop over all devices in
	// // room
	//
	// String id = device.getForeignDeviceId();
	//
	// if (id.contains("blind")) {
	//
	// // get window size from building simulator
	// windowSize = 5.0f;
	//
	// // retrieve sun model from building simulator
	// sun = 1.0f;
	//
	// // retrieve cloud model from building simulator
	// cloud = 0.2f;
	//
	// // retrieve value of blind state
	// List<MeasurementDto> measurements =
	// deviceAdapter.getDeviceEnergyUsageByNumber(id, "setpoint", now, 1);
	// blindState = Float.parseFloat(measurements.get(0).getValue());
	// System.out.println("Blind state = " + blindState);
	//
	// // update luminance from sun/cloud model through window
	// lmSun = lmSun + blindState * windowSize * sun * (1f - cloud); // luminous
	// // flux
	// // from
	// // windows
	// // (lm)
	// }
	//
	// }
	//
	// // get number of lamps in room
	// for (Device device : room.getDevices()) { // loop over all devices in
	// // room
	// String id = device.getForeignDeviceId();
	// if (id.contains("light")) {
	// noOfLamps = noOfLamps + 1;
	// }
	// }
	//
	// // retrieve minimum luminous flux (lumens) level required by policy
	// // model
	// // first, get energy state of building
	// energyState = EnergyState.NORMAL;
	// // then, get activity level
	// activityLevel = this.getActivityLevel(room);
	// // and get lighting block id
	// lightingBlockId = 1;
	// // finally, look up minimum luminance value in table
	// minRoomLum = 300.0f;
	//
	// // calculate minimum luminous flux (lumens) to be supplied by each lamp
	// minLampLum = Math.max(0f, minRoomLum - lmSun) / noOfLamps;
	//
	// // convert from luminous flux (lumens) to power (W)
	// minPower = minLampLum / efficacyLamp;
	//
	// System.out.println("no of lamps = " + noOfLamps);
	// System.out.println("luminousity of sun-cloud-blinds = " + lmSun);
	//
	// // return minimum power required by each lamp in the room in W
	// return minPower;
	// }
	// TODO Do we need this, Pu?
	// // Toggle state of lamps to fulfil minimum lux level in the room
	// public void setLightsAccordingToPolicy(AbstractRoom room) {
	// int wattage = 0;
	// float gain = 0f;
	// float minPower = 0f;
	//
	// // Calendar now = Calendar.getInstance();
	//
	// minPower = this.getLampMinPower(room);
	// //
	// for (Device device : room.getDevices()) { // loop over all devices in
	// // room
	// String id = device.getForeignDeviceId();
	// if (id.contains("light")) {
	// // retrieve value of max power (wattage)
	// wattage = 60;
	// gain = Math.min(1f, minPower / wattage);
	// System.out.println("Lamp state = " + gain);
	// // change state of lamp
	// deviceService.adjustLight(id, gain);
	// /**
	// ** Retrieving measured after set the state: Not working yet. My
	// * guess is duration of time. currently implemented as
	// * retrieving right after the state is changed.
	// */
	// // List<MeasurementDto> measurements =
	// // roomAdapter.getDeviceEnergyUsageByNumber(id, "gain", now,1);
	// // float lampstate =
	// // Float.parseFloat(measurements.get(0).getValue());
	// // System.out.println("Measured Lamp state = " + lampstate);
	// }
	// }
	//
	// }

	@Override
	public AbstractRoom getRoomData(Long roomId) {
		return roomDao.find(roomId);
	}

	@Override
	public void updateRoomMeasurementdata() {

		for (AbstractRoom room : roomDao.findAll()) {
			room.setEnergyUsageLastDay(getEnergyUsageByDay(room));
			room.setEnergyUsageLastWeek(getEnergyUsageByWeek(room));
			room.setEnergyUsageLastMonth(getEnergyUsageByMonth(room));
			roomDao.save(room);

			// TODO Do we need this, Pu?.
			// test getLampMinPower function
			// System.out.println("Lamp min power = " +
			// roomService.getLampMinPower(room));
			// change state of lamps according to the policy model
			// roomService.setLightsAccordingToPolicy(room);
		}
	}
	@Override
	public void turnOffLight(){
		// Go through each room in the building
		for (AbstractRoom room : roomDao.findAll()) {
			
			//If lighting is not allowed we turn of the light and continue to next room
			SecurityRuleObject securityPolicy = ruleService.getRoomSecurityPolicy(room);
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
			// hence we look at each device and see if there has been any
			// activity in the last x minutes, where x is taken from the
			// schedule
			for (Device device : room.getDevices()) {
				boolean hasActivityOccured = false;
				ScheduleRuleObject policy = ruleService.getRoomSchedulePolicy(room);
				int minutesBeforeLightShouldTurnOff = policy.getTimeoutLimit();

				// Lights have different output (gain, state)we need to look at.
				if (device.getForeignDeviceId().contains("light")) {
					hasActivityOccured = deviceService.hasRegistreredActivity(device.getForeignDeviceId(), "gain", minutesBeforeLightShouldTurnOff);
					// hasActivityOccured can be overridden, so store the result now
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

			// if there has not been any room Activity then we shut off the
			// lights.
			if (!roomActivity) {
				logger.debug(String.format("Turning of lights in room %s", room.getForeignRoomID()) );
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
		for (AbstractRoom room : roomDao.findAll()) {

			// If lighting is not allowed we turn of the light and continue to
			// next room
			SecurityRuleObject securityPolicy = ruleService.getRoomSecurityPolicy(room);
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

			// Look up if the current light level should be adjusted.
			LuxRuleObject rule = ruleService.getRoomLightingPolicy(room, Float.parseFloat(light.getValue()));

			// In case needs to be adjusted
			if (rule.getShouldAdjustLight()) {
				Double recommendedLight = rule.getRecommendedLux();
				Double actualLight = Double.parseDouble(light.getValue());

				// Double adjustmentRatio = recommendedLight / actualLight;
				Double adjustmentRatio = 2d; // FIXME do correct calculation
												// here.

				for (Device device : room.getDevices()) {
					if (device.getForeignDeviceId().contains("light")) {
						MeasurementDto currentGainMeasurement = deviceAdapter.getLatestDeviceMeasurement(device.getForeignDeviceId(), "gain");

						Double newGainValue = Double.valueOf(currentGainMeasurement.getValue()) * adjustmentRatio;
						deviceAdapter.adjustLight(device.getForeignDeviceId(), newGainValue);
					}

				}
				// In case needs NOT to be adjusted we ignore
			} else {
				// do nothing...
			}
		}
	}

}
