package ex12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

public class LocalDatePractice {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2014, 3, 18);
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();

		System.out.println("year : " + year);
		System.out.println("month : " + month);
		System.out.println("day : " + day);
		System.out.println("dow : " + dow);
		System.out.println("len : " + len);
		System.out.println("leap : " + leap);

		LocalDate now = LocalDate.now();
		int nowYear = now.get(ChronoField.YEAR);
		int nowMonth = now.get(ChronoField.MONTH_OF_YEAR);
		int nowDay = now.get(ChronoField.DAY_OF_MONTH);

		System.out.println("nowYear : " + nowYear);
		System.out.println("nowMonth : " + nowMonth);
		System.out.println("nowDay : " + nowDay);
	}

}
