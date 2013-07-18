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

import java.io.InputStream;
import java.io.IOException;  

public class ByteLineReader {
  private byte[] input; 
  private InputStream inputstream; 
  private byte[] line; 
  private final int bufferSize = 4194304; 
  private int doneLen; //number of bytes in the buffer that were already accounted for
  private int inputLen; //total number of bytes read into buffer

  public ByteLineReader(InputStream inputstream) {
    this.inputstream = inputstream;

    input = new byte[bufferSize];  
    line = new byte[1024]; 

    doneLen = 0;
    inputLen = 0; 
  }

  public int readLine() throws IOException {
    int currLineLen = 0;  

    while(true) {
      if(doneLen >= inputLen) {  
	int readLen = inputstream.read(input, 0, bufferSize); 
	while(readLen == 0) 
	  readLen = inputstream.read(input, 0, bufferSize); 
	inputLen = readLen; 
	doneLen = 0; 
      }  
      if(inputLen == -1) //EOF 
	return currLineLen; 

      int linebreak = -1; 
      for(int i = doneLen; i < inputLen; i++) 
	if(input[i] == '\n' || input[i] == '\r') {
	  linebreak = i;
	  break; 
	}
      
      if(linebreak == -1) {
	append(doneLen, currLineLen, inputLen - doneLen); 
	currLineLen += (inputLen - doneLen); 
	doneLen = inputLen; 
      } else {
	append(doneLen, currLineLen, linebreak - doneLen); 
	currLineLen += (linebreak - doneLen); 
	doneLen = linebreak + 1; 
	return currLineLen; 
      }
    }
  }

  private void append(int srcStart, int destStart, int len) {
    int totalLineLen = line.length;  
    if(totalLineLen <= destStart + len) {
      if(totalLineLen * 2 <= destStart + len) 
	totalLineLen += len;
      totalLineLen *= 2; 
      byte[] temp = new byte[totalLineLen]; 
      System.arraycopy(line, 0, temp, 0, line.length);
      line = temp; 
    }
    System.arraycopy(input, srcStart, line, destStart, len); 
  }

  public byte[] getLine() {
    return line; 
  }

  public void close() throws IOException{
    inputstream.close(); 
  }
} 