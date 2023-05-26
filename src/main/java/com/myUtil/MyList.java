package com.myUtil;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface MyList<T> extends List<T> {
    default boolean some(Predicate<? super T> predicate) {
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    default boolean all(Predicate<? super T> predicate) {
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()) {
            if (!predicate.test(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    default <R> void map(Function<? super T, ? extends R> function) {
        ListIterator<T> iterator = listIterator();
        while(iterator.hasNext()) {
            T oldElement = iterator.next();
            iterator.previous();
            iterator.set((T)function.apply(oldElement));
        }
    }
    @SuppressWarnings("unchecked")
    default <R> void map(Function<? super T, ? extends R> function, Predicate<? super T> predicate) {
        ListIterator<T> iterator = listIterator();
        while(iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.test(element)) {
                iterator.previous();
                iterator.set((T)function.apply(element));
            }
        }
    }
    default void action(Consumer<? super T> consumer) {
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()) {
            consumer.accept(iterator.next());
        }
    }
    default void action(Consumer<? super T> consumer, Predicate<? super T> predicate) {
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.test(element)) {
                consumer.accept(element);
            }
        }
    }
}
