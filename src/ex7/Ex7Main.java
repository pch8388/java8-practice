package ex7;

import java.util.function.Function;

public class Ex7Main {
	public static void main(String[] args) {
		System.out.println("iterativeSum : " + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + "mesc");
		System.out.println("sequentialSum : " + measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + "mesc");
		System.out.println("parallelSum : " + measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + "mesc");
		System.out.println("rangedSum : " + measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + "mesc");
		System.out.println("parallelRangedSum : " + measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + "mesc");
		System.out.println("sideEffectSum : " + measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000) + "mesc");
		System.out.println("sideEffectParallelSum : " + measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000) + "mesc");
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
}
