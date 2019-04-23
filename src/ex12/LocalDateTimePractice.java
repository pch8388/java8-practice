package ex12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimePractice {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2014, 3, 18);
		LocalTime time = LocalTime.of(13, 45, 20);

		LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, time);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);

		System.out.println("dt1 : " + dt1);
		System.out.println("dt2 : " + dt2);
		System.out.println("dt3 : " + dt3);
		System.out.println("dt4 : " + dt4);
		System.out.println("dt5 : " + dt5);

		LocalDate date1 = dt1.toLocalDate();
		LocalTime time1 = dt1.toLocalTime();

		System.out.println("date1 : " + date1);
		System.out.println("time1 : " + time1);
	}

}
