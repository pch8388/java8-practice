package ex6;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

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
        String shortMenuReduce = menu.stream().map(Dish::getName).reduce((s1, s2) -> s1 + s2).get();
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

        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
            menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostColoricByType2 =
            menu.stream()
                .collect(groupingBy(Dish::getType,
                    collectingAndThen(
                        maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(mostColoricByType2);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
            menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> caloricLevelGroupFunction(dish),
                    toSet())));
        System.out.println(caloricLevelsByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
            menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> caloricLevelGroupFunction(dish),
                    toCollection(HashSet::new))));
        System.out.println(caloricLevelsByType2);

        System.out.println("============================ partitioning function ============================");
        Map<Boolean, List<Dish>> partitionedMenu =
            menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(vegetarianDishes);

        List<Dish> vegetarianDishes2 = menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println(vegetarianDishes2);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegeterianDishesByType =
            menu.stream().collect(
                partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(vegeterianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
            menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                    collectingAndThen(
                        maxBy(comparingInt(Dish::getCalories)),
                        Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);

        Map<Boolean, Map<Boolean, List<Dish>>> partitioningBy =
            menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));
        System.out.println(partitioningBy);

        System.out.println("============================ prime ============================");
        Map<Boolean, List<Integer>> partitionPrimes = partitionPrimes(100);
        System.out.println(partitionPrimes.get(true));


        System.out.println("============================ ToListCollector ============================");
        List<Dish> dishes = menu.stream().collect(new ToListCollector<>());
        System.out.println(dishes);

        System.out.println("============================ PrimeNumbersCollector ============================");
        Map<Boolean, List<Integer>> partitionPrimesWithCustom = partitionPrimesWithCustomCollector(100);
        System.out.println(partitionPrimesWithCustom.get(true));
    }

    private static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
            .collect(partitioningBy(candidate -> isPrime(candidate)));
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

    public enum CaloricLevel {DIET, NORMAL, FAT}
}

