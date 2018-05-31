package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    static final int metaData = 4*3;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] b) throws IOException {
        if (b.length == 0) return 0;
        //if (data == -1) return -1;
        in.read(b, 0, metaData);
                //(Arrays.copyOfRange(b, 0, metaData));
        int data = in.read();
        int count = metaData;

        while(data!=-1)
        {
            for (int i=7;i>-1 && count<b.length;i--) {
                b[count] = (byte) (((data & 0xFF) >> i) & 1);
                count++;
            }
            data=in.read();
        }
        /*
        byte lastByte = 0;
        while(data != -1){
            for (int i=0; i < data; i++){
                b[count] = lastByte;
                count++;
            }
            if(lastByte == 0)
                lastByte = 1;
            else
                lastByte = 0;
            data = in.read();
        }*/
        return count;
    }
}
