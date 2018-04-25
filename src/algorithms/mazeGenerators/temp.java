/*//private Maze maze;
    boolean[][] visited;
            int [][]maze;
@Override
public Maze generate(int numRows, int numColumns) {
        maze = new int[numRows][numColumns] ;
        for (int x=0;x<numRows;x++)
        for (int y=0;y<numColumns;y++)
        maze[x][y]=1;
        Stack<Position> stack = new Stack<>();
        visited = new boolean[numRows][numColumns];

        //start point
        Random rand = new Random();
        Position start = new Position(rand.nextInt(numRows), 0);

        stack.push(start);
        Position current=null;
        visited[start.getRow()][start.getColumn()] = true;
        maze[start.getRow()][start.getColumn()]=0;
        while (current==null||!current.equals(start)){
        if (current==null)
        current =start;
        ArrayList<Position> neighbors = getNeighbors(current);
        if(!neighbors.isEmpty()){
        Position pos=neighbors.get(rand.nextInt(neighbors.size()));
        stack.push(pos);
        maze[pos.getRow()][pos.getColumn()]=0;
        current = pos;
        visited[current.getRow()][current.getColumn()] = true;
        }
        else if(!stack.isEmpty()) {
        Position pos = stack.pop();
        current = pos;
        }
        }
        Maze result=new Maze(maze);
        result.setStartPositon(start);
        result.setGoalPosition(new Position(0, 0));
        return result;
        }

private ArrayList<Position> getNeighbors(Position position) {
        ArrayList<Position> neighbors = new ArrayList<>();
        int row = position.getRow();
        int col = position.getColumn();
        Position up = new Position(row-2, col);
        Position right = new Position(row, col+2);
        Position down = new Position(row+2, col);
        Position left = new Position(row, col-2);
        if(isValidPosition(up)) {
        up.setRow(row - 1);
        if (maze[row-1][col]==1)
        neighbors.add(up);
        }
        if(isValidPosition(right)) {
        right.setColumn(col+1);
        if (maze[row][col+1]==1)
        neighbors.add(right);
        }
        if(isValidPosition(down)) {
        down.setRow(row+1);
        if (maze[row+1][col]==1)
        neighbors.add(down);
        }
        if(isValidPosition(left)) {
        left.setColumn(col-1);
        if (maze[row][col-1]==1)
        neighbors.add(left);
        }
        return neighbors;
        }

private boolean isValidPosition(Position position) {
        int row = position.getRow();
        int col = position.getColumn();
        if(row<0 || row>=maze.length)
        return false;
        if(col<0 || col>=maze[0].length)
        return false;
        if (visited[row][col]==true)
        return false;

        return true;
        }
        }
*/