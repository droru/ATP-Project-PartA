package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Objects;

public abstract  class AState implements Comparable {
   private Position pos;
   private AState parent;
   private int distance = -1;

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

    public void setParent(AState parent) {
        this.parent = parent;
    }

    public AState getParent() {

        return parent;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {

        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState state = (AState) o;
        return Objects.equals(pos, state.pos);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pos, parent);
    }

    @Override
    public String toString() {
        return pos.toString();
    }
}
