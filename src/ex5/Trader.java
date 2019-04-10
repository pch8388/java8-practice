package ex5;

public class Trader {
	private final String name;
	private final String city;

	public Trader(String anme, String city) {
		super();
		this.name = anme;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "Trader [name=" + name + ", city=" + city + "]";
	}

}
