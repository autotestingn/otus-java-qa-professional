package utils;

import java.util.List;

public interface IFindData<T> {
    T searchFirstElement();
    List<T> searchAllElements();
    T searchMaxElement();
    T searchMinElement();
}
