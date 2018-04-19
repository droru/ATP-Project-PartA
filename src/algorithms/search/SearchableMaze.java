package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.util.List;

public class SearchableMaze extends Maze implements ISearchable{

    public SearchableMaze(int[][] maze) {
        super(maze);
    }

    public SearchableMaze(int row, int col) {
        super(row, col);
    }

    @Override
    public List<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        int row = state.getPos().getRow();
        int col = state.getPos().getColumn();
        Position up = new Position(row-1, col);
        Position right = new Position(row, col+1);
        Position down = new Position(row+1, col);
        Position left = new Position(row, col-1);
        if(isValidPosition(up))
            possibleStates.add(new MazeState(up));
        if(isValidPosition(right))
            possibleStates.add(new MazeState(right));
        if(isValidPosition(down))
            possibleStates.add(new MazeState(down));
        if(isValidPosition(left))
            possibleStates.add(new MazeState(left));
        return possibleStates;
    }

    private boolean isValidPosition(Position position) {
        int row = position.getRow();
        int col = position.getColumn();
        if(row<0 || row>getNumRows())
            return false;
        if(col<0 || col>getNumColumns())
            return false;
        if(maze[row][col] == 1)
            return false;

        return true;
    }
}
