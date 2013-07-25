import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        //WRITE FILE
        int len = 200;
        RandomAccessFile file = new RandomAccessFile("output.txt", "rw");
        MappedByteBuffer outputBuffer = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, len);
        for (int i = 0; i < len; i++)
            outputBuffer.put((byte) i);

        //READ FILE
        FileInputStream inputStream = new FileInputStream("output.txt");
        MappedByteBuffer mappedByteBuffer = inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, len);
        for (int i = 0; i < len; i++) {
            if(mappedByteBuffer.get(i) != (byte) i)
               throw new IOException("oops");
        }
        ((sun.nio.ch.DirectBuffer) mappedByteBuffer).cleaner().clean();
    }
}