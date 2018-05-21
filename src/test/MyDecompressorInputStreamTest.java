import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MyDecompressorInputStreamTest {
    @Test
    void read(){
        int [][]temp={
                {1, 1, 1 ,1 ,1 ,1 ,1 ,1 ,1 ,1},
                {0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,1},
                {0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,1},
                {0 ,0 ,0 ,0 ,0 ,1 ,0 ,0, 0 ,1},
                {1 ,1 ,1 ,1 ,0, 1 ,1 ,1 ,0 ,1},
                {0 ,1, 0, 0, 0 ,1 ,0 ,0 ,0 ,0},
                {0 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,1 ,1},
                {0 ,0 ,0 ,1 ,0, 0, 0 ,1 ,0 ,1},
                {0 ,1 ,0 ,1 ,0 ,1, 1,1 ,0 ,1 },
                {0, 1 ,0 ,1, 0, 0, 0, 0, 0,1}};
        Position start=new Position(7,0);
        Position End=new Position(5,9);
        Maze maze =new Maze(temp);
        maze.setStartPositon(start);
        maze.setGoalPosition(End);

        String mazeFileName = "savedMaze.maze";
        try {
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = savedMazeBytes[0]<<24 | savedMazeBytes[1]<<16 | savedMazeBytes[2]<<8 | savedMazeBytes[3];
        assertEquals(10, width);
        int startInd = savedMazeBytes[4]<<24 | savedMazeBytes[5]<<16 | savedMazeBytes[6]<<8 | savedMazeBytes[7];
        assertEquals(12+70, startInd);
        int goalInd = savedMazeBytes[8]<<24 | savedMazeBytes[9]<<16 | savedMazeBytes[10]<<8 | savedMazeBytes[11];
        assertEquals(12+50+9, goalInd);

        assertEquals(1, savedMazeBytes[12]);
        assertEquals(1, savedMazeBytes[13]);
        assertEquals(1, savedMazeBytes[14]);
        assertEquals(1, savedMazeBytes[15]);
        assertEquals(1, savedMazeBytes[16]);
        assertEquals(1, savedMazeBytes[17]);
        assertEquals(1, savedMazeBytes[18]);
        assertEquals(1, savedMazeBytes[19]);
        assertEquals(1, savedMazeBytes[20]);
        assertEquals(1, savedMazeBytes[21]);

        assertEquals(0, savedMazeBytes[22]);
        assertEquals(1, savedMazeBytes[23]);
        assertEquals(0, savedMazeBytes[24]);
        assertEquals(1, savedMazeBytes[25]);
        assertEquals(0, savedMazeBytes[26]);
        assertEquals(0, savedMazeBytes[27]);
        assertEquals(0, savedMazeBytes[28]);
        assertEquals(0, savedMazeBytes[29]);
        assertEquals(0, savedMazeBytes[30]);

        assertEquals(0, savedMazeBytes[102]);
        assertEquals(1, savedMazeBytes[103]);
        assertEquals(0, savedMazeBytes[104]);
        assertEquals(1, savedMazeBytes[105]);
        assertEquals(0, savedMazeBytes[106]);
        assertEquals(0, savedMazeBytes[107]);
        assertEquals(0, savedMazeBytes[108]);
        assertEquals(0, savedMazeBytes[109]);
        assertEquals(0, savedMazeBytes[110]);
        assertEquals(1, savedMazeBytes[111]);
    }
}