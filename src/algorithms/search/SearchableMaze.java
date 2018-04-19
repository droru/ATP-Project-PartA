package algorithms.search;
import algorithms.mazeGenerators.Maze;
import java.util.List;

public class SearchableMaze extends Maze implements ISearchable{
    public SearchableMaze(int[][] maze) {
        super(maze);
    }

    public SearchableMaze(int row, int col) {
        super(row, col);
    }

    @Override
    public List<Astate> getAllPossibleStates(Astate state) {
        return null;
    }
}
