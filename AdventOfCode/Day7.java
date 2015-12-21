import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList; 
import java.util.HashMap; 

public class Day7 {
	static HashMap<String, ArrayList<String>> instructions = new HashMap<String, ArrayList<String>>(); 
	static HashMap<String, Integer> results = new HashMap<String, Integer>(); 

	public static int not(String wire1, String wire2) {
		int val = (~calculate(wire1) + 65536) % 65536; 
		results.put(wire2, val);
		return val; 
	}

	public static int or(String wire1, String wire2, String wire3) {
		int val = (calculate(wire1) | calculate(wire2) + 65536) % 65536;
		results.put(wire3, val);
		return val;  
	}

	public static int and(String wire1, String wire2, String wire3) {
		int val = (calculate(wire1) & calculate(wire2) + 65536) % 65536;
		results.put(wire3, val);
		return val;  
	}

	public static int lshift(String wire1, String b, String wire2) {
		int val = calculate(wire1) << Integer.parseInt(b); 
		results.put(wire2, val);
		return val; 
	}

	public static int rshift(String wire1, String b, String wire2) {
		int val = calculate(wire1) >> Integer.parseInt(b); 
		results.put(wire2, val);
		return val; 
	}

	public static boolean isInteger(String x) {
		try {
        	Integer.parseInt(x);
        	return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}

	public static int signal(String x, String wire) {
		int val;
		if(isInteger(x)) val = Integer.parseInt(x); 
		else val = calculate(x);

		results.put(wire, val);
		return val; 
	}

	public static int calculate(String wire) {
		System.out.println(wire);
		if(results.containsKey(wire)) return results.get(wire);
		if(isInteger(wire)) return Integer.parseInt(wire); 

		ArrayList<String> input = instructions.get(wire); 

		if(input.get(0).equals("NOT")) return not(input.get(1), input.get(3));  
		else if(input.get(1).equals("OR")) return or(input.get(0), input.get(2), input.get(4));
		else if(input.get(1).equals("AND")) return and(input.get(0), input.get(2), input.get(4));
		else if(input.get(1).equals("LSHIFT")) return lshift(input.get(0), input.get(2), input.get(4));
		else if(input.get(1).equals("RSHIFT")) return rshift(input.get(0), input.get(2), input.get(4));
		else return signal(input.get(0), input.get(2)); 		
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

		        instructions.put(input.get(input.size()-1), input);

				t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		System.out.println(calculate("a"));
	}
}