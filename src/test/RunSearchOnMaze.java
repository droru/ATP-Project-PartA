package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
//import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new SimpleMazeGenerator();
        Maze maze = mg.generate(10, 10);
        /*int [][] temp={{1,1,1,1,1,1},
                       {1,0,0,0,0,1},
                       {1,0,1,1,0,1},
                       {0,0,1,1,0,1},
                       {1,0,0,0,0,0},
                       {1,1,1,1,1,1}};
        Position start=new Position(3,0);
        Position End=new Position(4,5);
        Maze maze =new Maze(temp);
        maze.setStartPositon(start);
        maze.setGoalPosition(End);*/
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        //solveProblem(searchableMaze, new DepthFirstSearch());
        //solveProblem(searchableMaze, new BestFirstSearch());

    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
        }
    }
}

