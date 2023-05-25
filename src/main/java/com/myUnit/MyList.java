package com.myUnit;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface MyList<T> extends List<T> {
    boolean some(Predicate<? super T> predicate);
    boolean all(Predicate<? super T> predicate);
    <R> void map(Function<? super T, ? extends R> function);
    <R> void map(Function<? super T, ? extends R> function, Predicate<? super T> predicate);
    void action(Consumer<? super T> consumer);
    void action(Consumer<? super T> consumer, Predicate<? super T> predicate);
}
