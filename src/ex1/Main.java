package ex1;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {


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

        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        boolean isHealthy = menu.stream()
                                .allMatch(d -> d.getCalories() < 1000);

        System.out.println("The menu allMatch is " + isHealthy);

        boolean isHealthy2 = menu.stream()
                                 .noneMatch(d -> d.getCalories() >= 1000);

        System.out.println("The menu noneMatch is " + isHealthy2);

        menu.stream()
            .filter(Dish::isVegetarian)
            .findAny()
            .ifPresent(System.out::println);

        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                           .map(x -> x*x)
                           .filter(x -> x % 3 == 0)
                           .findFirst();
        firstSquareDivisibleByThree.ifPresent(System.out::println);

        Integer sumNumber = someNumbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("reduce sumNumbers is " + sumNumber);
        Integer sumNumberMethodReference = someNumbers.stream().reduce(0, Integer::sum);
        System.out.println("reduce sumNumberMethodReference is " + sumNumberMethodReference);

        System.out.print("Integer max number is ");
        someNumbers.stream().reduce(Integer::max).ifPresent(System.out::println);

        int count = menu.stream()
                        .map(d -> 1)
                        .reduce(0, (a, b) -> a + b);
        System.out.println("count is : "+ count);
    }
}
