package algoritms.week1;

public class Tester {

	public static void main(String[] args) {
		
		int n = 10;
		
//		QuickUnion test = new QuickUnion(n);
		QuickFind test = new QuickFind(n);
		
		test.union(1, 2);
		test.union(1, 2);
		System.out.println(test.isConnected(1,2) == true);
		test.union(2, 3);
		System.out.println(test.isConnected(1,2) == true);
		System.out.println(test.isConnected(3,2) == true);
		System.out.println(test.isConnected(1,4) == false);
		System.out.println(test.isConnected(1,3) == true);
		test.union(1, 4);
		System.out.println(test.isConnected(1,4) == true);
		System.out.println(test.isConnected(1,3) == true);
	}

}
