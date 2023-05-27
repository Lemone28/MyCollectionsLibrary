package com.myUtil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayListTest {
    @Test
    public void testEmptyConstructor() {
        MyList<Integer> list = new MyArrayList<Integer>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testConstructorWithInitialCapacity() {
        MyList<Integer> list = new MyArrayList<Integer>(10);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testConstructorWithNotEmptyInitialCollection() {
        MyList<Integer> list = new MyArrayList<Integer>(List.of(1,2,3,4));
        assertFalse(list.isEmpty());
        assertTrue(list.containsAll(List.of(1,2,3,4)));
    }

    @Test
    public void testConstructorWithEmptyInitialCollection() {
        MyList<Integer> list = new MyArrayList<Integer>(new MyArrayList<Integer>());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testConstructorWithElements() {
        MyList<Integer> list = new MyArrayList<Integer>(1, 2, 3);
        assertEquals(3, list.size());

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testSize() {
        MyList<Integer> list1 = new MyArrayList<Integer>();
        MyList<Integer> list2 = new MyArrayList<Integer>(15);
        MyList<Integer> list3 = new MyArrayList<Integer>(List.of(1,2,3,4,5));
        assertEquals(0, list1.size());
        assertEquals(0, list2.size());
        assertEquals(5, list3.size());
    }

    @Test
    public void testIsEmpty() {
        MyList<Integer> list = new MyArrayList<Integer>();
        assertTrue(list.isEmpty());
        list.add(25);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGet() {
        MyList<Integer> list = new MyArrayList<Integer>(List.of(13,6,3,2));
        assertEquals(13, list.get(0));
        assertEquals(6, list.get(1));
        assertEquals(2, list.get(list.size() - 1));
    }

    @Test
    public void testSet() {
        MyList<Integer> list = new MyArrayList<Integer>(List.of(13,6,3,2));

        list.set(0, -1);
        list.set(1, 5);
        list.set(list.size()-1, 1);

        assertEquals(-1, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(1, list.get(list.size()-1));
    }

    @Test
    public void testAdd() {
        MyList<Integer> list = new MyArrayList<Integer>();
        list.add(18);
        assertEquals(1, list.size());

        int indexElement = list.indexOf(18);
        assertEquals(0, indexElement);
    }

    @Test
    public void testAddOnZeroIndex() {
        MyList<Integer> list = new MyArrayList<Integer>();

        list.add(0, 25);
        assertEquals(1, list.size());
        assertEquals(0, list.indexOf(25));

        list.add(0, 13);
        assertEquals(2, list.size());
        assertEquals(0, list.indexOf(13));
    }

    @Test
    public void testAddOnOtherIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);

        list.add(1, 25);
        assertEquals(4, list.size());
        assertEquals(25, list.get(1));

        list.add(2, 13);
        assertEquals(5, list.size());
        assertEquals(13, list.get(2));
    }

    @Test
    public void testAddOnEndIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);

        list.add(list.size()-1, 25);
        assertEquals(4, list.size());
        assertEquals(3, list.get(list.size()-1));
        assertEquals(25, list.get(list.size()-2));

        list.add(list.size(), 14);
        assertEquals(5, list.size());
        assertEquals(14, list.get(list.size()-1));
    }

    @Test
    public void testRemove() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3,4,5);

        list.remove((Integer)2);
        list.remove((Integer)4);

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    public void testLinearRemoveAtIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3,4,5);

        list.remove(0);
        list.remove(0);

        assertEquals(3, list.get(0));
        assertEquals(4, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    public void testNotLinearRemoveAtIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3,4,5);

        list.remove(1);
        list.remove(2);

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    public void testContains() {
        MyList<Integer> list = new MyArrayList<Integer>();
        list.add(13);
        list.add(29);
        assertTrue(list.contains(13));
        assertTrue(list.contains(29));
        assertFalse(list.contains(60));
    }

    @Test
    public void testIndexOf() {
        MyList<Integer> list = new MyArrayList<Integer>();
        list.add(13);
        list.add(29);
        list.add(13);
        list.add(12);
        assertEquals(0, list.indexOf(13));
        assertEquals(1, list.indexOf(29));
        assertEquals(-1, list.indexOf(60));
    }

    @Test
    public void testLastIndexOf() {
        MyList<Integer> list = new MyArrayList<Integer>();
        list.add(13);
        list.add(29);
        list.add(13);
        list.add(29);
        assertEquals(2, list.lastIndexOf(13));
        assertEquals(list.size()-1, list.lastIndexOf(29));
        assertEquals(-1, list.lastIndexOf(60));
    }

    @Test
    public void testContainsAll() {
        MyList<String> list = new MyArrayList<String>();
        list.add("My");
        list.add("Array");
        list.add("List");
        list.add("<String>");

        assertTrue(list.containsAll(List.of("List", "Array")));
        assertFalse(list.containsAll(List.of("List", "<Integer>")));
    }

    @Test
    public void testAddAll() {
        MyList<String> list = new MyArrayList<String>();
        list.add("My");
        list.addAll(List.of("Array", "List", "<String>"));

        assertEquals("My", list.get(0));
        assertEquals("Array", list.get(1));
        assertEquals("List", list.get(2));
        assertEquals("<String>", list.get(3));
    }

    @Test
    public void testAddAllAtOtherIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);

        list.addAll(1, List.of(4,8));

        assertEquals(1, list.get(0));
        assertEquals(4, list.get(1));
        assertEquals(8, list.get(2));
        assertEquals(2, list.get(3));
        assertEquals(3, list.get(4));
    }
    @Test
    public void testAddAllAtZeroIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2);

        list.addAll(0, List.of(4,8));

        assertEquals(4, list.get(0));
        assertEquals(8, list.get(1));
        assertEquals(1, list.get(2));
        assertEquals(2, list.get(3));
    }
    @Test
    public void testAddAllAtEndIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2);

        list.addAll(list.size(), List.of(4,8));

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(8, list.get(3));
    }

    @Test
    public void testRemoveAllElementsSome() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3,4,5);

        list.removeAll(List.of(2,4));

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
    }
    @Test
    public void testRemoveAllElementsAll() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3,4,5);

        list.removeAll(List.of(1,2,3,4,5));

        assertTrue(list.isEmpty());
    }

    @Test
    public void testClear() {
        MyList<String> list = new MyArrayList<String>(List.of("My", "Array", "List", "<String>"));
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToArray() {
        String[] testArray = {"Str1", "Str2", "Str3"};
        MyArrayList<String> list = new MyArrayList<String>(testArray);
        Object[] listArray = list.toArray();

        assertArrayEquals(testArray, listArray);
    }

    @Test
    public void testToArrayWithType() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3,4,5);

        Object[] thisArray = list.toArray();
        Integer[] thisArrayInteger = list.toArray(Integer[]::new);

        assertArrayEquals(thisArray, thisArrayInteger);
    }

    @Test
    public void testRetainAllEmptyListWithEmptyList() {
        MyList<Integer> list1 = new MyArrayList<Integer>();
        MyList<Integer> list2 = new MyArrayList<Integer>();

        boolean modified = list1.retainAll(list2);

        assertTrue(list1.isEmpty());
        assertFalse(modified);
    }

    @Test
    public void testRetainAllEmptyListWithNotEmptyList() {
        MyList<Integer> list1 = new MyArrayList<Integer>();
        MyList<Integer> list2 = new MyArrayList<Integer>(1,2,3,4,5);

        boolean modified = list1.retainAll(list2);

        assertTrue(list1.isEmpty());
        assertFalse(modified);
    }

    @Test
    public void testRetainAllNotEmptyListWithEmptyList() {
        MyList<Integer> list1 = new MyArrayList<Integer>(1,2,3,4,5);
        MyList<Integer> list2 = new MyArrayList<Integer>();

        boolean modified = list1.retainAll(list2);

        assertTrue(list1.isEmpty());
    }

    @Test
    public void testRetainAllNotEmptyListWithNotEmptyList() {
        MyList<Integer> list1 = new MyArrayList<Integer>(1,2,3,4,5);
        MyList<Integer> list2 = new MyArrayList<Integer>(1,3,5);

        boolean modified = list1.retainAll(list2);

        assertEquals(3, list1.size());
        assertEquals(1, list1.get(0));
        assertEquals(3, list1.get(1));
        assertEquals(5, list1.get(2));
        assertTrue(modified);
    }

    @Test
    public void testEnsureCapacity() {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        list.ensureCapacity(20);
        assertEquals(0, list.size());
    }

    @Test
    public void testIterator() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        Iterator<Integer> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testListIteratorToNext() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator();

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testListIteratorToPrevious() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator();

        iterator.next();
        iterator.next();
        iterator.next();

        assertTrue(iterator.hasPrevious());
        assertEquals(3, iterator.previous());

        assertTrue(iterator.hasPrevious());
        assertEquals(2, iterator.previous());

        assertTrue(iterator.hasPrevious());
        assertEquals(1, iterator.previous());

        assertFalse(iterator.hasPrevious());
    }
    @Test
    public void testListIteratorAtZeroIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator(0);

        assertFalse(iterator.hasPrevious());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
    }
    @Test
    public void testListIteratorAtOtherIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator(1);

        assertTrue(iterator.hasPrevious());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
    }
    @Test
    public void testListIteratorAtEndIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator(list.size()-1);

        assertTrue(iterator.hasPrevious());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());

        assertFalse(iterator.hasNext());
        assertTrue(iterator.hasPrevious());
    }
    @Test
    public void testListIteratorAddElementAtZeroIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator();

        iterator.add(27);

        assertEquals(4, list.size());
        assertEquals(27, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));
    }
    @Test
    public void testListIteratorAddElementAtOtherIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator(1);

        iterator.add(27);

        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(27, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));
    }
    @Test
    public void testListIteratorAddElementAtEndIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator(list.size()-1);

        iterator.next();
        iterator.add(38);

        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(38, list.get(3));
    }

    @Test
    public void testListIteratorRemoveElementAtZeroIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator();

        iterator.next();
        iterator.remove();

        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void testListIteratorRemoveElementAtOtherIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator(1);

        iterator.next();
        iterator.remove();

        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void testListIteratorRemoveElementAtEndIndex() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator(list.size()-1);

        iterator.next();
        iterator.remove();

        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testListIteratorSetValue() {
        MyList<Integer> list = new MyArrayList<Integer>(1,2,3);
        ListIterator<Integer> iterator = list.listIterator();

        iterator.set(25);
        assertEquals(25, iterator.next());
        assertEquals(25, list.get(0));
    }

    @Test
    public void testSubListReturnsEmptyList() {
        MyList<String> list = new MyArrayList<String>();
        List<String> subList = list.subList(0,0);
        assertTrue(subList.isEmpty());
    }
    @Test
    public void testSubListReturnsNotEmptyList() {
        MyList<String> list = new MyArrayList<String>("My", "Array", "List", "<String>");

        List<String> subList = list.subList(0, 2);
        assertFalse(subList.isEmpty());
        for(int i = 0; i < 2; i++) {
            assertEquals(list.get(i), subList.get(i));
        }
    }

    @Test
    public void testEqualsListWithEmptyList() {
        MyList<Integer> list1 = new MyArrayList<Integer>(1,2,3,4,5);
        MyList<Integer> list2 = new MyArrayList<Integer>();
        MyList<Integer> list3 = new MyArrayList<Integer>();

        assertFalse(list1.equals(list2));
        assertTrue(list2.equals(list3));
    }
    @Test
    public void testEqualsListWithNotEmptyList() {
        MyList<Integer> list1 = new MyArrayList<Integer>(1,2,3,4,5);
        MyList<Integer> list2 = new MyArrayList<Integer>(1,2,3,4,5);
        MyList<Integer> list3 = new MyArrayList<Integer>(1,2,3);

        assertTrue(list1.equals(list2));
        assertFalse(list2.equals(list3));
    }
}