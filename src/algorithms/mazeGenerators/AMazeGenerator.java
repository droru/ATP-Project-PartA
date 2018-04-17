package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Maze generate(int numRows, int numColumns);

    public Long measureAlgorithmTimeMillis(int numRows, int numColumns){
        Long startTime = System.currentTimeMillis();
        generate(numRows, numColumns);
        Long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
