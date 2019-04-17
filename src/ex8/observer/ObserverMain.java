package ex8.observer;

public class ObserverMain {
	public static void main(String[] args) {
		Feed f = new Feed();
		f.registerObserver(new NYTimes());
		f.registerObserver(new Guardian());
		f.registerObserver(new Lemonde());
		f.notifyObservers("The queen said her favourite book is Java 8 in Action");

		// in lambda
		Feed fLambda = new Feed();
		fLambda.registerObserver(tweet -> {
			if(tweet != null && tweet.contains("money")) {
				System.out.println("Breaking new in NY! " +tweet);
			}
		});
		fLambda.registerObserver(tweet -> {
			if(tweet != null && tweet.contains("queen")) {
				System.out.println("Yet another news in London... " +tweet);
			}
		});
		fLambda.notifyObservers("The queen said her favourite book is Java 8 in Action");
	}
}
