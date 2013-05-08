package dk.itu.gsd.lms.model;

public class LuxRuleObject {

	private Double currentLux;
	private String roomActivity;
	private String energyState;
	private Double RecommendedLux;
	private Boolean ShouldAdjustLight;
	public Double getCurrentLux() {
		return currentLux;
	}
	public Boolean getShouldAdjustLight() {
		return ShouldAdjustLight;
	}
	public void setShouldAdjustLight(Boolean shouldAdjustLight) {
		ShouldAdjustLight = shouldAdjustLight;
	}
	public void setCurrentLux(Double currentLux) {
		this.currentLux = currentLux;
	}
	public String getRoomActivity() {
		return roomActivity;
	}
	public void setRoomActivity(String roomActivity) {
		this.roomActivity = roomActivity;
	}
	public String getEnergyState() {
		return energyState;
	}
	public void setEnergyState(String energyState) {
		this.energyState = energyState;
	}
	public Double getRecommendedLux() {
		return RecommendedLux;
	}
	public void setRecommendedLux(Double recommendedLux) {
		RecommendedLux = recommendedLux;
	}
	
}
