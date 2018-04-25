/*package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
    private int width;
    private int hight;
    private Random random;
    int[][] maze;
    private LinkedList<int[]> frontiers = new LinkedList<>();


    private void initinalize(){
        for (int i=0;i<width;i++)
            for (int j=0;j<hight;j++)
                maze[i][j]=1;
    }
    @Override

    public Maze generate(int numRows, int numColumns) {
        random = new Random();
        width = numColumns;
        hight = numRows;
        maze = new int[hight][width];
        initinalize();
        int x = random.nextInt(width);
        int y = random.nextInt(hight);
        frontiers.add(new int[]{x, y, x, y});

        while (!frontiers.isEmpty()) {
            final int[] f = frontiers.remove(random.nextInt(frontiers.size()));
            x = f[2];
            y = f[3];
            if (maze[x][y] == 1) {
                maze[f[0]][f[1]] = maze[x][y] = 0;
                if (x >= 2 && maze[x - 2][y] == 1)
                    frontiers.add(new int[]{x - 1, y, x - 2, y});
                if (y >= 2 && maze[x][y - 2] == 1)
                    frontiers.add(new int[]{x, y - 1, x, y - 2});
                if (x < width - 2 && maze[x + 2][y] == 1)
                    frontiers.add(new int[]{x + 1, y, x + 2, y});
                if (y < hight - 2 && maze[x][y + 2] == 1)
                    frontiers.add(new int[]{x, y + 1, x, y + 2});
            }
        }
        Maze result=new Maze(maze);
        result.setStartPositon(new Position(x,0));
        result.setGoalPosition(new Position(0,0));
        ArrayList<Position>exit =new ArrayList<>();
        for (int i=0;i<hight;i++)
            if(maze[i][width-1]==0)
                exit.add(new Position(i,width-1));
        if(!exit.isEmpty())
            result.setGoalPosition(exit.remove(random.nextInt(exit.size()-1)));
        else {
            boolean done = false;
            Position end = null;
            while (!done) {
                end = new Position(random.nextInt(hight), width - 1);
                if (maze[end.getRow()][end.getColumn() - 1] == 0) ;
                {
                    done = true;
                    result.setGoalPosition(end);
                }
            }
        }
        return result;

    }
}
public class temp3 {
}
*/