package utils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Finder<T> implements IFindData<T> {
    private List<T> items;
    private Predicate<T> predicate;
    private Comparator<T> comparator;

    public Finder(List<T> items, Predicate<T> predicate) {
        this.items = items;
        this.predicate = predicate;
    }

    public Finder(List<T> items, Comparator<T> comparator) {
        this.items = items;
        this.comparator = comparator;
    }

    @Override
    public T searchFirstElement() {
        List<T> results = items.stream().filter(predicate).collect(Collectors.toList());
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    @Override
    public  List<T> searchAllElements() {
        List<T> results = items.stream().filter(predicate).collect(Collectors.toList());
        if (!results.isEmpty()) {
            return results;
        }
        return null;
    }

    @Override
    public T searchMaxElement() {
        T result = items.stream().max(comparator).get();
        return result;
    }

    @Override
    public T searchMinElement() {
        T result = items.stream().min(comparator).get();
        return result;
    }
}
