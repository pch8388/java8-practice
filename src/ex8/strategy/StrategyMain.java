package ex8.strategy;

public class StrategyMain {
	public static void main(String[] args) {
		Validator numbericValidator = new Validator(new IsNumberic());
		System.out.println("numbericValidator : " + numbericValidator.validate("aaaa"));
		Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
		System.out.println("lowerCaseValidator : " + lowerCaseValidator.validate("bbbb"));

		// in lambda
		Validator numbericValidatorLambda = new Validator(s -> s.matches("\\d+"));
		System.out.println("numbericValidatorLambda : " + numbericValidatorLambda.validate("aaaa"));
		Validator lowerCaseValidatorLambda = new Validator(s -> s.matches("[a-z]+"));
		System.out.println("lowerCaseValidatorLambda : " + lowerCaseValidatorLambda.validate("bbbb"));

	}
}
