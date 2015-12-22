import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import org.json.*; 

//with help from thucdx's solution in the aoc subreddit

public class Day12 {
	static int totalnum(Object o) {
		if(o instanceof Integer) return (int) o; 
		else if(o instanceof String) return 0; 
		else if(o instanceof JSONArray) {
			JSONArray a = (JSONArray) o; 
			int ans = 0; 
			for(int i = 0; i < a.length(); i++) 
				ans += totalnum(a.get(i)); 
			return ans; 
		}
		else if(o instanceof JSONObject) {
			int ans = 0; 
			JSONObject j = (JSONObject) o; 
			JSONArray a = j.names(); 
			for(int i = 0; i < a.length(); i++) {
				if(j.get((String) a.get(i)).equals("red"))
					return 0; 
				ans += totalnum(j.get((String) a.get(i)));
			}
			return ans; 
		}
		else return 0; 
	}

	public static void main(String[] args) {
		File file = new File("input.txt");

		try {
			Scanner s = new Scanner(file);  
			JSONArray array = new JSONArray(s.nextLine()); 
			System.out.println(totalnum(array));
			
			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}
   		
	}
}