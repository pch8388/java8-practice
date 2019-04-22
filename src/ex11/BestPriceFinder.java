package ex11;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    public List<String> findPrices(String product) {
    	return shops.stream()
    				 .map(shop -> String.format("%s price is %.2f",
    						 				 shop.getName(), shop.getPrice(product)))
    				 .collect(toList());
    }

    public List<String> findPricesAsync(String product) {
    	return shops.parallelStream()
    				 .map(shop -> String.format("%s price is %.2f",
    						 					shop.getName(), shop.getPrice(product)))
    				 .collect(toList());
    }

    public List<String> findPricesLambda(String product) {
    	List<CompletableFuture<String>> priceFutures =
    			shops.stream()
    				.map(shop -> CompletableFuture.supplyAsync(
    								() -> shop.getName() + " price is " +
    									  shop.getPrice(product)))
    				.collect(toList());
    	return priceFutures.stream()
    					    .map(CompletableFuture::join)
    					    .collect(toList());
    }
}
