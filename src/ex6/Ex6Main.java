package ex6;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

import ex1.Dish;

public class Ex6Main {
	public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        System.out.println("============================ howManyDishes ============================");
        long howManyDishes = menu.stream().collect(counting());
        System.out.println(howManyDishes);

        System.out.println("============================ maxBy ============================");
        menu.stream().collect(maxBy(comparingInt(Dish::getCalories))).ifPresent(System.out::println);

        System.out.println("============================ summingInt ============================");
        System.out.println(menu.stream().collect(summingInt(Dish::getCalories)));

        System.out.println("============================ summarizingInt ============================");
        System.out.println(menu.stream().collect(summarizingInt(Dish::getCalories)));
	}
}
