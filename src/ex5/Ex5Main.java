package ex5;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Ex5Main {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
					new Transaction(brian, 2011, 300),
					new Transaction(raoul, 2012, 1000),
					new Transaction(raoul, 2011, 400),
					new Transaction(mario, 2012, 710),
					new Transaction(mario, 2012, 700),
					new Transaction(alan, 2012, 950)
				);

		System.out.println("============================ ex 1 ============================");
		List<Transaction> ex1 = transactions.stream()
											.filter(t -> t.getYear() == 2011)
											.sorted(Comparator.comparing(Transaction::getValue))
											.collect(toList());
		ex1.stream().forEach(System.out::println);

		System.out.println("============================ ex 2 ============================");
		transactions.stream()
					.map(t -> t.getTrader().getCity())
					.distinct()
					.forEach(System.out::println);

		System.out.println("============================ ex 3 ============================");
		transactions.stream()
					.map(m -> m.getTrader())
					.filter(t -> t.getCity().equals("Cambridge"))
					.distinct()
					.sorted(Comparator.comparing(Trader::getName))
					.forEach(System.out::println);


		System.out.println("============================ ex 4 ============================");
		System.out.println(transactions.stream()
					.map(m -> m.getTrader().getName())
					.distinct()
					.sorted()
					.reduce("", (n1, n2) -> n1 + " " +n2)
				);

		System.out.println("============================ ex 5 ============================");
		transactions.stream()
					.filter(t -> t.getTrader().getCity() == "Milan")
					.findAny()
					.ifPresent(i -> System.out.println("Milan Trader : " + i));

		transactions.stream()
					.anyMatch(t -> t.getTrader().getCity().equals("Milan"));


		System.out.println("============================ ex 6 ============================");
		transactions.stream()
					.filter(t -> "Cambridge".equals(t.getTrader().getCity()))
					.map(Transaction::getValue)
					.forEach(System.out::println);

		System.out.println("============================ ex 7 ============================");
		transactions.stream()
					.max(Comparator.comparing(Transaction::getValue))
					.ifPresent(System.out::println);

		System.out.println("============================ ex 8 ============================");
		transactions.stream()
					.min(Comparator.comparing(Transaction::getValue))
					.ifPresent(System.out::println);
	}
}
