package algoritms.week1;

public class QuickFind implements UnionFindInterface {
	
	private int N;
	private int[] ids;

	public QuickFind(int N) {
		this.N = N;
		this.ids = new int[N+1];
		for(int i=1; i <= N; i++) {
			this.ids[i] = i;
		}
	}
	
	public boolean isConnected(int p, int q) {
		return ids[p] == ids[q];
	}
	
	public void union(int p, int q) {
		int pid = ids[p];
		int qid = ids[q];
		
		for(int i=1; i <= N; i++) {
			if(ids[i] == pid)
				ids[i] = qid;
		}
	}

}