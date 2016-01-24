import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 

public class Day17 {
	public static void main(String[] args) {
		File file = new File("input.txt");
		ArrayList<Integer> capacities = new ArrayList<Integer>(); 

		try {
			Scanner s = new Scanner(file);  

			while(s.hasNextLine()) 
		        capacities.add(Integer.parseInt(s.nextLine()));

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		int ways = 0; 
   		int n = capacities.size(); 

   		int minNumContainers = Integer.MAX_VALUE; 

   		for(int i = 1; i < 1 << n; i++) {
   			int total = 0; 
   			int currNumContainers = 0; 
   			for(int j = 0; j < n; j++) 
   				if((i & (1 << j)) == (1 << j)) {
   					total += capacities.get(j); 
   					currNumContainers++; 
   				}
   			if(total == 150) {
   				if(currNumContainers < minNumContainers) {
   					ways = 1; 
   					minNumContainers = currNumContainers; 
   				}
   				else if(currNumContainers == minNumContainers) ways++; 
   			}
   		}

   		System.out.println(ways);
	}
}