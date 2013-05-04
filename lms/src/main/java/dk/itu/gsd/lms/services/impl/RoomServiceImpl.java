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
						System.out.println("(D) Energy used by device " + id + " in time " + measurementDto.getTimestamp() + " t=" + time + ": gain = "
								+ measurementDto.getValue() + " Energy = " + deviceEnergyUsage + " Ws" + " in " + duration + " seconds");
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
						System.out.println("(W) Energy used by device " + id + " in time " + measurementDto.getTimestamp() + " t=" + time + ": gain = "
								+ measurementDto.getValue() + " Energy = " + deviceEnergyUsage + " Ws" + " in " + duration + " seconds");
					}
				}
			}
			roomEnergyUsage += deviceEnergyUsage;
		}
		return roomEnergyUsage / 1000 / 3600; // this is in kWh
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
						System.out.println("(M) Energy used by device " + id + " in time " + measurementDto.getTimestamp() + " t=" + time + ": gain = "
								+ measurementDto.getValue() + " Energy = " + deviceEnergyUsage + " Ws" + " in " + duration + " seconds");
					}
				}
			}
			roomEnergyUsage += deviceEnergyUsage;
		}
		return roomEnergyUsage / 1000 / 3600; // this is in kWh //FIXME is it always 30.
	}

	@Override
	public AbstractRoom getRoomData(Long roomId) {
		return roomDao.find(roomId);
	}

}
