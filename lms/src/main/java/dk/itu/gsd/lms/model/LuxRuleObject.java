package dk.itu.gsd.lms.model;

public class LuxRuleObject {

	private Integer currentLux;
	private String roomActivity;
	private String energyState;
	private Integer RecommendedLux;
	private Boolean ShouldAdjustLight;
	public Integer getCurrentLux() {
		return currentLux;
	}
	public Boolean getShouldAdjustLight() {
		return ShouldAdjustLight;
	}
	public void setShouldAdjustLight(Boolean shouldAdjustLight) {
		ShouldAdjustLight = shouldAdjustLight;
	}
	public void setCurrentLux(Integer currentLux) {
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
	public Integer getRecommendedLux() {
		return RecommendedLux;
	}
	public void setRecommendedLux(Integer recommendedLux) {
		RecommendedLux = recommendedLux;
	}
	
}
