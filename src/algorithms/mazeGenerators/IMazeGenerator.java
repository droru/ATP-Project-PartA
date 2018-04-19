package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**
     * generates a new maze with the given dimensions
     * @param numRows - the height of the desirable maze
     * @param numColumns - the width of the desirable maze
     * @return the generated maze
     */
    Maze generate(int numRows, int numColumns);

    /**
     * calculate the time for generating a maze with the given dimensions
     * @param numRows - the height of the desirable maze
     * @param numColumns - the width of the desirable maze
     * @return the amount of time took to generate a maze
     */
    Long measureAlgorithmTimeMillis(int numRows, int numColumns);
}
