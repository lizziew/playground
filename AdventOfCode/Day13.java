import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 

public class Day13 {
	static HashMap<String, Integer> relations = new HashMap<String, Integer>(); 
	static HashSet<String> names_set = new HashSet<String>(); 
	static ArrayList<String> names = new ArrayList<String>(); 
	static int maxHappiness = Integer.MIN_VALUE;

	public static void genPerm(ArrayList<String> res, ArrayList<String> remaining) {
		if(remaining.size() == 0) {
			int totalHappiness = 0;
			for(int i = 0; i < res.size(); i++) {
				String curr = res.get(i);
				String n1 = res.get((i-1+res.size())%res.size()); 
				String n2 = res.get((i+1)%res.size()); 
				totalHappiness += (relations.get(curr+n1) + relations.get(curr+n2)); 
			}
			if(totalHappiness > maxHappiness) maxHappiness = totalHappiness;
			return;
		}

		for(int i = 0; i < remaining.size(); i++) {
			String curr = remaining.get(i);

			res.add(curr);
			remaining.remove(i);

			genPerm(res, remaining);

			res.remove(res.size()-1);
			remaining.add(i, curr); 
		}
	}

	public static void main(String[] args) {
		File file = new File("input.txt");

		try {
			Scanner s = new Scanner(file);  

			while(s.hasNextLine()) {
				Scanner t = new Scanner(s.nextLine()); 
				ArrayList<String> input = new ArrayList<String>();
		        while(t.hasNext()) input.add(t.next());

		        String p1 = input.get(0);
		        String p2 = input.get(10);
		        p2 = p2.substring(0, p2.length()-1); 

		        if(input.get(2).equals("gain")) relations.put(p1 + p2, Integer.parseInt(input.get(3)));
		        else if(input.get(2).equals("lose")) relations.put(p1 + p2, -1 * Integer.parseInt(input.get(3)));

		        names_set.add(p1);
		        names_set.add(p2); 

				t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		for(String n : names_set) 
   			names.add(n); 
   		names.add("Lizzie");

   		for(String n : names_set) {
   			relations.put("Lizzie" + n, 0);
   			relations.put(n + "Lizzie", 0); 
   		}

   		ArrayList<String> res = new ArrayList<String>(); 
   		genPerm(res, names);
   		System.out.println(maxHappiness); 
	}
}