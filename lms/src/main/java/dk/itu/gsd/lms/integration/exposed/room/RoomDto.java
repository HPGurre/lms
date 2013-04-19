package dk.itu.gsd.lms.integration.exposed.room;

import dk.itu.gsd.lms.model.EnergyState;

/**
 * 
 * @author HP
 * This class represent the attributes or model we present as a webservice. 
 * That is, if you fill out these attributes they will be exposed through the webservice.
 */
public class RoomDto {

	private double totalEnergyUsage;
	private double maxEnergyUsage;
	private EnergyState energyState;
	private double dailyEnergyUsage;
	private double weeklyEnergyUsage;
	private double monthlyEnergyUsage;
	public double getTotalEnergyUsage() {
		return totalEnergyUsage;
	}
	public void setTotalEnergyUsage(double totalEnergyUsage) {
		this.totalEnergyUsage = totalEnergyUsage;
	}
	public double getMaxEnergyUsage() {
		return maxEnergyUsage;
	}
	public void setMaxEnergyUsage(double maxEnergyUsage) {
		this.maxEnergyUsage = maxEnergyUsage;
	}
	public EnergyState getEnergyState() {
		return energyState;
	}
	public void setEnergyState(EnergyState energyState) {
		this.energyState = energyState;
	}
	public double getDailyEnergyUsage() {
		return dailyEnergyUsage;
	}
	public void setDailyEnergyUsage(double dailyEnergyUsage) {
		this.dailyEnergyUsage = dailyEnergyUsage;
	}
	public double getWeeklyEnergyUsage() {
		return weeklyEnergyUsage;
	}
	public void setWeeklyEnergyUsage(double weeklyEnergyUsage) {
		this.weeklyEnergyUsage = weeklyEnergyUsage;
	}
	public double getMonthlyEnergyUsage() {
		return monthlyEnergyUsage;
	}
	public void setMonthlyEnergyUsage(double monthlyEnergyUsage) {
		this.monthlyEnergyUsage = monthlyEnergyUsage;
	}

	
}
