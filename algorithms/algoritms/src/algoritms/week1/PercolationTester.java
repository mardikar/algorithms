package algoritms.week1;

import java.io.File;
import edu.princeton.cs.algs4.In;

public class PercolationTester {

	public static void main(String[] args) {
		long start = System.nanoTime();
		String[] fileNames = new String[] { "greeting57.txt", "heart25.txt", "input10-no.txt", "input10.txt",
				"input1-no.txt", "input1.txt", "input20.txt", "input2-no.txt", "input2.txt", "input3.txt", "input4.txt",
				"input50.txt", "input5.txt", "input6.txt", "input7.txt", "input8-dups.txt", "input8-no.txt",
				"input8.txt", "java60.txt", "jerry47.txt", "sedgewick60.txt", "snake101.txt", "snake13.txt",
				"wayne98.txt" };
		int files = fileNames.length;
		for (int k = 0; k < files; k++) {
			In in = new In(new File("../algoritms/src/algoritms/week1/" + fileNames[k])); // input file
			int n = in.readInt(); // n-by-n percolation system

			Percolation perc = new Percolation(n);
			while (!in.isEmpty()) {
				int i = in.readInt();
				int j = in.readInt();
				perc.open(i, j);
				perc.isOpen(i, j);
				perc.percolates();
				perc.numberOfOpenSites();
				perc.isFull(i, j);
			}
			System.out.println(perc.percolates());
		}
		System.out.println((System.nanoTime() - start) / 1000000000.0);
	}

}
