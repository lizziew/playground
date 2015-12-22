import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*; 

//part 2: with help from KnorbenKnutsen's solution on reddit -> work backwards and reduce to e, instead of trying to construct the molecule

public class Day19 {
	/*static HashMap<String, ArrayList<String>> replacements = new HashMap<String, ArrayList<String>>(); 
	static HashSet<String> results = new HashSet<String>(); */

	static HashMap<String, String> reductions = new HashMap<String, String>();  
	static int minSteps = Integer.MAX_VALUE; 

	public static void reduceMolecule(String m, int n) {
		if(m.equals("e")) {
			//System.out.println(n);
			if(n < minSteps) minSteps = n; 
			return; 
		} 

		for (Map.Entry<String, String> entry : reductions.entrySet()) {
		    String m1 = entry.getKey();
		    String m2 = entry.getValue(); 
		    
		    int i = 0; 
		    while(i < m.length() && m.indexOf(m1, i) != -1) {
		    	int pos = m.indexOf(m1, i); 
		    	String result = m.substring(0, pos) + m2 + m.substring(pos + m1.length()); //replace m1 with m2
		    	reduceMolecule(result, n+1);
		    	i++; 
		    }
		}
	}

	public static void main(String[] args) {
		File file = new File("input.txt");

		String molecule = "CRnSiRnCaPTiMgYCaPTiRnFArSiThFArCaSiThSiThPBCaCaSiRnSiRnTiTiMgArPBCaPMgYPTiRnFArFArCaSiRnBPMgArPRnCaPTiRnFArCaSiThCaCaFArPBCaCaPTiTiRnFArCaSiRnSiAlYSiThRnFArArCaSiRnBFArCaCaSiRnSiThCaCaCaFYCaPTiBCaSiThCaSiThPMgArSiRnCaPBFYCaCaFArCaCaCaCaSiThCaSiRnPRnFArPBSiThPRnFArSiRnMgArCaFYFArCaSiRnSiAlArTiTiTiTiTiTiTiRnPMgArPTiTiTiBSiRnSiAlArTiTiRnPMgArCaFYBPBPTiRnSiRnMgArSiThCaFArCaSiThFArPRnFArCaSiRnTiBSiThSiRnSiAlYCaFArPRnFArSiThCaFArCaCaSiThCaCaCaSiRnPRnCaFArFYPMgArCaPBCaPBSiRnFYPBCaFArCaSiAl"; 

		try {
			Scanner s = new Scanner(file);  

			int i = 0; 
			while(s.hasNextLine()) {
				Scanner t = new Scanner(s.nextLine()); 
				ArrayList<String> input = new ArrayList<String>();
		        while(t.hasNext()) input.add(t.next());

		        String m1 = input.get(0);
		        String m2 = input.get(2); 

		        /*if(replacements.containsKey(m1)) {
		        	ArrayList<String> opts = replacements.get(m1);
		        	opts.add(m2);
		        	replacements.put(m1, opts);
		        }
		        else {
		        	ArrayList<String> opts = new ArrayList<String>();
		        	opts.add(m2);
		        	replacements.put(m1, opts); 
		        }*/

		        reductions.put(m2, m1);

		        t.close();
			}

			s.close(); 
		}
		catch (FileNotFoundException e) {
        	e.printStackTrace();
   		}

		/*for (Map.Entry<String, ArrayList<String>> entry : replacements.entrySet()) {
		    String m = entry.getKey();
		    ArrayList<String> opts = entry.getValue();
		    
		    int i = 0; 
		    while(i < molecule.length() && molecule.indexOf(m, i) != -1) {
		    	int pos = molecule.indexOf(m, i); 
		    	for(int j = 0; j < opts.size(); j++) {
		    		String result = molecule.substring(0, pos) + opts.get(j) + molecule.substring(pos + m.length());
		    		results.add(result); 
		    	}
		    	i++; 
		    }
		}

		System.out.println(results.size());*/ 

		reduceMolecule(molecule, 0);
		System.out.println(minSteps); 
	}
}