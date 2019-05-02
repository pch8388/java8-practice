package ex13;

import java.util.function.DoubleUnaryOperator;

public class Currying {

    private static DoubleUnaryOperator curriedConverter(double f, double b) {
        return x -> x * f + b;
    }

    public static void main(String[] args) {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        double ctoF = convertCtoF.applyAsDouble(30);
        double gbp = convertUSDtoGBP.applyAsDouble(1000);
        double kmToMi = convertKmtoMi.applyAsDouble(300);

        System.out.println(ctoF);
        System.out.println(gbp);
        System.out.println(kmToMi);
    }
}
