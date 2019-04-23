package ex12;

import java.time.LocalTime;

public class LocalTimePractice {
	public static void main(String[] args) {
		LocalTime time = LocalTime.of(13, 45, 20);
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();

		System.out.println("hour : " + hour);
		System.out.println("minute : " + minute);
		System.out.println("second : " + second);
	}
}
