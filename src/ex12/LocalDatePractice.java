package ex12;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.*;

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

		System.out.println("withYear : " + date.withYear(2011));
		System.out.println("withDayOfMonth : " + date.withDayOfMonth(25));
		System.out.println("with : " + date.with(ChronoField.MONTH_OF_YEAR, 9));

		System.out.println("quize : " + date.with(ChronoField.MONTH_OF_YEAR, 9).plusYears(2).minusDays(10));

		LocalDate date1 = date.with(nextOrSame(DayOfWeek.SUNDAY));
		LocalDate date2 = date.with(lastDayOfMonth());
		System.out.println("date1 : " + date1);
		System.out.println("date2 : " + date2);

		date = date.with(new NextWorkingDay());
		System.out.println("next working day date : " + date);

		date = date.with(temporal -> {
				DayOfWeek dayOfWeek =
						DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
				int dayToAdd = 1;
				if (dayOfWeek == DayOfWeek.FRIDAY) dayToAdd = 3;
				else if (dayOfWeek == DayOfWeek.SATURDAY) dayToAdd = 2;
				return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});

		System.out.println("next working day date in Lambda : " + date);

		TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
					temporal -> {
						DayOfWeek dayOfWeek =
								DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
						int dayToAdd = 1;
						if (dayOfWeek == DayOfWeek.FRIDAY) dayToAdd = 3;
						else if (dayOfWeek == DayOfWeek.SATURDAY) dayToAdd = 2;
						return temporal.plus(dayToAdd, ChronoUnit.DAYS);
					});

		System.out.println("TemporalAdjuster nextWorkingDay : " + date.with(nextWorkingDay));
	}

}
