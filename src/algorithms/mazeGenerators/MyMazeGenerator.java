package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
    private int width;
    private int hight;
    private Random random;
    int[][] maze;
    private LinkedList<int[]> frontiers = new LinkedList<>();

    @Override
    public Maze generate(int numRows, int numColumns) {
        random = new Random();
        width = numColumns;
        hight = numRows;
        maze = new int[hight][width];
        initinalize();

        int y = random.nextInt(width-1);
        int x = random.nextInt(hight-1);
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
                if (x < hight - 2 && maze[x + 2][y] == 1)
                    frontiers.add(new int[]{x + 1, y, x + 2, y});
                if (y < width - 2 && maze[x][y + 2] == 1)
                    frontiers.add(new int[]{x, y + 1, x, y + 2});
            }
        }
        Maze result=MakeMaze(new Position(x,0));
        return result;

    }

    private Maze MakeMaze(Position start) {
        Maze res = new Maze(maze);
        res.setStartPositon(start);
        ArrayList<Position> exit = new ArrayList<>();
        for (int i = 0; i < hight; i++)
            if (maze[i][width - 1] == 0)
                exit.add(new Position(i, width - 1));
        if (!exit.isEmpty()) {
            System.out.println(exit.size());
            res.setGoalPosition(exit.remove(random.nextInt(exit.size() - 1)));
        }
        else {
            boolean done = false;
            Position end = null;
            while (!done) {
                end = new Position(random.nextInt(hight-1), width - 1);
                if (maze[end.getRow()][end.getColumn() - 1] == 0)
                {
                    done = true;
                    res.setPosition(end,0);
                    res.setGoalPosition(end);
                }
            }

        }
        return res;

    }

    /**
     * initialize all the cells in the maze  as wall (value 1)
     */
    private void initinalize(){

        for (int i=0;i<hight;i++)
            for (int j=0;j<width;j++)
                maze[i][j]=1;
    }
}
