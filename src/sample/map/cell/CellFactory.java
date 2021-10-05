package sample.map.cell;

public class CellFactory {
    public static Cell getCell(String name) {
        switch (name) {
            case "empty" : return new EmptyCell();
            case "shelf" : return new ShelfCell();
            case "inventory" : return new InventoryCell();
        }
        return Cell.NULL;
    }
}
