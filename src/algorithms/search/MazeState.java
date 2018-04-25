package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    public MazeState(int row, int column) {
        super(row, column);
    }

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
