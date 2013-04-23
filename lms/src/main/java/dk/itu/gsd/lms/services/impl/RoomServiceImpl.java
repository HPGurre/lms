package dk.itu.gsd.lms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.integration.consumed.building.RoomAdapter;
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

	@Autowired
	RoomAdapter roomAdapter;
	
	@Autowired
	RoomDao roomDao;
	
	@Override
	public Long getEnergyUsageByDay(AbstractRoom room) {
		// TODO get list of measurement agregate the data?
		Long roomEnergyUsage = 0L;
		
		//look up room devices.
		for (Device device : room.getDevices()) {
			Long deviceEnergyUsage = 0L;
			String id = device.getForeignDeviceId();
			if (id.contains("light")) {
				roomAdapter.getDeviceEnergyUsageByDay(id);
			}
			roomEnergyUsage+= deviceEnergyUsage;
		}
		//AbstractRoom room = roomDao.find(foreignRoomId);
		
		//query each device ID.
		
//		roomAdapter.getDeviceEnergyUsageByDay(foreignRoomId, "room-1-light-2-production");
//		roomAdapter.getDeviceEnergyUsageByDay(foreignRoomId, "room-1-light-2-production");
		
		return null;
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
