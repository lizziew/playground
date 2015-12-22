import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 

public class Day14 { 
	static int time = 1000;
	static ArrayList<int[]> info = new ArrayList<int[]>(); 

	public static int calcDist(int speed, int endurance, int rest, int t) {
		int blockTime = 0; //time since resting/flying 
		boolean isFlying = true; 

		int dist = 0; 

		for(int i = 1; i <= t; i++) {
			if(isFlying) {
				if(blockTime == endurance) {
					blockTime = 1;
					isFlying = false; 
				}
				else {
					dist += speed; 
					blockTime++; 
				}
			}
			else {
				if(blockTime == rest) {
					blockTime = 1;
					isFlying = true; 
					dist += speed; 
				}
				else blockTime++; 
			}
		}

		return dist; 
	}

	public static void main(String[] args) {
		File file = new File("input.txt");
		int maxDist = Integer.MIN_VALUE; 

		int n = 0; 

		try {
			Scanner s = new Scanner(file);  

			while(s.hasNextLine()) {
				Scanner t = new Scanner(s.nextLine()); 
				ArrayList<String> input = new ArrayList<String>();
		        while(t.hasNext()) input.add(t.next());

		        int speed = Integer.parseInt(input.get(3));
		        int endurance = Integer.parseInt(input.get(6));
		        int rest = Integer.parseInt(input.get(13)); 

		        info.add(new int[]{speed, endurance, rest}); 
		        n++; 

				t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

   	 	ArrayList<Integer> bonus = new ArrayList<Integer>(); 
   	 	ArrayList<Integer> dist = new ArrayList<Integer>(); 

   	 	for(int i = 0; i < n; i++) {
   	 		bonus.add(0);
   	 		dist.add(0); 
   	 	}

   		for(int t = 1; t <= time; t++) {
   			for(int i = 0; i < n; i++) {
   				int[] r = info.get(i); 
   				dist.set(i, calcDist(r[0], r[1], r[2], t));
   			}

   			int max_i = 0; 
   			for(int i = 1; i < n; i++) 
   				if(dist.get(i) > dist.get(max_i)) max_i = i; 
   			for(int i = 0; i < n; i++) 
   				if(dist.get(i) == dist.get(max_i))
   					bonus.set(i, bonus.get(i) + 1); 

   			System.out.println(dist.get(0) + "..." + dist.get(1));
   		}

   		for(int i = 0; i < n; i++) {
   			System.out.println(bonus.get(i));
   			if(bonus.get(i) > maxDist)
   				maxDist = bonus.get(i); 
   		}

   		System.out.println(maxDist);
	}
}