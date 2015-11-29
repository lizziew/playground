import java.lang.*;
import java.util.*; 

public class LIS {
	static int[] tab; 

	public static int lis(int[] a) {
		tab[0] = 1; 

		for(int i = 1; i < a.length; i++) {
			int maxlen = Integer.MIN_VALUE; 
			for(int j = 0; j < i; j++)
				if(a[i] > a[j] && tab[j] > maxlen)
					maxlen = tab[j];
			tab[i] = 1 + maxlen; 
		}

		return tab[a.length-1]; 
	}

	public static void main(String[] args) {
		int[] a = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}; 
		tab = new int[a.length+1]; 
		System.out.println(lis(a));
	}
}