package ex11;

import static ex11.Util.*;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
	private final Random random = new Random();
	private String name;


	public Shop(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPrice(String product) {
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[
									random.nextInt(Discount.Code.values().length)];

		return String.format("%s:%.2f:%s", name, price, code);
	}

	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread( () -> {
						try {
							double price = calculatePrice(product);
							futurePrice.complete(price);
						} catch (Exception ex) {
							futurePrice.completeExceptionally(ex);
						}
		}).start();
		return futurePrice;
	}

	public Future<Double> getPriceAsyncLambda(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}


	private double calculatePrice(String product) {
		delay();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}
}
