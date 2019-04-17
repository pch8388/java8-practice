package ex8.strategy;

public class IsNumberic implements ValidationStrategy {
	@Override
	public boolean execute(String s) {
		return s.matches("\\d+");
	}
}
