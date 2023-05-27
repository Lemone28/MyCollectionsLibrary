package com.myUtil;

import javax.lang.model.element.Element;
import java.lang.reflect.Array;
import java.util.*;

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
        this.elements = DEFAULT_ELEMENTS;
        if (!coll.isEmpty()) {
            addAll(coll);
        }
    }
    public MyArrayList(T...elements) {
        this.elements = DEFAULT_ELEMENTS;
        setElements(elements);
    }

    static int checkIndex(int i, int size, boolean isIgnoreIndexEqualsSize) throws IllegalArgumentException {
        if (i < 0 || i > size || (i == size && !isIgnoreIndexEqualsSize)) {
            throw new IllegalArgumentException("Illegal index: " + i);
        }
        return i;
    }

    private void setElements(Object[] array) {
        int newLength = array.length;
        ensureCapacity(newLength);
        System.arraycopy(array, 0, elements, 0, array.length);
        size = newLength;
    }
    static class MyIterator<T> implements Iterator<T> {
        protected final MyArrayList<T> list;
        protected int currentIndex;
        protected boolean isStepNext;

        public MyIterator(MyArrayList<T> list, int start) {
            this.list = list;
            this.currentIndex = MyArrayList.checkIndex(start, list.size(), list.isEmpty());
            this.isStepNext = start != 0;
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
            isStepNext = true;
            return list.get(currentIndex++);
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
        @Override
        public void remove() {
            if (!isStepNext) {
                throw new IllegalStateException("No element to remove or next() has not been called");
            }
            list.remove(currentIndex-1);
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
        checkIndex(index, size, true);
        if (size == elements.length)
            grow();
        System.arraycopy(elements, index, elements, index+1, size-index);
        elements[index] = element;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index, size, false);
        return (T)elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index, size, false);
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
        checkIndex(index, size, false);
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
    public boolean addAll(Collection<? extends T> coll) {
        return addAll(size, coll);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> coll) {
        Objects.requireNonNull(coll);
        checkIndex(index, size, true);

        Object[] otherElements = coll.toArray();
        int newCapacity = otherElements.length + elements.length;
        if (newCapacity == 0) return false;

        Object[] oldElements = elements;
        elements = new Object[newCapacity];

        System.arraycopy(oldElements, 0, elements, 0, index);
        System.arraycopy(otherElements, 0, elements, index, coll.size());
        System.arraycopy(oldElements, index, elements, index + coll.size(), size - index);
        size += otherElements.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        Objects.requireNonNull(coll);

        int sizeBefore = size;
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < size; readIndex++) {
            Object element = elements[readIndex];
            if (!coll.contains(element)) {
                elements[writeIndex++] = element;
            }
        }

        int newSize = writeIndex;
        if (newSize != sizeBefore) {
            for (int i = newSize; i < size; i++) {
                elements[i] = null;
            }
            size = newSize;
            return true;
        }

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
    public boolean retainAll(Collection<?> coll) {
        Objects.requireNonNull(coll);
        boolean modified = false;

        for(int i = 0; i < size; i++) {
            if (!coll.contains(elements[i])) {
                remove(i--);
                modified = true;
            }
        }

        return modified;
    }

    private void grow() {
        ensureCapacity(size+1);
    }
    public void ensureCapacity(int minCapacity) {
        int currentCapacity = elements.length;
        if (minCapacity > currentCapacity) {
            int newCapacity = currentCapacity + (currentCapacity >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
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
        if (isEmpty() || fromIndex == toIndex) {
            return new MyArrayList<T>();
        } else if (Objects.checkIndex(fromIndex, size) >
                Objects.checkIndex(toIndex, size)) {
            throw new IllegalArgumentException("The start index is greater than the end index");
        }
        MyArrayList<T> subList = new MyArrayList<T>(toIndex - fromIndex);
        for(int i = fromIndex; i <= toIndex; i++) {
            subList.add((T)elements[i]);
        }
        return subList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        else if (!(o instanceof MyList)) return false;
        return equalsList((MyList<?>)o);
    }

    private boolean equalsList(MyList<?> otherList) {
        if (size != otherList.size())
            return false;
        Object[] otherArray = otherList.toArray();
        for(int i = 0; i < size; i++) {
            if (!Objects.equals(elements[i], otherArray[i]) ||
                    elements[i].getClass() != otherArray[i].getClass()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for(int i = 0; i < size; i++) {
            if (builder.length() > 1) builder.append(", ");
            builder.append(elements[i]);
        }
        builder.append("]");
        return builder.toString();
    }
}
