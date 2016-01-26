import java.io.*;
import java.util.*; 

public class wordfreq {
	static class word {
		String w;
		int c;

		public word(String w, int c) {
			this.w = w;
			this.c = c; 
		}

		public String toString() {
			return w + " " + c; 
		}
	}

	public static void main(String[] args) {
		HashMap<String, Integer> freq = new HashMap<String, Integer>();
		try {
			Scanner s = new Scanner(new File("input.txt"));
			while (s.hasNext()){
				String str = s.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
				if(!str.equals("")) {
					if(freq.containsKey(str)) {
						freq.put(str, freq.get(str)+1); 
					}
					else {
						freq.put(str, 1); 
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

			PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
			System.setOut(out);

			for(int i = 0; i < list.size(); i++)			
				System.out.println(list.get(i));
		}
		catch(FileNotFoundException e) {
			System.out.println(e); 
		}
	}
}