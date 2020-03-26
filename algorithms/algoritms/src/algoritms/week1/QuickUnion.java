package algoritms.week1;

public class QuickUnion implements UnionFindInterface {
	
	private int N;
	private int[] ids;
	
	public QuickUnion(int n) {
		N = n;
		ids = new int[N+1];
		for(int i=1;i <= N; i++) {
			ids[i] = i;
		}
	}
	
	private int root(int p) {
		while(ids[p] != p) {
			p = ids[p];
		}
		return ids[p];
	}

	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		ids[q] = p;
	}

}
