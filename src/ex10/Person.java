package ex10;

import java.util.Optional;

public class Person {
    int age;

    private Optional<Car> car;
    public Optional<Car> getCar() {return car;}
    public int getAge() {return age;}
}
