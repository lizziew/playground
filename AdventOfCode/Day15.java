import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 
import java.lang.Math; 

public class Day15 {
	static int[][] factors = new int[4][5]; 

	public static void main(String[] args) {
		File file = new File("input.txt");

		try {
			Scanner s = new Scanner(file);  
			int i = 0; 
			while(s.hasNextLine()) {
				String line = s.nextLine();
				Scanner t = new Scanner(line); 
				
				ArrayList<String> input = new ArrayList<String>();
		        while(t.hasNext()) input.add(t.next());

		        factors[i][0] = Integer.parseInt(input.get(2).substring(0, input.get(2).length()-1)); 
		        factors[i][1] = Integer.parseInt(input.get(4).substring(0, input.get(4).length()-1)); 
		        factors[i][2] = Integer.parseInt(input.get(6).substring(0, input.get(6).length()-1)); 
		        factors[i][3] = Integer.parseInt(input.get(8).substring(0, input.get(8).length()-1)); 
		        factors[i][4] = Integer.parseInt(input.get(10));   

		       	i++; 

				t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		int maxScore = 0; 

   		for(int a = 0; a <= 100; a++)
   			for(int b = 0; b <= 100; b++)
   				for(int c = 0; c <= 100; c++) 
   					if(a+b+c <= 100) {
   						int d = 100-a-b-c; 

   						int calories = a*factors[0][4]+b*factors[1][4]+c*factors[2][4]+d*factors[3][4]; 
   						if(calories != 500) continue; 

   						int capacity = Math.max(0, a*factors[0][0]+b*factors[1][0]+c*factors[2][0]+d*factors[3][0]);
   						int durability = Math.max(0, a*factors[0][1]+b*factors[1][1]+c*factors[2][1]+d*factors[3][1]);
   						int flavor = Math.max(0, a*factors[0][2]+b*factors[1][2]+c*factors[2][2]+d*factors[3][2]);
   						int texture = Math.max(0, a*factors[0][3]+b*factors[1][3]+c*factors[2][3]+d*factors[3][3]);

   						int currScore = capacity*durability*flavor*texture; 
   						if(currScore > maxScore) maxScore = currScore; 
   					}

   		System.out.println(maxScore);
	}
}