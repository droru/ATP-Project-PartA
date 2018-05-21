package algorithms.mazeGenerators;

public class Maze {
    private Position startPositon;
    private Position goalPosition;
    protected int [][] maze;
    static final int metaData = 4*3;

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
        if(maze != null) {
            this.maze = maze.getMaze();
            this.startPositon = maze.getStartPosition();
            this.goalPosition = maze.getGoalPosition();
        }
    }

    /**
     * constructor from a given num rows and num columns
     * @param row - num rows for the maze
     * @param col - num columns for the maze
     */
    public Maze(int row, int col) {
        if(row > 1 && col > 1) {
            this.maze = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    this.maze[i][j] = -1;
                }
            }
        }
    }

    /**
     * constructor from a given byte array
     * @param bytes - byte array with the content:
     * index 0 - width of the maze (columns)
     * index 1 - height of the maze (rows)
     * index 2 - start index
     * index 3 - goal index
     * index from 4 to the end - the content of the maze (row by row)
     */
    public Maze(byte[] bytes){
        int width = bytes[0]<<24 | bytes[1]<<16 | bytes[2]<<8 | bytes[3];
        int height = (bytes.length-metaData)/width;
        int startInd = bytes[4]<<24 | bytes[5]<<16 | bytes[6]<<8 | bytes[7];
        int goalInd = bytes[8]<<24 | bytes[9]<<16 | bytes[10]<<8 | bytes[11];
        int[][] maze = new int[height][width];
        int i = metaData;
        for (int row=0; row<height; row++){
            for (int col=0; col<width; col++){
                maze[row][col] = bytes[i];
                if(i == startInd)
                    setStartPositon(new Position(row, col));
                else
                    if(i == goalInd)
                        setGoalPosition(new Position(row, col));
                i++;
            }
        }
        this.maze = maze;

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
        if(isValidPosition(pos) && (val == 0 || val == 1))
            this.maze[pos.getRow()][pos.getColumn()]=val;
    }

    /**
     * getter for a value inside a maze
     * @param pos - a position on the maze
     * @return the value of the given position
     */
    public int getPosition(Position pos){
        if (isValidPosition(pos))
            return this.maze[pos.getRow()][pos.getColumn()];
        return -1;
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
     * check if the given position is not null and is inside the maze
     * @param position - a position to check its validation
     * @return true if the position valid, and false if not
     */
    private boolean isValidPosition(Position position){
        return position!=null &&
                position.getRow() >=0 &&
                position.getColumn() >= 0 &&
                position.getRow() < maze.length &&
                position.getColumn() < maze[0].length;
    }

    /**
     * convert this maze to a byte array in 1 dimensions
     * @return byte array with the content:
     * index 0-3 - width of the maze
     * index 4-7 - start index
     * index 8-11 - goal index
     * index from 12 to the end - the content of the maze (row by row)
     */
    public byte[] toByteArray(){
        byte[] result = new byte[metaData+getNumColumns()*getNumRows()];
        //result[0]-result[3] width of the maze
        int width = getNumColumns();
        result[0] = (byte) ((width>>24) & 0xFF);
        result[1] = (byte) ((width>>16) & 0xFF);
        result[2] = (byte) ((width>>8) & 0xFF);
        result[3] = (byte) ((width>>0) & 0xFF);

        //result[4] - starting the content of the maze
        int index = metaData;
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                result[index] = (byte) maze[i][j];
                Position pos = new Position(i, j);
                if(pos.equals(startPositon)){
                    //result[4]-result[7] start index
                    result[4] = (byte) ((index>>24) & 0xFF);
                    result[5] = (byte) ((index>>16) & 0xFF);
                    result[6] = (byte) ((index>>8) & 0xFF);
                    result[7] = (byte) ((index>>0) & 0xFF);
                }
                else if(pos.equals(goalPosition)){
                    //result[8]-result[11] goal index
                    result[8] = (byte) ((index>>24) & 0xFF);
                    result[9] = (byte) ((index>>16) & 0xFF);
                    result[10] = (byte) ((index>>8) & 0xFF);
                    result[11] = (byte) ((index>>0) & 0xFF);
                }
                index++;
            }
        }
        return result;
    }


    /**
     * print the maze
     * the start position will be represented by "S" string
     * the goal position will be represented by "E" string
     */
    public void print(){
        Position pos=new Position(0,0);
        for (int i=0;i<maze.length;i++) {
            for (int j = 0; j < maze[0].length; j++) {
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

