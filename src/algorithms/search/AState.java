package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract  class AState {
   private Position pos;

    public AState(Position pos) {
        this.pos = pos;
    }

    public AState(int row, int col) {
        pos=new Position(row,col);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
