import java.lang.*; 

public class Day21 {
	static int[][] weapons = {{8, 4, 0}, 
				   {10, 5, 0},
				   {25, 6, 0},
				   {40, 7, 0},
				   {74, 8, 0}};
	static int[][] armor = {{13, 0, 1}, 
				   {31, 0, 2},
				   {53, 0, 3},
				   {75, 0, 4},
				   {102, 0, 5}};
	static int[][] rings = {{25, 1, 0}, 
				   {50, 2, 0},
				   {100, 3, 0},
				   {20, 0, 1},
				   {40, 0, 2},
				   {80, 0, 3}};

	public static boolean win(int phits, int pdamage, int parmor, int bhits, int bdamage, int barmor) {
		int pmoves = (int) Math.ceil((double) phits / Math.max(bdamage - parmor, 1)); //num of moves to defeat player 
		int bmoves = (int) Math.ceil((double) bhits / Math.max(pdamage - barmor, 1)); //num of moves to defeat boss 
		return pmoves >= bmoves; 
	}

	public static void main(String[] args) { 
		int maxGold = Integer.MIN_VALUE; 

		//1 weapon 
		for(int i = 0; i < weapons.length; i++)
			if(!win(100, weapons[i][1], 0, 103, 9, 2)) {
				maxGold = Math.max(maxGold, weapons[i][0]);
			}

		//1 weapon + 1 armor
		for(int i = 0; i < weapons.length; i++)
			for(int j = 0; j < armor.length; j++)
				if(!win(100, weapons[i][1], armor[j][2], 103, 9, 2)) {
					maxGold = Math.max(maxGold, weapons[i][0] + armor[j][0]);
				}

		//1 weapon + 1 armor + 2 rings
		for(int i = 0; i < weapons.length; i++)
			for(int j = 0; j < armor.length; j++)
				for(int k = 0; k < rings.length-1; k++)
					for(int l = k+1; l < rings.length; l++)
						if(!win(100, weapons[i][1]+rings[k][1]+rings[l][1], armor[j][2]+rings[k][2]+rings[l][2], 103, 9, 2)) {
							maxGold = Math.max(maxGold, weapons[i][0] + armor[j][0] + rings[k][0] + rings[l][0]);
						}

		//1 weapon + 1 armor + 1 ring
		for(int i = 0; i < weapons.length; i++)
			for(int j = 0; j < armor.length; j++)
				for(int k = 0; k < rings.length; k++)
					if(!win(100, weapons[i][1]+rings[k][1], armor[j][2]+rings[k][2], 103, 9, 2)) {
						maxGold = Math.max(maxGold, weapons[i][0] + armor[j][0] + rings[k][0]);	
					}	

		//1 weapon + 1 ring
		for(int i = 0; i < weapons.length; i++)
			for(int j = 0; j < rings.length; j++) 
				if(!win(100, weapons[i][1] + rings[j][1], rings[j][2], 103, 9, 2)) {
					maxGold = Math.max(maxGold, weapons[i][0] + rings[j][0]);
				}

		//1 weapon + 2 rings 
		for(int i = 0; i < weapons.length; i++)
				for(int k = 0; k < rings.length-1; k++)
					for(int l = k+1; l < rings.length; l++)
						if(!win(100, weapons[i][1]+rings[k][1]+rings[l][1], rings[k][2]+rings[l][2], 103, 9, 2)) {
							maxGold = Math.max(maxGold, weapons[i][0] + rings[k][0] + rings[l][0]);
						}

		System.out.println(maxGold);
	}	
}