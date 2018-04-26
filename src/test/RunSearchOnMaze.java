package test;
import algorithms.mazeGenerators.*;
//import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();

        //IMazeGenerator mg = new SimpleMazeGenerator();
        IMazeGenerator mg=new MyMazeGenerator();
        Maze maze = mg.generate( 10,15);
        /*int [][] temp={{1,1,1,1,1,1,},
                       {1,0,0,0,0,1},
                       {1,0,1,1,0,1},
                       {0,0,1,1,0,1},
                       {1,0,0,0,0,0},
                       {1,1,1,1,1,1}};

        int [][]temp2={{1, 1, 1 ,1 ,1 ,1 ,1 ,1 ,1 ,1},{0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,1},
                       {0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,1 },{0 ,0 ,0 ,0 ,0 ,1 ,0 ,0, 0 ,1},
                        {1 ,1 ,1 ,1 ,0, 1 ,1 ,1 ,0 ,1},{0 ,1, 0, 0, 0 ,1 ,0 ,0 ,0 ,0},
                        {0 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,1 ,1},{0 ,0 ,0 ,1 ,0, 0, 0 ,1 ,0 ,1},
                        {0 ,1 ,0 ,1 ,0 ,1,1 ,1 ,0 ,1 },{0, 1 ,0 ,1, 0, 0, 0, 0, 0,1}};

       int [][]temp2={{1 ,1 ,1 ,1 ,1 ,1 ,1, 1, 1 ,1},{0, 1, 0 ,0 ,0 ,0 ,0 ,0 ,0, 1},
                       {0 ,1 ,0 ,1 ,1 ,1 ,1 ,1 ,0 ,1},{0, 0, 0, 0 ,0 ,1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 0, 1, 0, 1, 1, 1},{0 ,0 ,0 ,0 ,0, 1 ,0, 0, 0,0 },
                        {0 ,1 ,1, 1, 0, 1, 0, 1, 1, 1 },{0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                        {1, 1, 1, 1, 1, 1, 0, 1, 0, 1 },{0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};

       // Position start=new Position(9,0);
       // Position End=new Position(5,9);
        Maze maze =new Maze(temp2);
        maze.setStartPositon(start);
        maze.setGoalPosition(End);
  */
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        //solveProblem(searchableMaze, new DepthFirstSearch());
        //solveProblem(searchableMaze, new BestFirstSearch());

        Long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath;
       if(solution!=null){
        solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s", i, solutionPath.get(i)));
        }
        }
    }
}

