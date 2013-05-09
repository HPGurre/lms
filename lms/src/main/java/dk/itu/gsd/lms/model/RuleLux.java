package dk.itu.gsd.lms.model;

public class RuleLux {

	private Double currentLux;
	private String energyState;
	private Double RecommendedLux;
	private String roomActivity;
	private Boolean ShouldAdjustLight;
	public Double getCurrentLux() {
		return currentLux;
	}
	public String getEnergyState() {
		return energyState;
	}
	public Double getRecommendedLux() {
		return RecommendedLux;
	}
	public String getRoomActivity() {
		return roomActivity;
	}
	public Boolean getShouldAdjustLight() {
		return ShouldAdjustLight;
	}
	public void setCurrentLux(Double currentLux) {
		this.currentLux = currentLux;
	}
	public void setEnergyState(String energyState) {
		this.energyState = energyState;
	}
	public void setRecommendedLux(Double recommendedLux) {
		RecommendedLux = recommendedLux;
	}
	public void setRoomActivity(String roomActivity) {
		this.roomActivity = roomActivity;
	}
	public void setShouldAdjustLight(Boolean shouldAdjustLight) {
		ShouldAdjustLight = shouldAdjustLight;
	}
	
}
