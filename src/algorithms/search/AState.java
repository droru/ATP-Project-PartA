package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Objects;

public abstract  class AState implements Comparable {
   private Position pos;
   private AState parent;

    /**
     * constructor with a position parameter
     * @param pos - a position for the state
     */
    public AState(Position pos) {
        this.pos = pos;
    }

    /**
     * constructor with 2 int parameters
     * @param row - int that represents the row for the state
     * @param col - int that represents the column for the state
     */
    public AState(int row, int col) {
        pos=new Position(row,col);
    }

    /**
     * getter for the position of the state
     * @return the position
     */
    public Position getPos() {
        return pos;
    }

    /**
     * setter for the position of the state
     * @param pos - the position to set for the state
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * setter for the parent of the state
     * @param parent - the AState that is the parent of the current state
     */
    public void setParent(AState parent) {
        this.parent = parent;
    }

    /**
     * getter for the parent of the state
     * @return AState that represents the parent of the current state
     */
    public AState getParent() {

        return parent;
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

        return pos.hashCode();
    }

    @Override
    public String toString() {
        return pos.toString();
    }
}
