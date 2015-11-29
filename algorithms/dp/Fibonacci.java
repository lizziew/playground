public class Fibonacci{
	public static int method1(int n) {
		if(n <= 2) return 1; 
		else return method1(n-1) + method1(n-2); 
	}

	static int[] memo; 
	public static int method2(int n) {
		if(memo[n] != -1) return memo[n]; 
		else {
			if(n <= 2) return 1; 
			else {
				memo[n] = method2(n-1) + method2(n-2); 
				return memo[n]; 
			}
		}
	}

	static int[] tab; 
	public static int method3(int n) {
		for(int i = 1; i <= n; i++) {
			if(i <= 2) tab[i] = 1; 
			else tab[i] = tab[i-1] + tab[i-2]; 
		}
		return tab[n]; 
	}

	public static int better_method3(int n) {
		if(n <= 2) return 1; 

		int prev = 1, curr = 1, next; 
		for(int i = 3; i <= n; i++) {
			next = prev + curr;
			prev = curr;
			curr = next; 
		}

		return curr; 
	}

	public static void main(String[] args) {
		int n = 50; 
		long startTime, endTime; 

		System.out.println("time of each approach in nanoseconds..."); 

		startTime = System.nanoTime();
		method1(n);
		endTime = System.nanoTime();
		System.out.println(endTime - startTime);

		memo = new int[n+1];
		for(int i = 0; i < memo.length; i++) memo[i] = -1; 
		startTime = System.nanoTime();
		method2(n);
		endTime = System.nanoTime();
		System.out.println(endTime - startTime);

		tab = new int[n+1]; 
		startTime = System.nanoTime();
		method3(n); 
		endTime = System.nanoTime();
		System.out.println(endTime - startTime);

		startTime = System.nanoTime();
		better_method3(n);
		endTime = System.nanoTime();
		System.out.println(endTime - startTime);
	}
}