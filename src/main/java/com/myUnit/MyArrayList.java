package com.myUnit;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_ELEMENTS = new Object[0];
    private Object[] elements;
    private int size;

    public MyArrayList() {
        this.elements = DEFAULT_ELEMENTS;
    }
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Incorrect capacity: " + capacity);
        }
        this.elements = (capacity > 0) ? new Object[capacity] : DEFAULT_ELEMENTS;
    }
    public MyArrayList(Collection<? extends T> coll) {}

    public void ensureCapacity(int minCapacity) {
        if (elements == DEFAULT_ELEMENTS
                && minCapacity <= DEFAULT_CAPACITY) return;

        if (minCapacity > elements.length) {


        }
    }

    private Object[] grow(int capacity) {
        if (elements.length == capacity)
            return elements;

        return null;
    }

    @Override
    public boolean some(Predicate<? super T> predicate) {
        return false;
    }

    @Override
    public boolean all(Predicate<? super T> predicate) {
        return false;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return elements.clone();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[])elements.clone();
    }

    @Override
    public boolean add(T t) {

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
