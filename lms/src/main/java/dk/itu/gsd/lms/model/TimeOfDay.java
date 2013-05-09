package dk.itu.gsd.lms.model;

import java.util.Calendar;

public enum TimeOfDay {
	DAY("Day"), 
	EVENING("Evening"), 
	HOLIDAY("Holiday"), 
	NIGHT("Night"), 
	WEEKEND("Weekend");

	public static TimeOfDay getTimeOfDayFromCalendar(Calendar cal) {
		int hour = cal.get(Calendar.HOUR_OF_DAY);

		if (hour >= 6 && hour <= 18) {
			return DAY;
		}

		if (hour > 18 && hour <= 22) {
			return EVENING;
		}

		if (hour > 22 || hour < 6) {
			return NIGHT;
		}

		return DAY;

	}

	private String displayName;

	TimeOfDay(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
