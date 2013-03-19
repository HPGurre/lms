package dk.itu.gsd.lms.dao.impl;

import org.springframework.stereotype.Repository;

import dk.itu.gsd.lms.dao.FloorDao;
import dk.itu.gsd.lms.model.Floor;

@Repository("floorDao")
public class FloorDaoImpl extends GenericDaoImpl<Floor> implements FloorDao{

}
