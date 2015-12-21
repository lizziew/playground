import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 

public class Day9 {
	static HashMap<String, Integer> dist = new HashMap<String, Integer>(); 
	static HashSet<String> cities_set = new HashSet<String>(); 
	static ArrayList<String> cities = new ArrayList<String>();
	static int maxTotalDist = Integer.MIN_VALUE;  

	public static void genPerm(ArrayList<String> res, ArrayList<String> remaining) {
		if(remaining.size() == 0) {
			int totalDist = 0;
			for(int i = 0; i < res.size()-1; i++) totalDist += dist.get(res.get(i)+res.get(i+1));
			if(totalDist > maxTotalDist) maxTotalDist = totalDist; 
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
				String line = s.nextLine();
				Scanner t = new Scanner(line); 
				
				ArrayList<String> input = new ArrayList<String>();
		        while(t.hasNext()) input.add(t.next());

		        cities_set.add(input.get(0));
		        cities_set.add(input.get(2));

				dist.put(input.get(0) + input.get(2), Integer.parseInt(input.get(4)));	
				dist.put(input.get(2) + input.get(0), Integer.parseInt(input.get(4)));		        

				t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		for(String c : cities_set)
   			cities.add(c);

   		ArrayList<String> res = new ArrayList<String>(); 
   		genPerm(res, cities); 
   		System.out.println(maxTotalDist);
	}
}