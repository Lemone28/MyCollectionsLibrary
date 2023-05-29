package com.myUtil;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.*;

public class MyLinkedList<T> implements MyList<T>, Deque<T> {
    private static class Node<T> {
        T value;
        Node<T> previous;
        Node<T> next;

        Node(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        static <T> void swap(Node<T> left, Node<T> right) {
            Node<T> t = left.next;
            left.next = right.next;
            right.next = t;

            t = left.previous;
            left.previous = right.previous;
            right.previous = t;
        }
        static <T> void bind(Node<T> left, Node<T> right) {
            if (left == null && right != null) {
                right.previous = null;
            } else if (left != null && right == null) {
                left.next = null;
            } else if (left != null && right != null) {
                left.next = right;
                right.previous = left;
            }
        }
        static <T> Node<T> unlink(Node<T> node) {
            Node.bind(node.previous, node.next);
            node.next = null;
            node.previous = null;
            return node;
        }
    }
    private static class MyIterator<T> implements Iterator<T> {
        protected MyLinkedList<T> list;
        protected Node<T> currentNode;
        protected boolean isStepNext;

        MyIterator(MyLinkedList<T> list, Node<T> node) {
            this.list = Objects.requireNonNull(list);
            this.currentNode = node;
            this.isStepNext = false;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            isStepNext = true;

            Node<T> node = currentNode;
            currentNode = currentNode.next;
            return node.value;
        }
    }
    private static class MyListIterator<T> extends MyIterator<T>
            implements ListIterator<T> {
        private int currentIndex;

        MyListIterator(MyLinkedList<T> list, Node<T> node, int index) {
            super(list, node);
            this.currentIndex = MyLinkedList.checkIndex(index, list.size(), false);
            toIndex(index);
        }
        MyListIterator(MyLinkedList<T> list, Node<T> node) {
            this(list, node, 0);
        }

        private void toIndex(int index) {
            int i = index;
            while(hasNext() && i > 0) {
                this.next();
                i--;
            }
        }

        @Override
        public boolean hasPrevious() {
            return currentNode != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            Node<T> node = currentNode;
            currentNode = currentNode.previous;
            return node.value;
        }

        @Override
        public int nextIndex() {
            return (hasNext()) ? currentIndex+1: list.size();
        }

        @Override
        public int previousIndex() {
            return (hasPrevious()) ? currentIndex-1: -1;
        }

        @Override
        public void remove() {
            if (!isStepNext) {
                throw new IllegalStateException("No element to remove or next() has not been called");
            }
            list.remove(currentIndex-1);
        }

        @Override
        public void set(T t) {
            currentNode.value = t;
        }

        @Override
        public void add(T t) {
            list.add(currentIndex, t);
        }
    }
    private static class DescendingIterator<T> extends MyIterator<T> {
        public DescendingIterator(MyLinkedList<T> list, Node<T> node) {
            super(list, node);
        }
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = currentNode.value;
            currentNode = currentNode.previous;
            return data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
    }
    public MyLinkedList(Collection<? extends T> coll) {
        this();
        addAll(size, coll);
    }

    public T getNodeFirst(T element) {
        Node<T> node = nodeFromValueFirst(head, element);
        return (node == null) ? null:node.value;
    }
    public T getNodeLast(T element) {
        Node<T> node = nodeFromValueLast(tail, element);
        return (node == null) ? null:node.value;
    }

    static int checkIndex(int i, int size, boolean isIgnoreIndexEqualsSize) {
        if (i < 0 || i > size || (i == size && !isIgnoreIndexEqualsSize)) {
            throw new IllegalArgumentException("Illegal index: " + i);
        }
        return i;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return nodeSearchFromFirst(head, 0, (T)o) != -1;
    }

    @Override
    public int indexOf(Object o) {
        return nodeSearchFromFirst(head, 0, (T)o);
    }
    @Override
    public int lastIndexOf(Object o) {
        return nodeSearchFromLast(tail, size-1, (T)o);
    }

    private int nodeSearchFromFirst(Node<T> node, int index, T element) {
        if (node == null)
            return -1;
        else if (Objects.equals(node.value, element))
            return index;
        return nodeSearchFromFirst(node.next, index+1, element);
    }
    private int nodeSearchFromLast(Node<T> node, int index, T element) {
        if (node == null)
            return -1;
        else if (Objects.equals(node.value, element))
            return index;
        return nodeSearchFromLast(node.previous, index-1, element);
    }

    @Override
    public Object[] toArray() {
        return values();
    }
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[]) Array.newInstance(a.getClass().componentType(), size);
        }
        Object[] elements = values();
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
    }
    private Object[] values() {
        Object[] _values = new Object[size];
        Node<T> n = head;
        for(int i = 0; n != null;) {
            _values[i++] = n.value;
            n = n.next;
        }
        return _values;
    }

    @Override
    public T get(int index) {
        checkIndex(index, size, false);
        Node<T> node = nodeAt(head, index);
        return node.value;
    }
    @Override
    public T set(int index, T element) {
        checkIndex(index, size, false);
        Node<T> node = nodeAt(head, index);
        T oldElement = node.value;
        node.value = element;
        return oldElement;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size, false);
        Node<T> node = null;
        if (index == 0) {
            node = unlinkFirst(false, true);
        } else if (index == size - 1) {
            node = unlinkLast(false, true);
        } else {
            node = Node.unlink(nodeAt(head, index));
            size--;
        }
        return node.value;
    }

    private Node<T> nodeAt(Node<T> node, int index) {
        if (index == 0) return node;
        return nodeAt(node.next, index-1);
    }

    @Override
    public boolean add(T t) {
        linkLast(t);
        return true;
    }
    @Override
    public void add(int index, T element) {
        checkIndex(index, size, true);
        nodeTo(index, element);
        size++;
    }

    private void nodeTo(int index, T element) {
        Node<T> h = head;
        for(int i = 0; h != null && i < index; i++) {
            h = h.next;
        }

        Node<T> newNode = new Node<T>(element, null, null);
        if (h == null) {
            if (head == null) {
                head = newNode;
                tail = newNode;
            }
            else {
                Node.bind(tail, newNode);
                tail = newNode;
            }
        } else if (h.previous != null) {
            Node.bind(h.previous, newNode);
            Node.bind(newNode, h);
        }
    }

    @Override
    public void addFirst(T e) {
        linkFirst(e);
    }

    @Override
    public void addLast(T t) {
        linkLast(t);
    }

    private void linkFirst(T e) {
        Node<T> h = head;
        Node<T> nn = new Node<T>(e, null, h);
        head = nn;

        if (h == null) {
            tail = nn;
        } else {
            h.previous = nn;
        }
        size++;
    }

    private void linkLast(T e) {
        Node<T> t = tail;
        Node<T> nn = new Node<T>(e, t, null);
        tail = nn;

        if (t == null) {
            head = nn;
        } else {
            t.next = nn;
        }
        size++;
    }

    @Override
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }
    @Override
    public boolean offerLast(T t) {
        addLast(t);
        return true;
    }
    @Override
    public boolean offer(T t) {
        addFirst(t);
        return true;
    }
    @Override
    public T removeFirst() {
        final Node<T> h = unlinkFirst(true, true);
        return h.value;
    }
    @Override
    public T removeLast() {
        final Node<T> t = unlinkLast(true, true);
        return t.value;
    }

    @Override
    public T remove() {
        final Node<T> h = unlinkFirst(true, true);
        return h.value;
    }
    @Override
    public boolean remove(Object o) {
        Node<T> node = nodeFromValueFirst(head, (T)o);
        if (node == null) return false;

        if (node == head) {
            unlinkFirst(false, true);
        } else if (node == tail) {
            unlinkLast(false, true);
        } else {
            Node.unlink(node);
            size--;
        }
        return true;
    }
    private Node<T> nodeFromValueFirst(Node<T> node, T element) {
        if (node == null)
            return null;
        else if (node.value.equals(element))
            return node;
        return nodeFromValueFirst(node.next, element);
    }

    @Override
    public T poll() {
        return pollFirst();
    }
    @Override
    public T pollFirst() {
        final Node<T> h = unlinkFirst(false, true);
        return (h != null) ? h.value : null;
    }
    @Override
    public T pollLast() {
        final Node<T> t = unlinkLast(false, true);
        return (t != null) ? t.value : null;
    }

    @Override
    public T getFirst() {
        final Node<T> h = unlinkFirst(true, false);
        return h.value;
    }

    @Override
    public T getLast() {
        final Node<T> t = unlinkLast(true, false);
        return t.value;
    }

    @Override
    public T peekFirst() {
        final Node<T> h = unlinkFirst(false, false);
        return (h != null) ? h.value : null;
    }
    @Override
    public T peekLast() {
        final Node<T> t = unlinkLast(false, false);
        return (t != null) ? t.value : null;
    }

    private Node<T> unlinkFirst(boolean throwIfNull, boolean isRemove) {
        if (head == null){
            if (throwIfNull)
                throw new NoSuchElementException();
            else
                return null;
        }
        else if (!isRemove) return head;

        final Node<T> h = head;
        if (head.next == null) {
            head = null;
        } else {
            Node<T> hn = head.next;
            hn.previous = null;
            head = hn;
        }
        size--;
        return h;
    }

    private Node<T> unlinkLast(boolean throwIfNull, boolean isRemove) {
        if (tail == null){
            if (throwIfNull)
                throw new NoSuchElementException();
            else
                return null;
        }
        else if (!isRemove)
            return tail;

        final Node<T> t = tail;
        if (tail.previous == null) {
            tail = null;
        } else {
            Node<T> tp = tail.previous;
            tp.next = null;
            tail = tp;
        }
        size--;
        return t;
    }
    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        Node<T> node = nodeFromValueLast(tail, (T)o);
        if (node == null) return false;

        if (node == tail) {
            unlinkLast(false, true);
        } else if (node == head) {
            unlinkFirst(false, true);
        } else {
            Node.unlink(node);
            size--;
        }
        return true;
    }

    private Node<T> nodeFromValueLast(Node<T> node, T element) {
        if (node == null)
            return null;
        else if (Objects.equals(node.value, element))
            return node;
        return nodeFromValueLast(node.previous, element);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        Iterator<?> iterator = coll.iterator();
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
        checkIndex(index, size, true);

        if (coll.isEmpty()) {
            return false;
        }

        Node<T> newNode = new Node<T>(null, null, null);
        Node<T> pred;
        Node<T> succ;

        if (index == size) {
            pred = tail;
            succ = null;
        } else {
            succ = nodeAt(head, index);
            pred = succ.previous;
        }

        for (T element : coll) {
            newNode.value = element;
            newNode.next = succ;
            newNode.previous = pred;

            if (pred == null) {
                head = newNode;
            } else {
                pred.next = newNode;
            }

            if (succ == null) {
                tail = newNode;
            } else {
                succ.previous = newNode;
            }

            pred = newNode;
            newNode = new Node<T>(null, null, null);
            size++;
        }

        return true;
    }

    @Override
    public T element() {
        final Node<T> h = unlinkFirst(true, false);
        return h.value;
    }
    @Override
    public void push(T t) {
        linkFirst(t);
    }

    @Override
    public T peek() {
        final Node<T> h = unlinkFirst(false, false);
        return (h != null) ? h.value : null;
    }
    @Override
    public T pop() {
        final Node<T> h = unlinkFirst(true, true);
        return h.value;
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        if (coll.isEmpty()) return false;

        boolean modified = false;
        Iterator<?> iterator = iterator();
        while(iterator.hasNext()) {
            Object element = iterator.next();
            if (coll.contains(element)) {
                modified = remove(element);
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        if (coll.isEmpty()) {
            clear();
            return true;
        }

        boolean modified = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (!coll.contains(element)) {
                modified = remove(element);
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        if (head == null) return;
        Node<T> node = head;
        while(node != null) {
            Node<T> nn = node.next;
            Node.unlink(node);
            node = nn;
        }
        head = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>(this, head);
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new DescendingIterator<T>(this, tail);
    }
    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator<T>(this, head);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new MyListIterator<T>(this, head, index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (isEmpty() || fromIndex == toIndex) {
            return new MyArrayList<T>();
        } else if (checkIndex(fromIndex, size, false) >
                checkIndex(toIndex, size, false)) {
            throw new IllegalArgumentException("The start index is greater than the end index");
        }
        MyLinkedList<T> subList = new MyLinkedList<T>();
        ListIterator<T> iterator = listIterator(fromIndex);
        for(int i = fromIndex; i <= toIndex; i++) {
            subList.add(iterator.next());
        }
        return subList;
    }
//    public List<T> subList(int fromIndex, int toIndex) {
//        checkIndex(fromIndex, size, false);
//        checkIndex(toIndex, size, false);
//
//        MyLinkedList<T> sublist = new MyLinkedList<T>();
//
//        if (fromIndex >= toIndex) {
//            return sublist;
//        }
//
//        ListIterator<T> iterator = listIterator(fromIndex);
//
//        for(int i = fromIndex; i < toIndex; i++) {
//            T element = iterator.next();
//            sublist.add(element);
//        }
//        return sublist;
//    }
}
