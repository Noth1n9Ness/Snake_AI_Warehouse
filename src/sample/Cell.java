package sample;
// To represent a cell of display board.
public class Cell {

    private final int row, col;
    private CellType cellType;
    private Direction direction;

    public Cell(int row, int col, CellType cellType)
    {
        this.row = row;
        this.col = col;
        this.cellType = cellType;
    }

    public Cell(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    public Cell copyCell()
    {
        return new Cell(this.row,this.col, this.cellType);
    }

    public CellType getCellType()
    {
        return cellType;
    }

    public void setCellType(CellType cellType)
    {
        this.cellType = cellType;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Cell moveTo(Direction direction) {
        switch (direction) {
            case UP:
                return new Cell(this.getRow()-1, this.getCol(), CellType.EMPTY);
            case DOWN:
                return new Cell(this.getRow()+1, this.getCol(), CellType.EMPTY);
            case LEFT:
                return new Cell(this.getRow(), this.getCol()-1, CellType.EMPTY);
            case RIGHT:
                return new Cell(this.getRow(), this.getCol()+1, CellType.EMPTY);
            default:
                return new Cell(this.getRow(), this.getCol(), CellType.EMPTY);
        }
    }
}