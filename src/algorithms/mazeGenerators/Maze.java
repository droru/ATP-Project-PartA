package algorithms.mazeGenerators;

public class Maze {
    private Position startPositon;
    private Position goalPosition;
    protected int [][] maze;

    public Maze(int[][] maze) {
        this.maze = maze;
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
    public void print(){
        for (int i=0;i<maze.length;i++) {
            for (int j = 0; j < maze.length; j++)
                System.out.print(maze[i][j] + " ");
            System.out.println();
        }
    }
}

