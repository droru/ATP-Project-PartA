package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable{
    private Maze maze;

    /**
     * constructor with maze parameter
     * @param maze - the maze for the current searchable maze
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState(){
        return new MazeState(maze.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition());
    }

    @Override
    public List<AState> getAllPossibleStates(AState state) {
        if(state == null) return null;
        Object obj = state.getPos();
        if(!(obj instanceof Position)) return null;
        Position pos = (Position)obj;

        ArrayList<AState> possibleStates = new ArrayList<>();
        int row = pos.getRow();
        int col = pos.getColumn();
        Position up = new Position(row-1, col);
        Position upright = new Position(row-1, col+1);
        Position right = new Position(row, col+1);
        Position rightdown = new Position(row+1, col+1);
        Position down = new Position(row+1, col);
        Position downleft = new Position(row+1, col-1);
        Position left = new Position(row, col-1);
        Position leftup = new Position(row-1, col-1);

        if(isValidPosition(up))
            possibleStates.add(new MazeState(up, false));
        if(isValidPosition(right))
            possibleStates.add(new MazeState(right, false));
        if(isValidPosition(down))
            possibleStates.add(new MazeState(down, false));
        if(isValidPosition(left))
            possibleStates.add(new MazeState(left, false));
        if(isValidPosition(upright) && (isValidPosition(up) || isValidPosition(right)))
            possibleStates.add(new MazeState(upright, true));
        if(isValidPosition(rightdown) && (isValidPosition(right) || isValidPosition(down)))
            possibleStates.add(new MazeState(rightdown, true));
        if(isValidPosition(downleft) && (isValidPosition(down) || isValidPosition(left)))
            possibleStates.add(new MazeState(downleft, true));
        if(isValidPosition(leftup) && (isValidPosition(left) || isValidPosition(up)))
            possibleStates.add(new MazeState(leftup, true));
        return possibleStates;
    }

    /**
     * check if the given state is valid:
     * 1. the state is inside the maze bounds
     * 2. the value of the state is not wall
     * @param position - a maze position to check
     * @return bool if the given state is valid
     */
    private boolean isValidPosition(Position position) {
        int row = position.getRow();
        int col = position.getColumn();
        if(row<0 || row>=maze.getNumRows())
            return false;
        if(col<0 || col>=maze.getNumColumns())
            return false;
        if (maze.getMaze()[row][col] == 1)
            return false;

        return true;
    }
}
