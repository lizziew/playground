public class Day25 {
	//row, column --> nth code 
	public static int getN(int r, int c) {
		int leftDiag = (r+c-1)*(r+c-2)/2 + 1; 
		return leftDiag + c-1; 
	}

	//generate nth code
	public static int genNCode(int n) {
		long curr = 20151125; 

		for(int i = 2; i <= n; i++) {
			curr = (curr*252533)%33554393; 
		}

		return (int) curr; 
	}

	public static void main(String[] args) {
		int r = 2981, c = 3075; 

		System.out.println(genNCode(getN(r, c)));
	}
}