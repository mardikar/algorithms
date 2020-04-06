package algoritms.week1;

public class QuickFind implements UnionFindInterface {

	public int N;
	public int[] ids;

	public QuickFind(int N) {
		this.N = N + 1;
		this.ids = new int[this.N];
		for (int i = 0; i < this.N; i++) {
			this.ids[i] = i;
		}
	}

	public boolean isConnected(int p, int q) {
		return ids[p] == ids[q];
	}

	public void union(int p, int q) {
		int pid = ids[p];
		int qid = ids[q];

		for (int i = 0; i < N; i++) {
			if (ids[i] == pid)
				ids[i] = qid;
		}
	}

}
