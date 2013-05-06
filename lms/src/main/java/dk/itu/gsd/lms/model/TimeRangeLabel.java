package dk.itu.gsd.lms.model;

import java.util.Calendar;

public enum TimeRangeLabel {
	DAY("Day"), EVENING("Evening"), NIGHT("Night"), WEEKEND("Weekend"), HOLIDAY("Holiday");

	private String description;

	TimeRangeLabel(String description) {
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static TimeRangeLabel getLabelFromCalendar(Calendar cal) {
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
}
