package sample;
public class Map {

    final int ROW_COUNT, COLUMN_COUNT;
    private Cell[][] cells;
    private Cell food;

    public Map(int rowCount, int columnCount)
    {
        ROW_COUNT = rowCount;
        COLUMN_COUNT = columnCount;

        cells = new Cell[ROW_COUNT][COLUMN_COUNT];
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_COUNT; column++) {
                cells[row][column] = new Cell(row, column);
            }
        }
    }
    public void initMap(int[][] map) {
        for (int row = 0; row < map.length; row++) {
            for(int col = 0; col < map[0].length; col++) {
                switch (map[row][col]) {
                    case 0:
                        cells[row][col].setCellType(CellType.EMPTY);
                        break;
                    case 1:
                        cells[row][col].setCellType(CellType.SNAKE_NODE);
                        break;
                    case 2:
                        cells[row][col].setCellType(CellType.FOOD);
                        break;
                    case 3:
                        cells[row][col].setCellType(CellType.SHELF);
                        break;
                    case 4:
                        cells[row][col].setCellType(CellType.PICKER);
                        break;
                    case 5:
                        cells[row][col].setCellType(CellType.ITEM);
                        break;
                }
            }
        }
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public boolean checkValidCell(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        if (0 > row || row>=ROW_COUNT) {
            return false;
        }
        if (0 > col || col>= COLUMN_COUNT) {
            return false;
        }
        return true;
    }

    public void setCells(Cell[][] cells)
    {
        this.cells = cells;
    }

    public Cell getFood()
    {
        return this.food;
    }

    public void generateFood()
    {
        int row=0,column=0;
        while(true){
            row = (int)(Math.random() * ROW_COUNT);
            column = (int)(Math.random() * COLUMN_COUNT);
            if(cells[row][column].getCellType()!=CellType.SNAKE_NODE)
                break;
        }
        cells[row][column].setCellType(CellType.FOOD);
        food = cells[row][column].copyCell();
        System.out.println("Food is generated at: " + row + " " + column);
    }
}