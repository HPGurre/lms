package dk.itu.gsd.lms.services.impl;

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
 * gain * wattage = value in megaWatt
 * @author HP
 *
 */

@Service("roomService")
public class RoomServiceImpl implements RoomService{
	public static int LIGHTING_WATTAGE = 60;

	@Autowired
	RoomAdapter roomAdapter;
	
	@Autowired
	RoomDao roomDao;
	
	@Override
	public Long getEnergyUsageByDay(AbstractRoom room) {
		// TODO get list of measurement aggregate the data?
		Long roomEnergyUsage = 0L;
		
		//look up room devices.
		for (Device device : room.getDevices()) {
			Long deviceEnergyUsage = 0L;
			String id = device.getForeignDeviceId();
			if (id.contains("light")) {
				List<MeasurementDto> measurements = roomAdapter.getDeviceEnergyUsageByDay(id, "g  ain");
				for (MeasurementDto measurementDto : measurements) {
					deviceEnergyUsage+= Integer.parseInt(measurementDto.getValue()) * LIGHTING_WATTAGE * 15;
				}
			}
			roomEnergyUsage+= deviceEnergyUsage;
		}
		return roomEnergyUsage;
	}

	@Override
	public String getEnergyUsageByWeek(Long foreignRoomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEnergyUsageByMonth(Long foreignRoomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getEnergymeasurements() {
		System.out.println("Fetching measurement data for rooms");
		for (AbstractRoom room : roomDao.findAll()) {
			//getEnergyUsageByDay(room.getForeignRoomID());
			room.setEnergyUsageLastDay((int)Math.random()*100L);
			roomDao.save(room);
		}
		System.out.println("Other measurement data...NOT IMPLEMENTED");
	}

	@Override
	public AbstractRoom getRoomData(Long roomId) {
		return roomDao.find(roomId);
	}
}
