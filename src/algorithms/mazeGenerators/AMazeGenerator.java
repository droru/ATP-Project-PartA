package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * generates a new maze with the given dimensions
     * @param numRows - the height of the desirable maze
     * @param numColumns - the width of the desirable maze
     * @return the generated maze
     */
    public abstract Maze generate(int numRows, int numColumns);

    /**
     * calculate the time for generating a maze with the given dimensions
     * @param numRows - the height of the desirable maze
     * @param numColumns - the width of the desirable maze
     * @return the amount of time took to generate a maze
     */
    public Long measureAlgorithmTimeMillis(int numRows, int numColumns){
        Long startTime = System.currentTimeMillis();
        generate(numRows, numColumns);
        Long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
