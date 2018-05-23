package algorithms.search;

import java.util.Objects;

public abstract  class AState implements Comparable {

    private AState parent;
    private int cost;
    private static final int regularCost = 3;
    private static final int diagonalCost = 4;

    /**
     * constructor with a boolean flag
     * @param isDiagonal - is the step to this position was made in diagonal or not?
     */
    public AState(boolean isDiagonal) {
        if(isDiagonal)
            this.cost = diagonalCost;
        else
            this.cost = regularCost;
    }

    /**
     * empty constructor
     */
    public AState() {    }

    /**
     * getter for the position of the state
     * @return the position
     */
    public abstract Object getPos();

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
        return Objects.equals(getPos(), state.getPos());
    }

    @Override
    public int hashCode() {
        return getPos().hashCode();
    }

    @Override
    public int compareTo(Object o) {
        AState other = ((AState)o);
        if(cost < other.cost)
            return 1;
        else if (cost == other.cost)
            return 0;
        else
            return -1;
    }

    @Override
    public String toString() {
        return getPos().toString();
    }
}
