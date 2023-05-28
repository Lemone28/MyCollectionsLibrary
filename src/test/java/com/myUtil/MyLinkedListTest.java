package com.myUtil;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
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
    public void testSize() {
        MyLinkedList<String> list1 = new MyLinkedList<String>();
        MyLinkedList<String> list2 = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals(0, list1.size());
        assertEquals(3, list2.size());
    }

    @Test
    public void testiIEmpty() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGet() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("com", list.get(0));
        assertEquals(".", list.get(1));
        assertEquals("myUtil", list.get(2));
    }

    @Test
    public void testSet() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        list.set(0, "org");
        list.set(list.size()-1, "java");
        list.set(1, ",");

        assertEquals("org", list.get(0));
        assertEquals(",", list.get(1));
        assertEquals("java", list.get(2));
    }

    @Test
    public void testContains() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertTrue(list.contains("com"));
        assertTrue(list.contains("myUtil"));
        assertFalse(list.contains("java"));
    }

    @Test
    public void testAdd() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        assertEquals("One", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("Three", list.get(2));
    }

    @Test
    void testAddLast() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.addLast("One");
        list.addLast("Two");
        list.addLast("Three");

        assertEquals("One", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("Three", list.get(2));
    }

    @Test
    public void testIerator() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));
        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("com", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(".", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("myUtil", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testDescendingIterator() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));
        Iterator<String> iterator = list.descendingIterator();

        assertTrue(iterator.hasNext());
        assertEquals("myUtil", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(".", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("com", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testToArray() {
        String[] testArray = {"com", ".", "myUtil"};
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertArrayEquals(testArray, list.toArray());
    }

    @Test
    void testToArray2() {

    }

    @Test
    void addFirst() {
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
    void testAddIndex() {
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