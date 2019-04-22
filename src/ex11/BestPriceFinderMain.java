package ex11;

public class BestPriceFinderMain {
	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(new BestPriceFinder().findPrices("myPhone27S"));
		long duration = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Done in " + duration + " mecs");

		long startAsync = System.nanoTime();
		System.out.println(new BestPriceFinder().findPricesAsync("myPhone27S"));
		long durationAsync = ((System.nanoTime() - startAsync) / 1_000_000);
		System.out.println("Async Done in " + durationAsync + " mecs");

		long startLambda = System.nanoTime();
		System.out.println(new BestPriceFinder().findPricesLambda("myPhone27S"));
		long durationLambda = ((System.nanoTime() - startLambda) / 1_000_000);
		System.out.println("Lambda Done in " + durationLambda + " mecs");
	}
}
