package com.myUtil;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    public void testEmptyConstructor() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testConstructorWithInitialCollection() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));
        assertEquals(3, list.size());
    }

    @Test
    public void size() {
        MyLinkedList<String> list1 = new MyLinkedList<String>();
        MyLinkedList<String> list2 = new MyLinkedList<String>(List.of("com", ".", "myUtil"));
        assertEquals(0, list1.size());
        assertEquals(3, list2.size());
    }

    @Test
    void isEmpty() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        assertTrue(list.isEmpty());
    }

    @Test
    void contains() {

    }

    @Test
    void iterator() {
    }

    @Test
    void descendingIterator() {
    }

    @Test
    void toArray() {
    }

    @Test
    void testToArray() {
    }

    @Test
    void addFirst() {
    }

    @Test
    void add() {
    }

    @Test
    void addLast() {
    }

    @Test
    void offerFirst() {
    }

    @Test
    void offerLast() {
    }

    @Test
    void removeFirst() {
    }

    @Test
    void removeLast() {
    }

    @Test
    void pollFirst() {
    }

    @Test
    void pollLast() {
    }

    @Test
    void getFirst() {
    }

    @Test
    void element() {
    }

    @Test
    void peekFirst() {
    }

    @Test
    void getLast() {
    }

    @Test
    void peekLast() {
    }

    @Test
    void removeFirstOccurrence() {
    }

    @Test
    void removeLastOccurrence() {
    }

    @Test
    void offer() {
    }

    @Test
    void remove() {
    }

    @Test
    void poll() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void containsAll() {
    }

    @Test
    void addAll() {
    }

    @Test
    void push() {
    }

    @Test
    void peek() {
    }

    @Test
    void pop() {
    }

    @Test
    void testAddAll() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void retainAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testRemove1() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void listIterator() {
    }

    @Test
    void testListIterator() {
    }

    @Test
    void subList() {
    }
}