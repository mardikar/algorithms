package algoritms.week1;

public class QuickUnion implements UnionFindInterface {
	
	protected int N;
	protected int[] ids;
	
	public QuickUnion(int n) {
		N = n+1;
		ids = new int[N];
		for(int i=0;i < N; i++) {
			ids[i] = i;
		}
	}
	
	protected int root(int p) {
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
