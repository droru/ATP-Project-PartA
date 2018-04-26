package algorithms.mazeGenerators;

public class Maze {
    private Position startPositon;
    private Position goalPosition;
    protected int [][] maze;

    /**
     * constructor from a given array
     * @param maze - 2D int array that represents a maze
     */
    public Maze(int[][] maze) {
        this.maze = maze;
    }

    /**
     * copy constructor for maze
     * @param maze - the maze to copy from
     */
    public Maze(Maze maze) {
        this.maze = maze.getMaze();
        this.startPositon = maze.getStartPosition();
        this.goalPosition = maze.getGoalPosition();
    }

    /**
     * constructor from a given num rows and num columns
     * @param row - num rows for the maze
     * @param col - num columns for the maze
     */
    public Maze(int row, int col) {
        this.maze=new int [row][col];
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {
                this.maze[i][j] = -1;
            }
        }
    }

    /**
     * getter for start position
     * @return the start position of the maze
     */
    public Position getStartPosition() {
        return startPositon;
    }

    /**
     * setter for start position
     * @param startPositon - a position that represents the start of the maze
     */
    public void setStartPositon(Position startPositon) {
        this.startPositon = startPositon;
    }

    /**
     * getter for goal position
     * @return the goal position of the maze
     */
    public Position getGoalPosition() {
        return goalPosition;
    }

    /**
     * setter for goal position
     * @param goalPosition - a position that represents the end of the maze
     */
    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    /**
     * getter for the maze array
     * @return 2D int array of the maze
     */
    public int[][] getMaze() {
        return maze;
    }

    /**
     * setter for a value inside a maze
     * @param pos - the position where to set the value
     * @param val - the int value to set
     *              0 for pass
     *              1 for wall
     */
    public void setPosition(Position pos,int val){
        this.maze[pos.getRow()][pos.getColumn()]=val;
    }

    /**
     * getter for a value inside a maze
     * @param pos - a position on the maze
     * @return the value of the given position
     */
    public int getPosition(Position pos){
        return this.maze[pos.getRow()][pos.getColumn()];
    }

    /**
     * getter for num rows that in the maze
     * @return how many rows are in the maze
     */
    public int getNumRows(){
        return maze.length;
    }

    /**
     * getter for num columns that in the maze
     * @return how many columns are in the maze
     */
    public int getNumColumns(){
        return maze[0].length;
    }

    /**
     * print the maze
     * the start position will be represented by "S" string
     * the goal position will be represented by "E" string
     */
    public void print(){
        Position pos=new Position(0,0);
        for (int i=0;i<maze.length;i++) {
            for (int j = 0; j < maze.length; j++) {
                pos.setPosition(i,j);
                if (pos.equals(this.startPositon))
                    System.out.print("S"+" ");
                else if (pos.equals(this.goalPosition))
                    System.out.print("E"+" ");
                else
                    System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

    }
}

