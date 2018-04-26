package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze extends Maze implements ISearchable{

    /**
     * constructor with maze parameter
     * @param maze - the maze for the current searchable maze
     */
    public SearchableMaze(Maze maze) {
        super(maze);
    }

    @Override
    public AState getStartState(){
        return new MazeState(getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(getGoalPosition());
    }

    @Override
    public List<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        int row = state.getPos().getRow();
        int col = state.getPos().getColumn();
        MazeState up = new MazeState(row-1, col);
        MazeState right = new MazeState(row, col+1);
        MazeState down = new MazeState(row+1, col);
        MazeState left = new MazeState(row, col-1);
        if(isValidPosition(up))
            possibleStates.add(up);
        if(isValidPosition(right))
            possibleStates.add(right);
        if(isValidPosition(down))
            possibleStates.add(down);
        if(isValidPosition(left))
            possibleStates.add(left);
        return possibleStates;
    }

    /**
     * check if the given state is valid:
     * 1. the state is inside the maze bounds
     * 2. the value of the state is not wall
     * @param state - a maze state to check
     * @return bool if the given state is valid
     */
    private boolean isValidPosition(MazeState state) {
        int row = state.getPos().getRow();
        int col = state.getPos().getColumn();
        if(row<0 || row>=getNumRows())
            return false;
         if(col<0 || col>=getNumColumns())
            return false;
         if (maze[row][col] == 1)
            return false;

        return true;
    }
}
