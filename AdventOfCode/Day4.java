import java.security.MessageDigest; 
import java.math.BigInteger; 

public class Day4 {
	public static boolean isValid(String hashtext) {
		for(int i = 0; i < 6; i++)
			if(hashtext.charAt(i) != '0')
				return false;
		return true; 
	}

	public static int first5Zeros(String input) {
		try {
			int i = 1; 

			while(true) {
				String plaintext = input + Integer.toString(i); 

				//md5 hash 
				MessageDigest m = MessageDigest.getInstance("MD5");
				m.reset();
				m.update(plaintext.getBytes());
				byte[] digest = m.digest();
				BigInteger bigInt = new BigInteger(1,digest);
				String hashtext = bigInt.toString(16);
				while(hashtext.length() < 32 ){
				  hashtext = "0"+hashtext;
				}

				if(isValid(hashtext)) return i; 

				i++; 
			}
			
		} catch (java.security.NoSuchAlgorithmException e) {
    	}

    	return -1; 
	}

	public static void main(String[] args) {
		System.out.println(first5Zeros("yzbqklnj"));
	}
}