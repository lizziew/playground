import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;

public class Day5 {
	public static boolean isNice(String input) {
		/*int numVowels = 0; 
		String vowels = "aeoiu"; 
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(vowels.indexOf(c) != -1) numVowels++;
		}

		int numTwice = 0; 
		for(int i = 0; i < input.length()-1; i++) {
			char curr = input.charAt(i);
			char next = input.charAt(i+1);

			if(curr == next) numTwice++;

			if(curr == 'a' && next == 'b') return false; 
			if(curr == 'c' && next == 'd') return false;
			if(curr == 'p' && next == 'q') return false;
			if(curr == 'x' && next == 'y') return false; 
		}

		return numVowels >= 3 && numTwice >= 1; */ 

		boolean twoPairs = false; 
		for(int i = 0; i < input.length() - 1; i++) {
			String pair = input.substring(i, i+2); 
			if(input.indexOf(pair, i+2) != -1) {
				twoPairs = true;
				break; 
			}
		}

		if(!twoPairs) return false;

		for(int i = 0; i < input.length() - 2; i++) 
			if(input.charAt(i) == input.charAt(i+2)) return true;
		
		return false; 
	}

	public static void main(String[] args) {
		File file = new File("input.txt");

		int total = 0; 

		try {
			Scanner s = new Scanner(file);  

			while(s.hasNextLine()) {
				String input = s.nextLine(); 

				if(isNice(input)) total++; 
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		System.out.println(total);
	}
}