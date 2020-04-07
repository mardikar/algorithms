package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int length = 0, capacity = 1;
	private Item[] queue;
	private final NoSuchElementException noSuchElementException = new NoSuchElementException();
	private final IllegalArgumentException illegalArgumentException = new IllegalArgumentException();

	public RandomizedQueue() {
		queue = (Item[]) new Object[capacity];
	}

	private void resize(int newCapacity) {
		capacity = newCapacity;
		Item[] newQueue = (Item[]) new Object[capacity];
		for (int i = 0; i < length; i++) {
			newQueue[i] = queue[i];
		}
		queue = newQueue;
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

	public void enqueue(Item item) {
		validateInput(item);
		if (capacity == length) {
			resize(2 * capacity);
		}
		queue[length++] = item;
	}

	private void validateRemove() {
		if (isEmpty()) {
			throw noSuchElementException;
		}
	}

	public Item dequeue() {
		validateRemove();
		if (length == capacity / 4) {
			resize(capacity / 2);
		}
		int index = StdRandom.uniform(0, length);
		Item item = queue[index];
		queue[index] = queue[--length];
		return item;
	}

	public Item sample() {
		return queue[StdRandom.uniform(0, length)];
	}

	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<Item> {

		private int current = 0;
		private final UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();

		public RandomQueueIterator() {
			StdRandom.shuffle(queue);
		}

		public boolean hasNext() {
			return current != length;
		}

		public Item next() {
			if (!hasNext()) {
				throw noSuchElementException;
			}
			return queue[current++];
		}

		public void remove() {
			throw unsupportedOperationException;
		}

	}

	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		queue.enqueue("Saurabh");
		System.out.println(queue.dequeue());
		queue.enqueue("Saurabh");
		queue.enqueue("Mardikar");
		System.out.println(queue.sample());
		System.out.println(queue.sample());
		System.out.println(queue.sample());
	}

}
