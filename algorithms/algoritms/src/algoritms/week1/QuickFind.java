package algoritms.week1;

public class QuickFind implements UnionFindInterface {
	
	protected int N;
	protected int[] ids;

	public QuickFind(int N) {
		this.N = N+1;
		this.ids = new int[N];
		for(int i=0; i < N; i++) {
			this.ids[i] = i;
		}
	}
	
	public boolean isConnected(int p, int q) {
		return ids[p] == ids[q];
	}
	
	public void union(int p, int q) {
		int pid = ids[p];
		int qid = ids[q];
		
		for(int i=0; i < N; i++) {
			if(ids[i] == pid)
				ids[i] = qid;
		}
	}

}
