import java.util.*; 

//at first my search space for day 24 was way too large so my program was taking too long to run
//I came across blecki's post in the subreddit and used their good insights to figure out what k must be beforehand 

public class Day24 { 
	static int[] weights = {113, 109, 107, 103, 101, 97, 89, 83, 79, 73, 71, 67, 61, 59, 53, 47, 43, 41, 31, 29, 23, 19, 17, 13, 11, 5, 3, 1};
	
	static int k = 5; 
	static int goal = 387;  

	public static boolean combok(int n) {
		int ans = 0; 
		for(int i = 0; i < 28; i++) {
			if((n & (1<<i)) == (1<<i))
				ans++;
		}
		return ans == k; 
	}

	public static int sum(int n) {
		int ans = 0; 
		for(int i = 0; i < 29; i++) {
			if((n & (1<<i)) == (1<<i)) {
				ans += weights[i]; 
			}
		}
		return ans; 
	}

	public static Long product(int n) {
		long ans = 1; 
		for(int i = 0; i < 29; i++) {
			if((n & (1<<i)) == (1<<i)) {
				ans *= weights[i]; 
			}
		}
		return ans; 
	}

	public static void print(int n) {
		for(int i = 0; i < 28; i++) {
			if((n & (1<<i)) == (1<<i))
				System.out.print(weights[i] + " ");
		}
		System.out.println(); 
	}

	public static void main(String[] args) {
		int total = 0; //total weight
		int n = weights.length; //num gifts 

   		Long minProduct = Long.MAX_VALUE; 
   		int minI = 0; 
      	for(int i = 1<<(n+1) - 1; i >= 1; i--) {
      		if(combok(i) && sum(i) == goal) {
      			Long p = product(i);
      			if(p < minProduct) {
      				minProduct = p;
      				minI = i; 
      			}
      		}
      	}

      	print(minI); 
      	System.out.println(minProduct);
	}
}