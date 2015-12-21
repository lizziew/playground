import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList; 

public class Day6 {
	static int[][] lights = new int[1000][1000]; 

	public static void turnOn(int x1, int y1, int x2, int y2) {
		for(int i = x1; i <= x2; i++)
			for(int j = y1; j <= y2; j++)
				lights[i][j]++; 
	}

	public static void turnOff(int x1, int y1, int x2, int y2) {
		for(int i = x1; i <= x2; i++)
			for(int j = y1; j <= y2; j++)
				if(lights[i][j] >= 1) lights[i][j]--; 
	}

	public static void toggleLights(int x1, int y1, int x2, int y2) {
		for(int i = x1; i <= x2; i++)
			for(int j = y1; j <= y2; j++)
				lights[i][j] += 2;  
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

		        //GET RECTANGLES 
		        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

		        int firstRectPos = 2; 
		        if(input.get(0).equals("toggle")) firstRectPos = 1; 

				String rect1 = input.get(firstRectPos); 
				int commapos = rect1.indexOf(",");
				x1 = Integer.parseInt(rect1.substring(0, commapos));
				y1 = Integer.parseInt(rect1.substring(commapos+1)); 

				String rect2 = input.get(firstRectPos+2); 
				commapos = rect2.indexOf(",");
				x2 = Integer.parseInt(rect2.substring(0, commapos));
				y2 = Integer.parseInt(rect2.substring(commapos+1)); 

		        //GET INSTRUCTION
		       	if(input.get(0).equals("turn")) {
		       		if(input.get(1).equals("on")) turnOn(x1, y1, x2, y2); 
		       		else if(input.get(1).equals("off")) turnOff(x1, y1, x2, y2);
		       	}
		       	else if(input.get(0).equals("toggle")) 
		       		toggleLights(x1, y1, x2, y2); 

				t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   		int total = 0; 
   		for(int i = 0; i < 1000; i++)
   			for(int j = 0; j < 1000; j++)
   				total += lights[i][j]; 
   		System.out.println(total); 
	}
}