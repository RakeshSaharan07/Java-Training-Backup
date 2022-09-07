package com.examples.java.core;

/**
 * Enum is a special class to hold constant values
 */
public class EnumDemo {

	public static enum Colors {
		BLUE(100), GREEN(200), RED(300);

		private int colorIndex;

		private Colors(int colorIndex) {
			this.colorIndex = colorIndex;
		}
	}
	
	// enum definition
	public enum WeekDays {		
		MONDAY(0) {			
			
			public String getDisplayName()
			{
				return "Monday";
			}
			
		},
		TUESDAY(1) {
			public String getDisplayName()
			{
				return "Tuesday";
			}
		}, 
		WEDNESDAY(2), 
		THURSDAY(3), 
		FRIDAY(4), 
		SATURDAY(5), 
		SUNDAY(6);
		
		private int dayIndex;		
		
		private WeekDays()
		{
			System.out.println("Default Enum constructor");
		}
		
		private WeekDays(Integer dayIndex)
		{
			System.out.println("Overloaded Enum constructor");
			this.dayIndex = dayIndex;
		}		
		
		public String getDisplayName()
		{
			return "Enum method test";
		}
		
		public int getDayIndex()
		{
			return this.dayIndex;
		}
	};
	
	public static void main(String[] args)
	{
//		Colors color = Colors.BLUE;
//		Colors color = Colors.valueOf("BLUE");
//		switch (color) {
//			case BLUE:
//				System.out.println(Colors.BLUE.toString() + " BLUE Color");
//				break;
//			case GREEN:
//				System.out.println("GREEN Color");
//				break;
//			case RED:
//				System.out.println("RED Color");
//				break;
//		}

		String day = "MONDAY";

		// String comparison
		if(WeekDays.MONDAY.toString() == day)
		{
			System.out.println("Given day is Monday");
		}

		// Enum comparison
		if(WeekDays.MONDAY == WeekDays.valueOf(day))
		{
			System.out.println("Given day is Monday");
		}

		// Get all enum values
		for(Object weekDay: WeekDays.values())
		{
			System.out.println(weekDay);
		}

		// Invoke enum methods
		System.out.println(WeekDays.MONDAY.getDisplayName());
		System.out.println(WeekDays.TUESDAY.getDisplayName());
		System.out.println(WeekDays.WEDNESDAY.getDisplayName());

		// Print day index value
		System.out.println(WeekDays.MONDAY.getDayIndex());
		System.out.println(WeekDays.SUNDAY.getDayIndex());

		// Access enum values as array element
		WeekDays[] weekDaysArr = WeekDays.values();
		System.out.println(weekDaysArr[0]);
	}

}
