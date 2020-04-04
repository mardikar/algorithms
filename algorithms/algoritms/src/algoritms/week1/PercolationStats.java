package algoritms.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final int n;
	private final double meanValue, factor, standardDev;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		this.n = n;
		double[] thresholds = new double[trials];
		for (int i = 0; i < trials; i++) {
			thresholds[i] = runSimulation();
		}
		meanValue = StdStats.mean(thresholds);
		standardDev = StdStats.stddev(thresholds);
		factor = (1.96 * stddev()) / Math.sqrt(trials);
	}

	private double runSimulation() {
		Percolation percolation = new Percolation(n);
		int[] sites = new int[(this.n) * (this.n)];
		int count = 0;
		for (int i = 1; i <= this.n; i++) {
			for (int j = 1; j <= this.n; j++) {
				sites[count++] = i * (this.n + 1) + j;
			}
		}
		StdRandom.shuffle(sites);
		for (int i = 0; i < count; i++) {
			int row = sites[i] / (this.n + 1);
			int col = sites[i] % (this.n + 1);
			percolation.open(row, col);
			if (percolation.percolates()) {
				return (i * 1.0) / count;
			}
		}
		return 1;
	}

	public double mean() {
		return meanValue;
	}

	public double stddev() {
		return standardDev;
	}

	public double confidenceLo() {
		return mean() - factor;
	}

	public double confidenceHi() {
		return mean() + factor;
	}

	public static void main(String[] args) {
		args = new String[] { "200", "100" };
		int n = Integer.parseInt(args[0]), trials = Integer.parseInt(args[1]);
		PercolationStats percolationStats = new PercolationStats(n, trials);
		System.out.println("mean = " + percolationStats.mean());
		System.out.println("stddev = " + percolationStats.stddev());
		System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
				+ percolationStats.confidenceHi() + "]");
	}

}