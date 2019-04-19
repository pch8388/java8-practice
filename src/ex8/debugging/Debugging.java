package ex8.debugging;

import ex8.testing.Point;

import java.util.Arrays;
import java.util.List;

public class Debugging {
    public static void main(String[] args) {
        List<Integer> points = Arrays.asList(1, 2, 3);
        points.stream().map(Debugging::divideByZero).forEach(System.out::println);
    }

    public static int divideByZero(int n) {
        return n / 0;
    }
}
