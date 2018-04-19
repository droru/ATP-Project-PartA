package algorithms.mazeGenerators;

public class Position {
    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

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
       Position pos=(Position)obj;
       return (pos.getColumn()==this.getColumn())&&(pos.getRow()==this.getRow());
    }
}
