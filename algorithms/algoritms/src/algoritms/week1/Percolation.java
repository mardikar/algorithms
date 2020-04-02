package algoritms.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private static final int VIRTUAL_TOP = 0, VIRTUAL_BOTTOM = 1;
	private final int n;

	private int openSitesCount;
	private boolean[][] grid;
	private final WeightedQuickUnionUF unionFind;

	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("N must be a non-zero positive integer.");
		}
		this.n = n + 1;
		unionFind = new WeightedQuickUnionUF(this.n * this.n);
		grid = new boolean[this.n][this.n];
		openSitesCount = 0;
		for(int j=1;j<n;j++) {
			unionFind.union(ufNodeId(1, j), VIRTUAL_TOP);
			unionFind.union(ufNodeId(n-1, j), VIRTUAL_BOTTOM);
		}
	}

	private void validateArguments(int row, int col) {
		IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
				"Row/Column index should be between 1 to N.");
		if (!isValid(row, col))
			throw illegalArgumentException;
	}

	private boolean isValid(int row, int col) {
		return row > 0 && row < n && col > 0 && col < n;
	}

	private boolean isValidOpen(int row, int col) {
		return isValid(row, col) && grid[row][col];
	}

	private void union(int row, int col, int row1, int col1) {
		int p = ufNodeId(row, col);
		int q = ufNodeId(row1, col1);
		unionFind.union(p, q);
	}

	private int ufNodeId(int row, int col) {
		return row * (this.n) + col;
	}

	public void open(int row, int col) {
		validateArguments(row, col);
		if(grid[row][col]) {
			return;
		}
		grid[row][col] = true;
		openSitesCount += 1;
		if (isValidOpen(row, col - 1)) {
			union(row, col, row, col - 1);
		}
		if (isValidOpen(row, col + 1)) {
			union(row, col, row, col + 1);
		}
		if (isValidOpen(row + 1, col)) {
			union(row, col, row + 1, col);
		}
		if (isValidOpen(row - 1, col)) {
			union(row, col, row - 1, col);
		}
	}

	public boolean isOpen(int row, int col) {
		validateArguments(row, col);
		return grid[row][col];
	}

	public boolean isFull(int row, int col) {
		return isOpen(row, col) && unionFind.find(ufNodeId(row, col)) == unionFind.find(VIRTUAL_TOP);
	}

	public int numberOfOpenSites() {
		return openSitesCount;
	}

	public boolean percolates() {
		return unionFind.find(VIRTUAL_TOP) == unionFind.find(VIRTUAL_BOTTOM);
	}

}
