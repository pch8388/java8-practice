package ex13;

import java.util.stream.LongStream;

public class FactorialPractice {
    static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n-1);
    }

    static long factorialStreams(long n) {
        return LongStream.rangeClosed(1, n)
                        .reduce(1, (a, b) -> a * b);
    }

    static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n-1);
    }

    public static void main(String[] args) {
        System.out.println(factorialIterative(10));
        System.out.println(factorialRecursive(10));
        System.out.println(factorialStreams(10));
        System.out.println(factorialTailRecursive(10));
    }
}
