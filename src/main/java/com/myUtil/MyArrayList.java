package com.myUtil;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_ELEMENTS = new Object[0];

    private Object[] elements;
    private int size;

    public MyArrayList() {
        this.elements = DEFAULT_ELEMENTS;
    }
    public MyArrayList(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Incorrect capacity: " + capacity);
        this.elements = (capacity > 0) ?
                new Object[capacity] :
                DEFAULT_ELEMENTS;
    }
    public MyArrayList(Collection<? extends T> coll) {
        Objects.requireNonNull(coll);
        Object[] otherElements = coll.toArray();
        this.elements = Arrays.copyOf(otherElements, otherElements.length);
        size = coll.size();
    }


    static class MyIterator<T> implements Iterator<T> {
        protected final MyArrayList<T> list;
        protected int currentIndex;

        public MyIterator(MyArrayList<T> list, int start) {
            if (list.size != 0)
                Objects.checkIndex(start, list.size());
            this.list = list;
            this.currentIndex = start;
        }
        public MyIterator(MyArrayList<T> list) {
            this(list, 0);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < list.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list.get(currentIndex++);
        }
        @Override
        public void remove() {
            list.remove(currentIndex - 1);
        }
    }

    static class MyListIterator<T> extends MyIterator<T> implements ListIterator<T> {
        public MyListIterator(MyArrayList<T> list, int start) {
            super(list, start);
        }
        public MyListIterator(MyArrayList<T> list) {
            super(list);
        }
        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }
        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            return list.get(--currentIndex);
        }
        @Override
        public int nextIndex() {
            return (hasNext()) ? currentIndex + 1 : list.size();
        }
        @Override
        public int previousIndex() {
            return (hasPrevious()) ? currentIndex - 1 : -1;
        }
        @Override
        public void set(T t) {
            list.set(currentIndex, t);
        }
        @Override
        public void add(T t) {
            list.add(currentIndex, t);
        }
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
    public boolean add(T t) {
        if (size == elements.length)
            grow();
        elements[size++] = t;
        return true;
    }

    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size);

        if (size == elements.length)
            grow();
        System.arraycopy(elements, index, elements, index+1, size-index);
        elements[index] = element;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T)elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldElement = (T)elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T element = (T)elements[index];
        System.arraycopy(elements, index+1, elements, index, size-index-1);
        size--;
        return element;
    }

    @Override
    public boolean contains(Object o) {
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()) {
            if (iterator.next().equals(o)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = size-1; i >= 0; i--) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        while(iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll((size == 0) ? 0 : size - 1, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Objects.checkIndex(index, size);
        Object[] otherElements = Objects.requireNonNull(c).toArray();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        elements = (T[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[])Array.newInstance(a.getClass().componentType(), size);
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        return false;
    }

    public void ensureCapacity(int minCapacity) {
        if (elements == DEFAULT_ELEMENTS
                && minCapacity <= DEFAULT_CAPACITY) return;
        else if (minCapacity > elements.length) {
            resetCapacity(minCapacity);
        }
    }

    private void grow() {
        resetCapacity(size+1);
    }
    private void resetCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > 0 || elements != DEFAULT_ELEMENTS) {
            final int newCapacity = size * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        } else {
            elements = new Object[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>(this);
    }
    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator<T>(this);
    }
    @Override
    public ListIterator<T> listIterator(int index) {
        return new MyListIterator<T>(this, index);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> subList(int fromIndex, int toIndex) {
        MyArrayList<T> subList = new MyArrayList<T>(Objects.checkIndex(toIndex, size) -
                        Objects.checkIndex(fromIndex, size));
        for(int i = fromIndex; i <= toIndex; i++) {
            subList.add((T)elements[i]);
        }
        return subList;
    }
}
