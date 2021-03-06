/*
	Unix Epoch - Thursday, 1/1/1970
	Week Begins on Sunday
 */
	import java.util.Scanner;
	public class Question6_24 {

		public static final int UNIX_EPOCH_YEAR = 1970;
		public static final int REFERENCE_YEAR = 1800;
		public static final int REFERENCE_DAY_OF_WEEK = 3;

		public static int getNumberOfDaysInMonth(int month, int year) {
			int numberOfDays = 0;
			switch (month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
				numberOfDays = 31;
				break;
				case 4:
				case 6:
				case 9:
				case 11:
				numberOfDays = 30;
				break;
				case 2:
				numberOfDays = isLeapYear(year) ? 29 : 28;
				break;
			}
			return numberOfDays;
		}

		public static boolean isLeapYear(int year) {
			return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
		}

		public static String getMonthName(int month) {
			String monthName = "";
			switch (month) {
				case 1: monthName = "January";
				break;
				case 2: monthName = "February";
				break;
				case 3: monthName = "March";
				break;
				case 4: monthName = "April";
				break;
				case 5: monthName = "May";
				break;
				case 6: monthName = "June";
				break;
				case 7: monthName = "July";
				break;
				case 8: monthName = "August";
				break;
				case 9: monthName = "September";
				break;
				case 10: monthName = "October";
				break;
				case 11: monthName = "November";
				break;
				case 12: monthName = "December";
				break;
			}
			return monthName;
		}

		public static String getDayName(int day) {
			String dayName = "";
			switch (day) {
				case 0: dayName = "Sunday";
				break;
				case 1: dayName = "Monday";
				break;
				case 2: dayName = "Tuesday";
				break;
				case 3: dayName = "Wednesday";
				break;
				case 4: dayName = "Thursday";
				break;
				case 5: dayName = "Friday";
				break;
				case 6: dayName = "Saturday";
				break;
			}
			return dayName;
		}

		public static int getDayOfWeek(int daysSinceReferenceDay) {
			return (REFERENCE_DAY_OF_WEEK + daysSinceReferenceDay) % 7;
		}

		public static void calculateAndDisplayDate(int daysSinceReferenceDay) {
			int dayOfWeek = getDayOfWeek(daysSinceReferenceDay);
			int year = REFERENCE_YEAR, numberOfDaysInYear;
			while (true) {
				numberOfDaysInYear = isLeapYear(year) ? 366 : 365;
				if (daysSinceReferenceDay < numberOfDaysInYear) {
					break;
				}
				else {
				daysSinceReferenceDay -= numberOfDaysInYear;
				}
				year++;
			}
			int daysSinceYearBeginning = daysSinceReferenceDay;	//storing values in variables more appropriately named
			int daysSinceMonthBeginning;
			int numberOfDaysInMonth, month;
			for (month = 1; month <= 12; month++) {
				numberOfDaysInMonth = getNumberOfDaysInMonth(month, year);
				if (daysSinceYearBeginning < numberOfDaysInMonth) {
					break;
				}
				else {
					daysSinceYearBeginning -= numberOfDaysInMonth;
				}
			}
			daysSinceMonthBeginning = daysSinceYearBeginning;
			// The variable "month" is 1-indexed, while differences between the days are 0-indexed. Adding 1.
			int dayOfMonth = daysSinceMonthBeginning + 1;
			displayDate(dayOfWeek, dayOfMonth, month, year);
		}

		public static void displayTime(int hours, int minutes, int seconds) {
			System.out.printf("%2s:%2s:%2s %2s GMT\n", 
				formatTimeUnits(convertTo12HourNotation(hours)),
				formatTimeUnits(minutes),
				formatTimeUnits(seconds), returnMeridiem(hours));
		}

		public static String formatTimeUnits(int timeUnit) {
			if (timeUnit / 10 > 0) {
				return "" + timeUnit;
			}
			else {
				return "0" + timeUnit;
			}
		}

		public static int convertTo12HourNotation(int hours) {
			return hours > 12 ? hours - 12: hours;
		}

		public static String returnMeridiem(int hours) {
			return hours > 12 ? "PM" : "AM";
		}

		public static void displayDate(int dayOfWeek, int dayOfMonth, int month, int year) {
			String dayName = getDayName(dayOfWeek);
			String monthName = getMonthName(month);
			System.out.printf("%s, %s %d, %d", dayName, monthName, dayOfMonth, year);
		}

		public static int daysBetweenYears(int startingYear, int endingYear) {
			//number of days from 1/1/startingYear to 1/1/endingYear
			int numberOfDays = 0;
			for (int i = startingYear; i < endingYear; i++) {
				numberOfDays += isLeapYear(i) ? 366 : 365;
			}
			return numberOfDays;
		}

		public static void calculateAndDisplayDateTime() {
			int seconds = (int) (System.currentTimeMillis() / 1000);

			// the calculation of time - this should have been in a separate method, but there is no clean way to return all the values (seconds, minutes, hours, daysSinceUnixEpoch), so it had to be written right here in the parent method to reduce redundancy
			int minutes = seconds / 60;
			seconds %= 60;
			int hours = minutes / 60;
			minutes %= 60;

			int daysSinceUnixEpoch = hours / 24;
			hours %= 24;

			int daysSinceReferenceDay = daysSinceUnixEpoch + daysBetweenYears(REFERENCE_YEAR, UNIX_EPOCH_YEAR);

			displayTime(hours, minutes, seconds);
			calculateAndDisplayDate(daysSinceReferenceDay);
		}

		public static void main(String[] args) {
			calculateAndDisplayDateTime();
		}
	}