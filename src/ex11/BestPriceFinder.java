package ex11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    private final Executor executor =
		Executors.newFixedThreadPool(Math.min(shops.size(), 100),
									r -> {
										Thread t = new Thread(r);
										t.setDaemon(true);
										return t;
									});

    public List<String> findPrices(String product) {
    	return shops.stream()
    				 .map(shop -> shop.getPrice(product))
					 .map(Quote::parse)
			  		 .map(Discount::applyDisCount)
    				 .collect(toList());
    }

    public List<String> findPricesAsync(String product) {
    	return shops.parallelStream()
    				 .map(shop -> shop.getPrice(product))
					 .map(Quote::parse)
					 .map(Discount::applyDisCount)
    				 .collect(toList());
    }

    public List<String> findPricesLambda(String product) {
    	List<CompletableFuture<String>> priceFutures =
    			shops.stream()
    				.map(shop -> CompletableFuture.supplyAsync(
    								() -> shop.getPrice(product), executor))
					.map(future -> future.thenApply(Quote::parse))
					.map(future -> future.thenCompose(quote ->
									CompletableFuture.supplyAsync(
										() -> Discount.applyDisCount(quote), executor)))
    				.collect(toList());
    	return priceFutures.stream()
    					    .map(CompletableFuture::join)
    					    .collect(toList());
    }
}
