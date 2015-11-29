public class Knapsack {
	static int[][] tab; 

	public static int knapsack(int[] values, int[] weights, int C) {
		for(int i = 0; i < tab.length; i++) {
			for(int j = 0; j < tab[0].length; j++) {
				if(i == 0) tab[i][j] = 0; 
				else if(j == 0) tab[i][j] = 0; 
				else if(weights[i-1] > j) tab[i][j] = tab[i-1][j]; 
				else tab[i][j] = Math.max(tab[i-1][j], values[i-1] + tab[i-1][j-weights[i-1]]); 
			}
		}
		return tab[values.length][C]; 
	}

	public static void main(String[] args) {
		int[] values = {60, 100, 120};
		int[] weights = {10, 20, 30}; 
		int C = 50; 

		tab = new int[values.length+1][C+1];

		System.out.println(knapsack(values, weights, C));
	}
}