package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    /**
     * constructor with 2 int parameters
     * @param row - int that represents the row for the state
     * @param column - int that represents the column for the state
     */
    public MazeState(int row, int column) {
        super(row, column);
    }

    /**
     * constructor with a position parameter
     * @param position - a position for the state
     */
    public MazeState(Position position) {
        super(position);
    }

    @Override
    public int compareTo(Object o) {
        int sumThis = this.getPos().getRow()+this.getPos().getColumn();
        int sumOther = ((MazeState)o).getPos().getRow()+((MazeState)o).getPos().getColumn();
        if(sumOther > sumThis)
            return 1;
        else if (sumThis == sumOther)
            return 0;
        else
            return -1;
    }
}
