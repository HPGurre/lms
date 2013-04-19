package dk.itu.gsd.lms.integration.exposed.building;

/**
 * 
 * @author HP
 * This class represent the attributes or model we present as a webservice. 
 * That is, if you fill out these attributes they will be exposed through the webservice.
 */
public class BuildingDto {

	private double energyConsumptionValue;

	public double getEnergyConsumptionValue() {
		return energyConsumptionValue;
	}

	public void setEnergyConsumptionValue(double energyConsumptionValue) {
		this.energyConsumptionValue = energyConsumptionValue;
	}
}
