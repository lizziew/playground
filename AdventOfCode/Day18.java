import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 

public class Day18 {
	static int n = 100; 
	static boolean[][] lights = new boolean[n][n]; 
	static boolean[][] orig = new boolean[n][n]; 

	public static boolean inBounds(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < n; 
	}

	public static int neighbors(int i, int j) {
		int ans = 0; 
		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1,  0,  1, 1,-1,-1, 0, 1};

		for(int t = 0; t < 8; t++) {
			int ni = i + dx[t];
			int nj = j + dy[t]; 

			if(inBounds(ni, nj) && orig[ni][nj]) ans++;  
		}

		return ans; 
	}

	public static void toggle() {
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				orig[i][j] = lights[i][j]; 

		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) {
				int n = neighbors(i, j);
				if(orig[i][j]) {
					if(n != 2 && n != 3) lights[i][j] = false;
				}
				else {
					if(n == 3) lights[i][j] = true; 
				}
			}

		lights[0][0] = true;
		lights[0][n-1] = true;
		lights[n-1][0] = true;
		lights[n-1][n-1] = true; 
	}

	public static int countLights() {
		int ans = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				if(lights[i][j]) ans++;
		return ans; 
	}

	public static void main(String[] args) {
		File file = new File("input.txt");

		try {
			Scanner s = new Scanner(file);  

			int i = 0; 
			while(s.hasNextLine()) {
				String input = s.nextLine(); 
				for(int j = 0; j < n; j++) 
					if(input.charAt(j) == '#') 
						lights[i][j] = true; 
				i++; 
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		for(int i = 0; i < 100; i++) 
   			toggle();
   		
   		System.out.println(countLights());
	}
}