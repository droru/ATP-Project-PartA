package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position pos;

    /**
     * constructor with 2 int parameters
     * @param row - int that represents the row for the state
     * @param column - int that represents the column for the state
     */
    public MazeState(int row, int column) {
        super();
        pos=new Position(row,column);
    }

    /**
     * constructor with a position parameter and a boolean flag
     * @param position - a position for the state
     * @param isDiagonal - is the step to this position was made in diagonal or not?
     */
    public MazeState(Position position, boolean isDiagonal) {
        super(isDiagonal);
        this.pos = position;
    }

    /**
     * constructor with a position parameter
     * @param position - a position for the state
     */
    public MazeState(Position position) {
        this.pos = position;
    }

    @Override
    public Object getPos() {
        return pos;
    }
}
