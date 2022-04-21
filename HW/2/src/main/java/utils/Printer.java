package utils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Printer<T> {
    private List<T> items;
    private Consumer<T> consumer;

    public Printer(List<T> items, Consumer<T> consumer) {
        this.items = items;
        this.consumer = consumer;
    }

    public void print() {
        items.stream().forEach(consumer);
    }
}
