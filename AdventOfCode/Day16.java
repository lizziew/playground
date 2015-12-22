import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 

public class Day16 {
	static HashMap<String, Integer> msg = new HashMap<String, Integer>(); 

	public static String val(String x) {
		return x.substring(0, x.length()-1); 
	}

	public static boolean check(String pi, String vi) {
		String p = val(pi);
		int v = 0; 
		if(vi.charAt(vi.length()-1) == ',') v = Integer.parseInt(val(vi)); 
		else v = Integer.parseInt(vi); 

		if(p.equals("cats")) return (msg.get(p) < v); 

		if(p.equals("trees")) return (msg.get(p) < v); 
		
		if(p.equals("pomeranians")) return (msg.get(p) > v); 

		if(p.equals("goldfish")) return (msg.get(p) > v);

		if(msg.get(p) == v) return true;
		else return false; 
	}

	public static void main(String[] args) {
		File file = new File("input.txt");

		msg.put("children", 3);
		msg.put("cats", 7);
		msg.put("samoyeds", 2);
		msg.put("pomeranians", 3);
		msg.put("akitas", 0);
		msg.put("vizslas", 0);
		msg.put("goldfish", 5);
		msg.put("trees", 3);
		msg.put("cars", 2);
		msg.put("perfumes", 1);

		try {
			Scanner s = new Scanner(file);  

			while(s.hasNextLine()) {
				Scanner t = new Scanner(s.nextLine()); 
				ArrayList<String> input = new ArrayList<String>();
		        while(t.hasNext()) input.add(t.next());
		        t.close();

		        String currsue = val(input.get(1)); 

		        if(!check(input.get(2), input.get(3))) continue;
		        if(!check(input.get(4), input.get(5))) continue;
		        if(!check(input.get(6), input.get(7))) continue;

				System.out.println(currsue);
				break; 
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}
	}
}