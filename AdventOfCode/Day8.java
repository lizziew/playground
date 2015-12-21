import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;

public class Day8 {
	public static int process(String input) {
		int res = 0; 
		int i = 1;
		while(i < input.length()-1) {
			if(i < input.length() - 2 && (input.charAt(i) == '\\' && input.charAt(i+1) == '\\') || (input.charAt(i) == '\\' && input.charAt(i+1) == '"')) {
				res++; 
				i += 2; 
			}
			else if(i < input.length() - 4 && input.charAt(i) == '\\' && input.charAt(i+1) == 'x') {
				res++;
				i += 4; 
			}
			else {
				res++;
				i++; 
			}
		}
		System.out.println(res);
		return res; 
	}

	public static int convert(String input) {
		int res = 0; 

		int i = 0; 
		while(i < input.length()) {
			if(input.charAt(i) == '"' || input.charAt(i) == '\\') {
				res+=2;
			}
			else res++; 

			i++; 
		}

		//System.out.println(res+2);
		return res+2; 
	}

	public static void main(String[] args) {
		File file = new File("input.txt");

		int totalcode = 0; 
		int totalmemory = 0; 
		int totalencoded = 0; 

		try {
			Scanner s = new Scanner(file);  

			while(s.hasNextLine()) {
				String line = s.nextLine();
				totalcode += line.length(); 
				totalencoded += convert(line); 
				//totalmemory += process(line); 
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		//System.out.println(totalcode-totalmemory);
   		System.out.println(totalencoded - totalcode);
	}
}