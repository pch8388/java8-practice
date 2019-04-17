package ex8.chain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainMain {
	public static void main(String[] args) {
		ProcessingObject<String> p1 = new HeaderTextProcessing();
		ProcessingObject<String> p2 = new SpellCheckerProcessing();

		p1.setSuccessor(p2);

		String result = p1.handle("Aren't labdas really sexy?!!");
		System.out.println(result);

		//in lambda
		UnaryOperator<String> headerProcessing = text -> "From Raoul, Mario and Alan : " + text;
		UnaryOperator<String> spellCheckerProcessing = text -> text.replaceAll("labda", "lambda");

		Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

		String resultLambda = pipeline.apply("Aren't labdas really sexy?!!");
		System.out.println(resultLambda);
	}
}
