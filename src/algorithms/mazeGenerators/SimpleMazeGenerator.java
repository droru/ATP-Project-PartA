package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int numRows, int numColumns) {
        Maze maze = new Maze(numRows, numColumns);

        //start point
        Random rand = new Random();
        maze.setStartPositon(new Position(rand.nextInt(numRows), 0));

        //end point
        maze.setStartPositon(new Position(rand.nextInt(numRows), numColumns-1));

        return maze;
    }
}
