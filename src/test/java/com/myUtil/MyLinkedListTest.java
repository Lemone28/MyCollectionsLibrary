package com.myUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    public void testIsEmpty() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        assertTrue(list.isEmpty());
    }

    @Test
    void getFirst() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("com", list.getFirst());
    }
    @Test
    void getLast() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("myUtil", list.getLast());
    }

    @Test
    public void element() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("com", list.getFirst());
    }

    @Test
    void peek() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("com", list.getFirst());
    }
    @Test
    void peekFirst() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("com", list.getFirst());
    }
    @Test
    void peekLast() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("myUtil", list.getLast());
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
    void push() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.addFirst("One");
        list.addFirst("Two");
        list.addFirst("Three");

        assertEquals("Three", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("One", list.get(2));
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
    public void testAddFirst() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.addFirst("One");
        list.addFirst("Two");
        list.addFirst("Three");

        assertEquals("Three", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("One", list.get(2));
    }
    @Test
    public void testAdd() {
        MyLinkedList<String> list = new MyLinkedList<String>();

        assertEquals(true, list.add("One"));
        assertEquals(true, list.add("Two"));
        assertEquals(true, list.add("Three"));

        assertEquals("One", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("Three", list.get(2));
    }

    @Test
    public void testAddIndex() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add(0, "com");
        list.add(list.size(), "myUtil");
        list.add(1, ".");

        assertEquals("com", list.get(0));
        assertEquals(".", list.get(1));
        assertEquals("myUtil", list.get(2));
    }

    @Test
    public void testIterator() {
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
    public void testToArrayWithType() {
        String[] testArray = {"com", ".", "myUtil"};
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));
        Object[] array = list.toArray(String[]::new);

        assertEquals(testArray.getClass().componentType(), array.getClass().componentType());
        assertArrayEquals(testArray, list.toArray());
    }


    @Test
    public void testOfferFirst() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.offerFirst("One");
        list.offerFirst("Two");
        list.offerFirst("Three");

        assertEquals("Three", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("One", list.get(2));
    }
    @Test
    public void testOfferLast() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.addLast("One");
        list.addLast("Two");
        list.addLast("Three");

        assertEquals("One", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("Three", list.get(2));
    }

    @Test
    public void testOffer() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.addFirst("One");
        list.addFirst("Two");
        list.addFirst("Three");

        assertEquals("Three", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("One", list.get(2));
    }

    @Test
    public void testRemoveFirst() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        list.removeFirst();
        assertEquals(".", list.get(0));
        list.removeFirst();
        assertEquals("myUtil", list.get(0));
        list.removeFirst();
        assertTrue(list.isEmpty());
    }
    @Test
    public void testRemoveLast() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        list.removeLast();
        assertEquals(".", list.get(list.size()-1));
        list.removeLast();
        assertEquals("com", list.get(list.size()-1));
        list.removeLast();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testPollFirst() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("com", list.pollFirst());
        assertEquals(".", list.pollFirst());
        assertEquals("myUtil", list.pollFirst());
        assertTrue(list.isEmpty());
    }
    @Test
    public void testPollLast() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("myUtil", list.pollLast());
        assertEquals(".", list.pollLast());
        assertEquals("com", list.pollLast());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveFirstOccurrence() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil", "com"));

        assertTrue(list.removeFirstOccurrence("com"));
        assertEquals(".", list.get(0));
        assertTrue(list.removeFirstOccurrence("."));
        assertEquals("myUtil", list.get(0));
        assertTrue(list.removeFirstOccurrence("myUtil"));
        assertEquals("com", list.get(0));
    }
    @Test
    public void testRemoveLastOccurrence() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil", "com"));

        assertTrue(list.removeLastOccurrence("com"));
        assertEquals("myUtil", list.get(list.size()-1));
        assertTrue(list.removeLastOccurrence("myUtil"));
        assertEquals(".", list.get(list.size()-1));
        assertTrue(list.removeLastOccurrence("."));
        assertEquals("com", list.get(list.size()-1));
    }

    @Test
    public void testRemove() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("com", list.remove());
        assertEquals(".", list.remove());
        assertEquals("myUtil", list.remove());
        assertTrue(list.isEmpty());
    }
    @Test
    void testRemoveWithElement() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertTrue(list.remove("com"));
        assertTrue(list.remove("myUtil"));
        assertTrue(list.remove("."));
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveWithIndex() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("myUtil", list.remove(list.size()-1));
        assertEquals("com", list.remove(0));
        assertEquals(".", list.remove(0));

        assertTrue(list.isEmpty());
    }

    @Test
    void poll() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertEquals("com", list.poll());
        assertEquals(".", list.poll());
        assertEquals("myUtil", list.poll());
        assertTrue(list.isEmpty());
    }

    @Test
    void containsAll() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertTrue(list.containsAll(List.of("com", "myUtil")));
        assertFalse(list.containsAll(List.of("org", ".")));
    }

    @Test
    void testAddAllWithCollection() {
        MyLinkedList<String> list = new MyLinkedList<String>();

        list.addLast("com");
        list.addAll(List.of(".", "myUtil"));

        assertEquals("com", list.get(0));
        assertEquals(".", list.get(1));
        assertEquals("myUtil", list.get(2));
    }
    @Test
    void testAddAllWithIndexAndCollection() {
        MyLinkedList<String> list = new MyLinkedList<String>();

        list.addAll(list.size(), List.of("myUtil"));
        list.addAll(0, List.of("com"));
        list.addAll(list.size(), List.of("."));

        assertEquals(3, list.size());
        assertEquals("com", list.get(0));
        assertEquals("myUtil", list.get(1));
        assertEquals(".", list.get(2));
    }

    @Test
    void pop() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        list.removeFirst();
        assertEquals(".", list.get(0));
        list.removeFirst();
        assertEquals("myUtil", list.get(0));
        list.removeFirst();
        assertTrue(list.isEmpty());
    }

    @Test
    void removeAll() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertTrue(list.removeAll(List.of(".", "myUtil")));

        assertEquals(1, list.size());
        assertEquals("com", list.get(0));
    }

    @Test
    void retainAll() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertTrue(list.retainAll(List.of("myUtil")));

        assertEquals(1, list.size());
        assertEquals("myUtil", list.get(0));
    }

    @Test
    void clear() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    void indexOf() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil", "com"));

        assertEquals(0, list.indexOf("com"));
        assertEquals(1, list.indexOf("."));
    }
    @Test
    void lastIndexOf() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil", "com"));

        assertEquals(list.size()-1, list.lastIndexOf("com"));
        assertEquals(1, list.lastIndexOf("."));
    }

    @Test
    void testListIteratorWithIndex() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));
        ListIterator<String> iterator = list.listIterator(1);

        assertEquals(".", iterator.next());
    }

    @Test
    public void listIteratorNext() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));
        ListIterator<String> iterator = list.listIterator();

        assertEquals("com", iterator.next());
        assertEquals(".", iterator.next());
        assertEquals("myUtil", iterator.next());
    }

    @Test
    public void listIteratorPrevious() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));
        ListIterator<String> iterator = list.listIterator(list.size()-1);

        assertEquals("myUtil", iterator.previous());
        assertEquals(".", iterator.previous());
        assertEquals("com", iterator.previous());
    }

    @Test
    void subList() {
        MyLinkedList<String> list = new MyLinkedList<String>(List.of("com", ".", "myUtil"));

        assertArrayEquals(List.of().toArray(), list.subList(0,0).toArray());
        assertArrayEquals(List.of("com", ".").toArray(), list.subList(0,1).toArray());
        assertArrayEquals(List.of("com", ".", "myUtil").toArray(), list.subList(0,2).toArray());
    }
}