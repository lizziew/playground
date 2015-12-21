import java.lang.StringBuilder; 

public class Day10 {
	public static String nextTerm(String x) {
		StringBuilder res = new StringBuilder();  

		char prev = x.charAt(0);
		int n = 1; 
		for(int i = 1; i < x.length(); i++) {
			char curr = x.charAt(i);
			if(curr == prev) n++; 
			else {
				res.append(Integer.toString(n) + prev);
				prev = curr;
				n = 1; 
			}
		}
		res.append(Integer.toString(n) + prev); 

		return res.toString(); 
	}

	public static void main(String[] args) {
		String input = "1321131112";
		for(int i = 0; i < 50; i++)
			input = nextTerm(input); 
		System.out.println(input.length());
	}
}