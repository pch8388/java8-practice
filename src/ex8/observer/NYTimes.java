package ex8.observer;

public class NYTimes implements Observer {
	@Override
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("money")) {
			System.out.println("Breaking new in NY! " + tweet);
		}
	}
}
