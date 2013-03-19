package dk.itu.gsd.lms.dao.impl;

import org.springframework.stereotype.Repository;

import dk.itu.gsd.lms.dao.RoomDao;
import dk.itu.gsd.lms.model.AbstractRoom;

@Repository("roomDao")
public class RoomDaoImpl extends GenericDaoImpl<AbstractRoom>implements RoomDao{

}
