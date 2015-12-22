public class Day20 {
	public static int numGifts1(int n) {
		int ans = 0; 
		for(int i = 1; i*i <= n; i++) {
			if(n % i == 0) {
				ans += 11*i;
				if(i*i != n) ans += 11 * (n/i);
			}
		}
		return ans; 
	}

	public static void main(String[] args) {
		int i = 10;
		while(true) {
			if(numGifts1(i) >= 33100000) {
				System.out.println(i);
				return; 
			}
			i++; 
		}
	}
}