package dk.itu.gsd.lms.dao.impl;

import org.springframework.stereotype.Repository;

import dk.itu.gsd.lms.dao.BuildingDao;
import dk.itu.gsd.lms.model.Building;

@Repository("buildingDao")
public class BuildingDaoImpl extends GenericDaoImpl<Building> implements BuildingDao{

}
