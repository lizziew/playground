import java.io.File;
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 

public class UnrollLoop {
  public static int N = 100000000;
  public static double[] data = new double[100000001]; 
  public static double sum; 

  public static double unroll4() {
    long n = System.nanoTime(); 

    int M = (N>>2)<<2; //greatest multiple of 4 less than N
    double sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0; 
    int index; 

    for(index = 0; index < M; index += 4) {
      sum1 += data[index];
      sum2 += data[index+1];
      sum3 += data[index+2];
      sum4 += data[index+3]; 
    }
    index -= 4;

    switch(N-M) {
    case 1: sum1 += data[index];
    case 2: sum1 += (data[index] + data[index+1]);
    case 3: sum1 += (data[index] + data[index+1] + data[index+2]);
    default: break; 
    }

    sum = sum1 + sum2 + sum3 + sum4; 

    double elapsed = (System.nanoTime() - n)/1e9;
    return elapsed; 
  }

  public static double unroll2() {
    long n = System.nanoTime(); 

    int M = (N>>1)<<1; //greatest multiple of 2 less than N
    double sum1 = 0, sum2 = 0;
    int index; 

    for(index = 0; index < M; index += 2) {
      sum1 += data[index];
      sum2 += data[index+1]; 
    }
    index -= 2;

    if(N-M == 1)
       sum1 += data[index];

    sum = sum1 + sum2; 

    double elapsed = (System.nanoTime() - n)/1e9;
    return elapsed; 
  }


  public static double naive() {
    long n = System.nanoTime(); 

    for(int i = 0; i < N; i++) 
      sum += data[i];

    double elapsed = (System.nanoTime() - n)/1e9;
    return elapsed; 
  }

  public static void main(String[] args) throws IOException {
    File file = new File("data.txt"); 
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String input; 
    int i = 0; 
    while((input = reader.readLine()) != null) {
      data[i] = Double.parseDouble(input);
      i++; 
    }

    double ind; 

    double avgtime = 0; 
    for(int j = 0; j < 5; j++) {
      System.gc(); 
      if(j != 0)
	avgtime += unroll4();
    }
    avgtime /= 5.0; 
    System.out.println("unroll 4: " + avgtime);

    avgtime = 0; 
    sum = 0; 
    for(int j = 0; j < 5; j++) {
      System.gc(); 
      if(j != 0)
	avgtime += unroll2();
    }
    avgtime /= 5.0; 
    System.out.println("unroll 2: " + avgtime);

    avgtime = 0; 
    sum = 0; 
    for(int j = 0; j < 5; j++) {
      System.gc(); 
      if(j != 0) 
	avgtime += naive();
    }
    avgtime /= 5.0; 
    System.out.println("naive version: " + avgtime);
  }
}