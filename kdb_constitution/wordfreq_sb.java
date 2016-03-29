import java.io.*;
import java.util.*; 

public class wordfreq_sb {
	static class word {
		String w;
		int c;

		public word(String w, int c) {
			this.w = w;
			this.c = c; 
		}

		public String toString() {
			return w + "|" + c; 
		}
	}

	public static void main(String[] args) throws Exception {
		HashMap<String, Integer> freq = new HashMap<String, Integer>();
		Scanner s = new Scanner(new File("input.txt"));
		StringBuilder sb; 
		String str; 

		while (s.hasNext()){
			sb = new StringBuilder(s.next()); 
			for(int i = sb.length()-1; i >= 0; i--) {
				if(!Character.isLetter(sb.charAt(i))) {
					sb.deleteCharAt(i);
				}
				else if(sb.charAt(i) >= 'A' && sb.charAt(i) <= 'Z') {
					sb.setCharAt(i, (char) (sb.charAt(i) + 32)); 
				}
			}

			str = sb.toString(); 

			if(str.length() != 0) {
				Integer total = freq.get(str); 
				if(total == null) {
					freq.put(str, 1);
				}
				else {
					freq.put(str, total+1);
				} 
			}
		}
		
		List<word> list = new ArrayList<word>(); 
		for (String w: freq.keySet()) { 
            list.add(new word(w, freq.get(w)));
		} 

		Collections.sort(list, new Comparator<word>() {
			public int compare(word w, word o) {
				return o.c - w.c; 
			}
		});

		PrintStream out = new PrintStream(new FileOutputStream("solution_sb.txt"));

		for(int i = 0; i < list.size(); i++)			
			out.println(list.get(i));
	}
}