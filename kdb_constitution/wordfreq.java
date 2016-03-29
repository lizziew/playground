import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;  

public class wordfreq {
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
		Pattern pattern = Pattern.compile("[^a-zA-Z ]"); 

		while (s.hasNext()){
			String str = s.next();

			str = pattern.matcher(str).replaceAll("").toLowerCase(); 

			if(!str.equals("")) {
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

		PrintStream out = new PrintStream(new FileOutputStream("solution.txt"));

		for(int i = 0; i < list.size(); i++)			
			out.println(list.get(i));
	}
}