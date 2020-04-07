package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        public Item item;
        public Node next;

        Node(Item item) {
            this.item = item;
            next = null;
        }

    }

    private int length = 0;
    private Node first = null, last = null;
    private final IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
    private final NoSuchElementException noSuchElementException = new NoSuchElementException();

    public Deque() {

    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    private void validateInput(Item item) {
        if (item == null) {
            throw illegalArgumentException;
        }
    }

    public void addFirst(Item item) {
        validateInput(item);
        if (isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node(item);
            first.next = oldFirst;
        }
        length++;
    }

    public void addLast(Item item) {
        validateInput(item);
        if (isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            Node oldLast = last;
            last = new Node(item);
            oldLast.next = last;
        }
        length++;
    }

    private void validateRemove() {
        if (isEmpty()) {
            throw noSuchElementException;
        }
    }

    public Item removeFirst() {
        validateRemove();
        Item item = first.item;
        first = first.next;
        length--;
        return item;
    }

    public Item removeLast() {
        validateRemove();
        Item item = last.item;
        Node pointer = first;
        while (pointer.next != null && pointer.next != last)
            pointer = pointer.next;
        last = pointer;
        last.next = null;
        length--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;
        private final UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw noSuchElementException;
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw unsupportedOperationException;
        }

    }

    private void printDeque() {
        for (Item s : this) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

        Deque<String> deque = new Deque<String>();
        deque.addFirst("Saurabh");
        deque.printDeque();
        deque.removeLast();
        deque.addFirst("Sukanya");
        deque.printDeque();
        deque.addLast("Mardikar");
        deque.printDeque();
        deque.removeFirst();
        deque.printDeque();
        System.out.println(deque.removeLast());
        deque.printDeque();

    }

}
