package test;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyCompressorOutputStreamTest {

    @Test
    void write() {
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
            //InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            InputStream in = new FileInputStream(mazeFileName);
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

        assertEquals(0, savedMazeBytes[12]);
        assertEquals(10, savedMazeBytes[13]);
        assertEquals(1, savedMazeBytes[14]);
        assertEquals(1, savedMazeBytes[15]);
        assertEquals(1, savedMazeBytes[16]);
        assertEquals(1, savedMazeBytes[17]);
        assertEquals(5, savedMazeBytes[18]);

    }
}