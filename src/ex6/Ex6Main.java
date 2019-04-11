package ex6;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                new Dish("salmon", false, 450, Dish.Type.FISH),
                new Dish("fish", false, 420, Dish.Type.FISH)
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

        System.out.println("============================ joining ============================");
        System.out.println(menu.stream().map(Dish::getName).collect(joining()));
        System.out.println(menu.stream().map(Dish::getName).collect(joining(", ")));

        System.out.println("============================ reducing ============================");
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalories);
        int totalCalories2 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories2);

        Optional<Dish> mostCalorieDish = menu.stream().collect(reducing((i, j) -> i.getCalories() > j.getCalories() ? i : j));
        mostCalorieDish.ifPresent(d -> System.out.println(d.getCalories()));

        System.out.println("============================ ex 6.1 ============================");
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);
        String shortMenuReduce = menu.stream().map(Dish::getName).reduce((s1, s2) -> s1+s2).get();
        System.out.println(shortMenuReduce);
        String shortMenuReduce2 = menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
        System.out.println(shortMenuReduce2);

        System.out.println("============================ groupingBy ============================");
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(Ex6Main::caloricLevelGroupFunction));
        System.out.println(dishesByCaloricLevel);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                  groupingBy(Dish::getType,
                          groupingBy(Ex6Main::caloricLevelGroupFunction))
                );
        System.out.println(dishesByTypeCaloricLevel);
	}

        private static CaloricLevel caloricLevelGroupFunction(Dish dish) {
                if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                } else {
                        return CaloricLevel.FAT;
                }
        }

        public enum CaloricLevel { DIET, NORMAL, FAT}
}

