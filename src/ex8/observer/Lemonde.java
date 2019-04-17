package ex8.observer;

public class Lemonde implements Observer {
	@Override
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("wine")) {
			System.out.println("Today cheese, wine and news! " + tweet);
		}
	}
}
