/*package algorithms.mazeGenerators;

import java.util.Random;

public class temp2 {
}import java.util.ArrayList;
        import java.util.Random;
        import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {
    int [][] maze ;
    int width;int hight;
    boolean [][] visited;
    Random rand=new Random();
    @Override
    public Maze generate(int numRows, int numColumns) {
        maze =new int [numRows][numColumns];
        visited=new boolean[numRows][numColumns];
        width=numColumns;
        hight=numRows;
        intilaizeMaze();
        MakeMaze(new Position(0,0));
        Maze res=new Maze(maze);
        res.setStartPositon(new Position(0,0));
        res.setGoalPosition(new Position(0,1));

        return res;
    }
    private void MakeMaze(Position pos) {
        int [][] direct={{1,0},{-1,0},{0,1},{0,-1}};
        int [] visitOrder  = {0,1,2,3};

        //out of boundary
        if(pos.getRow() < 0 || pos.getColumn() < 0 || pos.getRow() >= hight || pos.getColumn() >= width)
            return ;

        //visited, go back the the coming direction, return
        if(maze[pos.getRow()][pos.getColumn()] == 0) return ;

        //some neightbors are visited in addition to the coming direction, return
        //this is to avoid circles in maze
        if(countVisitedNeighbor(pos) > 1) return ;

        maze[pos.getRow()][pos.getColumn()] = 0; // visited

        //shuffle the visitOrder
        shuffle(visitOrder, 4);

        for (int k = 0; k < 4; ++k)
        {
            int ni = pos.getRow() + direct[visitOrder[k]][0];
            int nj = pos.getColumn() + direct[visitOrder[k]][1];
            pos.setPosition(ni,nj);
            MakeMaze(pos);
        }
    }
    int countVisitedNeighbor(Position pos){
        int [][] direct={{1,0},{-1,0},{0,1},{0,-1}};
        int count = 0;
        for (int k = 0; k < 4; ++k)
        {
            int ni = pos.getRow() + direct[k][0];
            int nj = pos.getColumn() + direct[k][1];
            //out of boundary
            if(ni < 0 || nj < 0 || ni >= hight || nj >= width) continue;
            if(maze[ni][nj] == 0) count++;//visited
        }
        return count;
    }
    void shuffle(int [] a, int n){
        for (int i = 0; i < a.length; ++i)
        {
            int x = rand.nextInt(a.length) % n;
            swap(a[i], a[x]);
        }
    }
    void swap(int a, int b){
        int c = a;
        a = b;
        b = c;
    }
    private void intilaizeMaze(){
        for (int i=0;i<hight;i++)
            for(int j=0;j<width;j++)
                maze[i][j]=1;
    }
}



*/