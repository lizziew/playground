import java.lang.*; 
import java.util.*; 

public class StringProblems {
	static int[][] tab; 

	public static int minEditDistance(String a, String b) {
		for(int i = 0; i <= a.length(); i++) {
			for(int j = 0; j <= b.length(); j++) {
				if(i == 0) tab[i][j] = j; //delete all j characters of b
				else if(j == 0) tab[i][j] = i; //delete all i characters of a
				else if(a.charAt(i-1) == b.charAt(j-1)) tab[i][j] = tab[i-1][j-1];
				else tab[i][j] = 1 + Math.min(Math.min(tab[i-1][j], tab[i][j-1]), tab[i-1][j-1]); 
			}
		}
		return tab[a.length()][b.length()]; 
	}

	public static int lcs(String a, String b) {
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					tab[i][j] = 1 + tab[i-1][j-1];
				}
				else tab[i][j] = Math.max(tab[i][j-1], tab[i-1][j]);
			}
		}
		return tab[a.length()][b.length()]; 
	}

	public static void main(String[] args) {
		String a = "FLOCCINAUCINIHILIPILIFICATION";
		String b = "SUBDERMATOGLYPHIC"; 

		tab = new int[a.length()+1][b.length()+1];
		System.out.println(minEditDistance(a, b));

		tab = new int[a.length()+1][b.length()+1];
		System.out.println(lcs(a, b));
	}
}