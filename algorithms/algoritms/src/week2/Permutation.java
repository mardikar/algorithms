package week2;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		boolean flag = true;
		while (flag) {
			try {
				queue.enqueue(StdIn.readString());
			} catch (NoSuchElementException e) {
				flag = false;
			}
		}
		for (int i = 0; i < k; i++) {
			System.out.println(queue.sample());
		}
	}

}
