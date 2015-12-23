import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 

public class Day23 { 
	static int a = 1;
	static int b = 0; 

	static ArrayList<ArrayList<String>> instructions = new ArrayList<ArrayList<String>>(); 

	public static void half(String x) {
		if(x.equals("a")) a /= 2;
		else b /= 2; 
	}

	public static void triple(String x) {
		if(x.equals("a")) a *= 3;
		else b *= 3; 
	}

	public static void inc(String x) {
		if(x.equals("a")) a++;
		else b++; 	
	}

	public static void main(String[] args) {
		File file = new File("input.txt");

		try {
			Scanner s = new Scanner(file);  

			while(s.hasNextLine()) {
				Scanner t = new Scanner(s.nextLine()); 

				ArrayList<String> input = new ArrayList<String>();
		        while(t.hasNext()) input.add(t.next());

		        instructions.add(input);

				t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		int i = 0; 
   		while(i < instructions.size() && i >= 0) {
   			System.out.println(i + "..." + a + "..." + b);

   			ArrayList<String> input = instructions.get(i);

	        String cmd = input.get(0);


	        if(cmd.equals("hlf")) {
	        	half(input.get(1)); 
	        	i++; 
	        }
	        else if(cmd.equals("tpl")) {
	        	triple(input.get(1)); 
	        	i++; 
	        }
	        else if(cmd.equals("inc")) {
	        	inc(input.get(1)); 
	        	i++; 
	        }
	        else if(cmd.equals("jmp")) i+= Integer.parseInt(input.get(1)); 
	        else if(cmd.equals("jie")) {
	        	String r = input.get(1).substring(0, input.get(1).length()-1); 
	        	if(r.equals("a") && a%2==0) i += Integer.parseInt(input.get(2));
	        	else if(r.equals("b") && b%2 == 0) i+= Integer.parseInt(input.get(2));
	        	else i++; 
	        }
	        else if(cmd.equals("jio")) {
	        	String r = input.get(1).substring(0, input.get(1).length()-1); 
	        	if(r.equals("a") && a==1) i+= Integer.parseInt(input.get(2));
	        	else if(r.equals("b") && b== 1) i+= Integer.parseInt(input.get(2));	
	        	else i++; 
	        }
   		}

   		System.out.println("a: " + a + " b: " + b);
	}
}