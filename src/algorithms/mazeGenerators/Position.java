package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
    private int row;
    private int column;

    /**
     * getter for row
     * @return int that represents the row of the position
     */
    public int getRow() {
        return row;
    }

    /**
     * getter for the column
     * @return int that represents the column of the position
     */
    public int getColumn() {
        return column;
    }

    /**
     * setter for the row
     * @param row - int value to set for the row position
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * setter for the position coordinates
     * @param row - int value to set the row
     * @param col - int value to set the column
     */
    public void setPosition(int row,int col)
    {
        this.row=row;
        this.column=col;
    }

    /**
     * setter for the column
     * @param column - int value to set for the column position
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * constructor for position with 2 int parameters
     * @param row - int value for the row
     * @param column - int value for the column
     */
     public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Row: "+this.row+" Colum: "+this.column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==null || obj == null)
            return  false ;
       Position pos=(Position)obj;
       return (pos.getColumn()==this.getColumn())&&(pos.getRow()==this.getRow());
    }

    @Override
    public int hashCode() {

        return Objects.hash(row, column);
    }
}
