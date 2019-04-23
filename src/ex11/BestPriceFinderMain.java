package ex11;

import java.util.concurrent.CompletableFuture;

public class BestPriceFinderMain {
	public static void main(String[] args) {
		final BestPriceFinder bestPriceFinder = new BestPriceFinder();

		long start = System.nanoTime();
		System.out.println(bestPriceFinder.findPrices("myPhone27S"));
		long duration = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Done in " + duration + " mecs");

		long startAsync = System.nanoTime();
		System.out.println(bestPriceFinder.findPricesAsync("myPhone27S"));
		long durationAsync = ((System.nanoTime() - startAsync) / 1_000_000);
		System.out.println("Async Done in " + durationAsync + " mecs");

		long startLambda = System.nanoTime();
		System.out.println(bestPriceFinder.findPricesLambda("myPhone27S"));
		long durationLambda = ((System.nanoTime() - startLambda) / 1_000_000);
		System.out.println("Lambda Done in " + durationLambda + " mecs");

		long startUSD = System.nanoTime();
		System.out.println(bestPriceFinder.findPricesInUSD("myPhone27S"));
		long durationUSD = ((System.nanoTime() - startUSD) / 1_000_000);
		System.out.println("USD Done in " + durationUSD + " mecs");

		long startStream = System.nanoTime();
		@SuppressWarnings("rawtypes")
		CompletableFuture[] futures = bestPriceFinder.findPricesStream("myPhone27S")
				.map(f -> f.thenAccept(s -> System.out.println(s + "(done in " +((System.nanoTime() - startStream) / 1_000_000) + " mecs)")))
				.toArray(size -> new CompletableFuture[size]);
		CompletableFuture.allOf(futures).join();
		System.out.println("All shops have now responded in " + ((System.nanoTime() - startStream) / 1_000_000) + "mecs");
		long durationStream = ((System.nanoTime() - startStream) / 1_000_000);
		System.out.println("Stream Done in " + durationStream + " mecs");
	}
}
