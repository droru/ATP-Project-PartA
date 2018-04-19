package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int numRows, int numColumns) {
        Maze maze = new Maze(numRows, numColumns);

        return maze;
    }
}
