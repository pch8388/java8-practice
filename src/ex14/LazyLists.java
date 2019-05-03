package ex14;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyLists {
    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
        int two = primes(numbers).head();
        int three = primes(numbers).tail().head();
        int five = primes(numbers).tail().tail().head();

        System.out.println(two + " " + three + " " + five);
    }

    static <T> void printAll(MyList<T> list) {
        if (list.isEmpty()) return;
        System.out.println(list.head());
        printAll(list.tail());
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
            numbers.head(),
            () -> primes(numbers.tail()
                                .filter(n -> n % numbers.head() != 0))
        );
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    static class LazyList<T> implements MyList<T> {
        final T head;
        final Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail.get();
        }

        public boolean isEmpty() {
            return false;
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ?
                    this :
                    p.test(head()) ?
                            new LazyList<>(head(), () -> tail().filter(p)) :
                            tail().filter(p);
        }
    }

    interface MyList<T> {
        T head();

        MyList<T> tail();

        default boolean isEmpty() {
            return true;
        }

        MyList<T> filter(Predicate<T> p);
    }

    static class MyLinkedList<T> implements MyList<T> {
        private final T head;
        private final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        public MyLinkedList<T> filter(Predicate<T> p) {
            return this;
        }
    }

    static class Empty<T> implements MyList<T> {
        public T head() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        public Empty<T> filter(Predicate<T> p) {
            return this;
        }
    }
}




