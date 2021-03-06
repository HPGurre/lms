package dk.itu.gsd.lms.integration.exposed.floor;

import dk.itu.gsd.lms.model.EnergyState;

public class FloorDto {
	
	private float totalEnergyUsage;
	private float maxEnergyUsage;
	private EnergyState energyState;
	private float dailyEnergyUsage;
	private float weeklyEnergyUsage;
	private float monthlyEnergyUsage;
	public float getTotalEnergyUsage() {
		return totalEnergyUsage;
	}
	public void setTotalEnergyUsage(float totalEnergyUsage) {
		this.totalEnergyUsage = totalEnergyUsage;
	}
	public float getMaxEnergyUsage() {
		return maxEnergyUsage;
	}
	public void setMaxEnergyUsage(float maxEnergyUsage) {
		this.maxEnergyUsage = maxEnergyUsage;
	}
	public EnergyState getEnergyState() {
		return energyState;
	}
	public void setEnergyState(EnergyState energyState) {
		this.energyState = energyState;
	}
	public float getDailyEnergyUsage() {
		return dailyEnergyUsage;
	}
	public void setDailyEnergyUsage(float dailyEnergyUsage) {
		this.dailyEnergyUsage = dailyEnergyUsage;
	}
	public float getWeeklyEnergyUsage() {
		return weeklyEnergyUsage;
	}
	public void setWeeklyEnergyUsage(float weeklyEnergyUsage) {
		this.weeklyEnergyUsage = weeklyEnergyUsage;
	}
	public float getMonthlyEnergyUsage() {
		return monthlyEnergyUsage;
	}
	public void setMonthlyEnergyUsage(float monthlyEnergyUsage) {
		this.monthlyEnergyUsage = monthlyEnergyUsage;
	}
	
	
}
