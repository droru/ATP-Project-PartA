package algorithms.mazeGenerators;

public class Maze {
    private Position startPositon;
    private Position goalPosition;
    protected int [][] maze;

    public Maze(int[][] maze) {
        this.maze = maze;
    }

    public Maze(Maze maze) {
        this.maze = maze.getMaze();
        this.startPositon = maze.getStartPosition();
        this.goalPosition = maze.getGoalPosition();
    }

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

    public Position getStartPosition() {
        return startPositon;
    }

    public void setStartPositon(Position startPositon) {
        this.startPositon = startPositon;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    public int[][] getMaze() {
        return maze;
    }
    public void setPosition(Position pos,int val){
        this.maze[pos.getRow()][pos.getColumn()]=val;
    }
    public int getPosition(Position pos){
        return this.maze[pos.getRow()][pos.getColumn()];
    }
    public int getNumRows(){
        return maze.length;
    }

    public int getNumColumns(){
        return maze[0].length;
    }
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

