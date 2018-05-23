package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int numRows, int numColumns) {
        if(numRows <= 0 || numColumns <= 0)
        {
            /* default maze */
            numRows=10;numColumns=10;
        }
        Maze maze = new Maze(numRows, numColumns);

        //start point
        Random rand = new Random();
        maze.setStartPositon(new Position(rand.nextInt(numRows), 0));

        //end point
        maze.setGoalPosition(new Position(rand.nextInt(numRows), numColumns-1));

        //make Path from Start Point to Goal Point
        setPath(maze);

        //initialize the maze with wall 1 or path 0
        initializeMaze(maze);


        return maze;
    }

    /**
     * build the path for the maze
     * @param maze
     */
    private void  setPath(Maze maze){

        boolean done =false;
        Position pos=new Position(maze.getStartPosition().getRow(),maze.getStartPosition().getColumn());

        while (!done){

            if (pos.getColumn()<maze.getGoalPosition().getColumn()) {
                maze.setPosition(pos, 0);
                pos.setColumn(pos.getColumn() + 1);
            }
            if (pos.getColumn()>maze.getGoalPosition().getColumn()) {
                maze.setPosition(pos, 0);
                pos.setColumn(pos.getColumn() - 1);
            }
            if (pos.getRow()<maze.getGoalPosition().getRow()) {
                maze.setPosition(pos, 0);
                pos.setRow(pos.getRow() + 1);
            }
            if (pos.getRow()>maze.getGoalPosition().getRow()) {
                maze.setPosition(pos, 0);
                pos.setRow(pos.getRow() - 1);
            }
            if (pos.equals(maze.getGoalPosition())) {
                maze.setPosition(pos, 0);
                done = true;
            }
        }

    }

    /**
     * initialize all the cells in the maze
     * the frame consists only from walls cells (with value 1)
     * except the start position and the goal position
     * the rest of the cells has random values
     * except the path, the cells that already define as pass (with value 0)
     * @param maze - the Maze to initialize
     */
    private void initializeMaze(Maze maze){

        Position pos=new Position(0,0);
        Random rnd=new Random();

        for (int i=0;i<maze.getNumRows();i++)
          for (int j=0;j<maze.getNumColumns();j++)
          {
              pos.setRow(i);pos.setColumn(j);

              if((i==0||j==0||i==maze.getNumRows()-1||j==maze.getNumColumns()-1) && (maze.getPosition(pos)==-1))
                  maze.setPosition(pos,1);

              else if (maze.getPosition(pos)==-1)
                     maze.setPosition(pos,rnd.nextInt(2));
          }
        }
   }
