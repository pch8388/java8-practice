package ex7;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Ex7Main {
	public static void main(String[] args) {
//		System.out.println("iterativeSum : " + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + "mesc");
//		System.out.println("sequentialSum : " + measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + "mesc");
//		System.out.println("parallelSum : " + measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + "mesc");
//		System.out.println("rangedSum : " + measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + "mesc");
//		System.out.println("parallelRangedSum : " + measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + "mesc");
//		System.out.println("sideEffectSum : " + measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000) + "mesc");
//		System.out.println("sideEffectParallelSum : " + measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000) + "mesc");
//		System.out.println("ForkJoin sum done in : "+ measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs");

		final String SENTENCE =
				" Nel  mezzo del cammin   di  nostra   vita " +
				"mi   ritroval   in  una   selva oscura" +
				" ch   la   dritta  via era   smarrita ";
		System.out.println("Found countWordsIteratively " + countWordsIteratively(SENTENCE) + " words");

		Stream<Character> stream = IntStream.range(0,  SENTENCE.length()).mapToObj(SENTENCE::charAt);
		System.out.println("Found countWords(stream) " + countWords(stream) + " words");

		Stream<Character> streamParallel = IntStream.range(0,  SENTENCE.length()).mapToObj(SENTENCE::charAt);
		System.out.println("Found countWords(stream.parallel()) " + countWords(streamParallel.parallel()) + " words");

		Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
		Stream<Character> spliterStream = StreamSupport.stream(spliterator, true);
		System.out.println("Found countWords(spliterStream) " + countWords(spliterStream) + " words");
	}

	public static long measureSumPerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for(int i = 0 ; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result : " + sum);
			if(duration < fastest) fastest = duration;
		}
		return fastest;
	}

	public static int countWordsIteratively(String s) {
		int counter = 0;
		boolean lastSpace = true;
		for (char c : s.toCharArray()) {
			if(Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace) {
					counter++;
				}
				lastSpace = false;
			}
		}
		return counter;
	}

	private static int countWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
												WordCounter::accumulate,
												WordCounter::combine);
		return wordCounter.getCounter();
	}
}
