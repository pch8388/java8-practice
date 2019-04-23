package ex11;

import static ex11.Util.delay;

public class ExchangeService {
	public enum Money {
		USD(1.0), EUR(1.35387), GBP(1.69715), CDA(.92106), MXN(.07693);

		private final double rate;

		Money(double rate) {
			this.rate = rate;
		}
	}

	public static double getRate(Money source, Money destination) {
		return getRateWithDelay(source, destination);
	}

	private static double getRateWithDelay(Money source, Money destination) {
		delay();
		return destination.rate / source.rate;
	}
}
