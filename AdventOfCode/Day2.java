import java.lang.Math; 
import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;

public class Day2 {
	public static int getWrapAmount(int a, int b, int c) {
		int x = a*b, y = a*c, z = b*c; 
		int min = Math.min(x, Math.min(y, z));
		return 2*(x+y+z)+min; 
	}

	public static int getRibbonAmount(int a, int b, int c) {
		int max = Math.max(a, Math.max(b, c)); 
		return 2*(a+b+c) - 2 * max + a*b*c; 
	}

	public static void main(String[] args) {
		File file = new File("input.txt");
		int total = 0; 

		try {
			Scanner s = new Scanner(file);  

			while(s.hasNextLine()) {
				Scanner t = new Scanner(s.nextLine()).useDelimiter("x");
				total += getRibbonAmount(t.nextInt(), t.nextInt(), t.nextInt()); 
				t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		System.out.println(total);
	}
}