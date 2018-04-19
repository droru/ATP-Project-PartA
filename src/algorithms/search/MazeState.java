package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState {
    public MazeState(int row, int column) {
        super(row, column);
    }

    public MazeState(Position position) {
        super(position);
    }
}
