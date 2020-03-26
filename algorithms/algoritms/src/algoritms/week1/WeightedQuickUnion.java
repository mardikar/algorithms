package algoritms.week1;

public class WeightedQuickUnion extends QuickUnion{
	
	protected int[] sizes;

	public WeightedQuickUnion(int n) {
		super(n);
		sizes = new int[N];
		for(int i=0; i < N; i++) {
			sizes[i] = 1;
		}
	}
	
	public void union(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		
		if(sizes[rootP] >= sizes[rootQ]) {
			ids[rootQ] = rootP;
			sizes[rootP] += sizes[rootQ];
		}
		else {
			ids[rootP] = rootQ;
			sizes[rootQ] += sizes[rootP];
		}
	}

}
