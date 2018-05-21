import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    void solve() {
        //10*10
        int [][]temp2={
                {1, 1, 1 ,1 ,1 ,1 ,1 ,1 ,1 ,1},
                {0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,1},
                {0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,1},
                {0 ,0 ,0 ,0 ,0 ,1 ,0 ,0, 0 ,1},
                {1 ,1 ,1 ,1 ,0, 1 ,1 ,1 ,0 ,1},
                {0 ,1, 0, 0, 0 ,1 ,0 ,0 ,0 ,0},
                {0 ,1 ,0 ,1 ,0 ,1 ,0 ,0 ,1 ,1},
                {0 ,0 ,0 ,1 ,0, 0, 0 ,1 ,0 ,1},
                {0 ,1 ,0 ,1 ,0 ,1, 1 ,1 ,0 ,1},
                {0, 1 ,0 ,1, 0, 0, 0 ,0, 0 ,1}};
        Position start=new Position(7,0);
        Position End=new Position(5,9);
        Maze maze =new Maze(temp2);
        maze.setStartPositon(start);
        maze.setGoalPosition(End);

        BestFirstSearch best = new BestFirstSearch();
        Solution solution = best.solve(new SearchableMaze(maze));
        ArrayList<AState> expextedSolution =new ArrayList<AState>()
        {{add(new MazeState(7, 0));
                add(new MazeState(7, 1));
                //add(new MazeState(7, 2));
                add(new MazeState(6, 2));
                //add(new MazeState(5, 2));
                add(new MazeState(5, 3));
                //add(new MazeState(5, 4));
                add(new MazeState(6, 4));
                //add(new MazeState(7, 4));
                add(new MazeState(7, 5));
                //add(new MazeState(7, 6));
                add(new MazeState(6, 6));
                //add(new MazeState(6, 7));
                add(new MazeState(5, 7));
                add(new MazeState(5, 8));
                add(new MazeState(5, 9));}};
        ArrayList<AState> actualSolution = solution.getSolutionPath();
        assertEquals(expextedSolution, actualSolution);
        assertEquals(29, best.getNumberOfNodesEvaluated());


        //without exit
        int [][] temp={
                {1,1,1,1,1,1},
                {1,0,0,0,0,1},
                {1,0,1,1,0,1},
                {0,0,1,1,0,1},
                {1,0,0,0,0,1},
                {1,1,1,1,1,1}};
        start=new Position(3,0);
        End=new Position(4,5);
        maze =new Maze(temp);
        maze.setStartPositon(start);
        maze.setGoalPosition(End);
        best = new BestFirstSearch();
        solution = best.solve(new SearchableMaze(maze));
        assertEquals(null, solution);
        //assertEquals(13, best.getNumberOfNodesEvaluated());


        //with value 2 in a cell
        int [][] temp1={
                {1,1,1,1,1,1},
                {1,0,0,0,0,1},
                {1,0,1,1,0,1},
                {0,0,1,1,0,1},
                {1,0,0,2,0,0},
                {1,1,1,1,1,1}};
        start=new Position(3,0);
        End=new Position(4,5);
        maze =new Maze(temp1);
        maze.setStartPositon(start);
        maze.setGoalPosition(End);
        best = new BestFirstSearch();
        solution = best.solve(new SearchableMaze(maze));
        expextedSolution =new ArrayList<AState>()
        {{add(new MazeState(3, 0));
            add(new MazeState(3, 1));
            //add(new MazeState(4, 1));
            add(new MazeState(4, 2));
            add(new MazeState(4, 3));
            add(new MazeState(4, 4));
            add(new MazeState(4, 5));}};
        assertEquals(expextedSolution, solution.getSolutionPath());
        //assertEquals(6, best.getNumberOfNodesEvaluated());

        //row of null
        int [][] temp3={
                {1,1,1,1,1,1},
                {1,0,0,0,0,1},
                {1,0,1,1,0,1},
                {0,0,1,1,0,1},
                {1,0,0,0,0,0},
                null};
    }
}