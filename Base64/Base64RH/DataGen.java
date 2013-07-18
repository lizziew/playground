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

import java.io.IOException;
import java.util.Random;
import java.io.FileWriter;
import java.io.FileOutputStream; 

public class DataGen {
  private static void genData(FileOutputStream out, int count) throws IOException {
    int numRows = 1000000; 

    byte[] randomData = new byte[count]; 

    while (numRows-->0) {
      new Random().nextBytes(randomData);
      byte[] enc = Base64.encodeBytesToBytes(randomData); 
      out.write(enc);
      out.write('\n');
    }
    out.close(); 
  }

  public static void main(String[] args) throws IOException {
    String[] names = {args[0] + "/1K", args[0] + "/4K", args[0] + "/8K"};
    int[] bytesRow = {1000, 4000, 8000};
    for(int i = 0; i < 3; i++) {
      FileOutputStream out = new FileOutputStream(names[i]); 
      genData(out, bytesRow[i]); 
    } 
  }
}