/**
 *  Copyright Elizabeth Wei 2013 
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.io.*;

public class TestBase64 {
  private static void test(String name) throws IOException {
    ByteLineReader reader = new ByteLineReader(new FileInputStream("../TestData/" + name)); 
    int inputLen; 
    byte[] inputLine; 
    int numLines = 0; 
    long n = System.nanoTime();

    while((inputLen = reader.readLine()) != 0) {
      numLines++; 
      inputLine = reader.getLine(); 
      byte[] data = Base64.decode(inputLine, 0, inputLen); 
    }

    double elapsed = (System.nanoTime() - n)/1e9;
    System.out.println(elapsed);
  }

  public static void main(String[] args) throws IOException{
    String[] names = {"1K", "4K", "8K"}; 
    for(int i = 0; i < 3; i++) {    
      for (int j = 0; j < 5; j++) {
	System.gc();
	test(names[i]); 
      }
    }
  }
}