package IO;

import com.sun.deploy.util.ArrayUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.oracle.jrockit.jfr.ContentType.Bytes;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    static final int metaData = 4*3;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {

    }

    /**
     * compress the given b array and write to the file
     * @param b - an array with the content:
     * index 0 - width of the maze
     * index 1 - height of the maze
     * index 2 - start index
     * index 3 - goal index
     * index from 4 to the end - the content of the maze (row by row)
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        byte lastByte = 0;
        int count = 0;
        List compressedBytes = new ArrayList();
        out.write(Arrays.copyOfRange(b, 0, metaData));
        for(int i=metaData; i < b.length; i++){
            if(b[i] == lastByte)
                count++;
            else{
                while(count>256){
                    compressedBytes.add((byte)255);
                    compressedBytes.add((byte)0);
                    //out.write(255);
                    //out.write(0);
                    count = count - 255;
                }
                compressedBytes.add((byte)count);
                //out.write(count);
                count = 1;
                if (lastByte == 0)
                    lastByte = 1;
                else
                    lastByte = 0;
            }
        }
        compressedBytes.add((byte)count);
        //out.write(count);
        out.write(toByteArray(compressedBytes));

        System.out.println ((compressedBytes.size()/b.length)*100);
    }

    private byte[] toByteArray(List<Byte> list){
        byte[] result = new byte[list.size()];
        for(int i = 0; i < list.size(); i++) {
            result[i] = (byte)list.get(i);
        }
        return result;
    }
}
