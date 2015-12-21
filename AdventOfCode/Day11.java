import java.lang.*; 

public class Day11 {
	public static char nextChar(char c) {
		if(c == 'z') return 'a';
		else return (char) (c+1); 
	}

	public static String increment(String s, int n) {
		if(s.charAt(n) == 'z') {
			String prev = increment(s, n-1); 
			return prev.substring(0, n) + "a" + prev.substring(n+1); 
		}
		else return s.substring(0, n) + nextChar(s.charAt(n)) + s.substring(n+1); 
	}

	public static boolean isValid(String s) {
		//can't include i, o, or l
		if(s.indexOf("i") != -1 || s.indexOf("o") != -1 || s.indexOf("l") != -1) return false; 

		//one increasing straight
		boolean includesStraight = false; 
		for(int i = 0; i < s.length() - 2; i++) 
			if(s.charAt(i)+1 == s.charAt(i+1) && s.charAt(i+1)+1 == s.charAt(i+2)) {
				includesStraight = true;
				break;
			}
		if(!includesStraight) return false; 
		
		//contains at least two different, non-overlapping pairs of letters
		boolean[] visited = new boolean[26]; 
		int numPairs = 0; 
		for(int i = 0; i < s.length()-1; i++) {
			if(s.charAt(i) == s.charAt(i+1)) {
				if(!visited[s.charAt(i)-'a']) {
					visited[s.charAt(i)-'a'] = true;
					numPairs++; 
					if(numPairs >= 2) return true; 
				}
			}
		}

		return false; 
	}

	public static void main(String[] args) {
		String input = "hxbxxzaa"; 

		while(true) {
			if(!isValid(input)) input = increment(input, input.length()-1);
			else break;
		}

		System.out.println(input); 
	}
}