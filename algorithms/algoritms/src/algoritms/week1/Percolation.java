package algoritms.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private static final int VIRTUAL_TOP = 0, VIRTUAL_BOTTOM = 1;
	private final int n;

	private int openSitesCount;
	private boolean[][] grid;
	private final WeightedQuickUnionUF unionFind;
	private final IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
			"Row/Column index should be between 1 to N.");

	public Percolation(int n) {
		if (n <= 0)
			throw illegalArgumentException;
		this.n = n + 1;
		unionFind = new WeightedQuickUnionUF(this.n * this.n);
		grid = new boolean[this.n][this.n];
		openSitesCount = 0;
	}

	private void validateArguments(int row, int col) {
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
		if (isValidOpen(row1, col1)) {
			int q = ufNodeId(row1, col1);
			unionFind.union(ufNodeId(row, col), q);
			if (row1 == 1) {
				unionFind.union(q, VIRTUAL_TOP);
			}
			if (row1 == n - 1) {
				unionFind.union(q, VIRTUAL_BOTTOM);
			}
		}
	}

	private int ufNodeId(int row, int col) {
		return row * (this.n) + col;
	}

	public void open(int row, int col) {
		validateArguments(row, col);
		if (grid[row][col]) {
			return;
		}
		grid[row][col] = true;
		openSitesCount += 1;
		if (row == 1) {
			unionFind.union(ufNodeId(row, col), VIRTUAL_TOP);
		}
		if (row == n - 1) {
			unionFind.union(ufNodeId(row, col), VIRTUAL_BOTTOM);
		}
		union(row, col, row, col - 1);
		union(row, col, row, col + 1);
		union(row, col, row + 1, col);
		union(row, col, row - 1, col);
	}

	public boolean isOpen(int row, int col) {
		validateArguments(row, col);
		return grid[row][col];
	}

	public boolean isFull(int row, int col) {
		if (isOpen(row, col)) {
			int p = unionFind.find(ufNodeId(row, col));
			if (row == n - 1) {
				for (int i = 1; i < n; i++) {
					if (grid[1][i] && p == unionFind.find(ufNodeId(1, i)))
						return true;
				}
			} else {
				return p == unionFind.find(VIRTUAL_TOP);
			}
		}
		return false;
	}

	public int numberOfOpenSites() {
		return openSitesCount;
	}

	public boolean percolates() {
		return unionFind.find(VIRTUAL_TOP) == unionFind.find(VIRTUAL_BOTTOM);
	}

}
