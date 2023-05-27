package com.myUtil;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractMyList<T> extends AbstractList<T>
        implements MyList<T> {
    protected AbstractMyList() {}

    @Override
    public boolean some(Predicate<? super T> predicate) {
        ListIterator<? extends T> iterator = listIterator();
        while(iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean all(Predicate<? super T> predicate) {
        ListIterator<? extends T> iterator = listIterator();
        while(iterator.hasNext()) {
            if (!predicate.test(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <R> void map(Function<? super T, ? extends R> function) {

    }

    @Override
    public <R> void map(Function<? super T, ? extends R> function, Predicate<? super T> predicate) {

    }

    @Override
    public void action(Consumer<? super T> consumer) {

    }

    @Override
    public void action(Consumer<? super T> consumer, Predicate<? super T> predicate) {

    }
}
