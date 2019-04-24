package ex12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateTimeFormatterPractice {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.of(2014, 3, 18);
		String formattedDate = date1.format(formatter);
		LocalDate date2 = LocalDate.parse(formattedDate, formatter);

		System.out.println("date1 : " + date1);
		System.out.println("formattedDate : " + formattedDate);
		System.out.println("date2 : " + date2);

		DateTimeFormatter italianFormatter =
				DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
		LocalDate date3 = LocalDate.of(2014, 3, 18);
		String formattedDate1 = date3.format(italianFormatter);
		LocalDate date4 = LocalDate.parse(formattedDate1, italianFormatter);

		System.out.println("date3 : " + date3);
		System.out.println("formattedDate1 : " + formattedDate1);
		System.out.println("date4 : " + date4);

		DateTimeFormatter italianFormatter2 = new DateTimeFormatterBuilder()
				.appendText(ChronoField.DAY_OF_MONTH)
				.appendLiteral(". ")
				.appendText(ChronoField.MONTH_OF_YEAR)
				.appendLiteral(" ")
				.appendText(ChronoField.YEAR)
				.parseCaseInsensitive()
				.toFormatter(Locale.ITALIAN);

		LocalDate date5 = LocalDate.of(2014, 3, 18);
		String formattedDate2 = date3.format(italianFormatter);
		LocalDate date6 = LocalDate.parse(formattedDate2, italianFormatter2);

		System.out.println("date5 : " + date5);
		System.out.println("formattedDate2 : " + formattedDate2);
		System.out.println("date6 : " + date6);
	}
}
