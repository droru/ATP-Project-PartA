package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int numRows, int numColumns);
    Long measureAlgorithmTimeMillis(int numRows, int numColumns);
}
