package com.myUtil;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface MyList<T> extends List<T> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<T> iterator();

    Object[] toArray();

    <T1> T1[] toArray(T1[] a);

    boolean add(T t);

    boolean remove(Object o);

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends T> c);

    boolean addAll(int index, Collection<? extends T> c);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);
    void clear();

    T get(int index);

    T set(int index, T element);

    void add(int index, T element);

    T remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int index);

    List<T> subList(int fromIndex, int toIndex);

    boolean some(Predicate<? super T> predicate);
    boolean all(Predicate<? super T> predicate);
    <R> void map(Function<? super T, ? extends R> function);
    <R> void map(Function<? super T, ? extends R> function, Predicate<? super T> predicate);
    void action(Consumer<? super T> consumer);
    void action(Consumer<? super T> consumer, Predicate<? super T> predicate);
}
