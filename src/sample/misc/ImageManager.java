package sample.misc;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class ImageManager {
    private static Dictionary<String, Dictionary<Integer, PivotImage>> dictionary = new Hashtable<>();
    public static PivotImage getPivotImage(String name, int flag) {
        return dictionary.get(name).get(flag);
    }
    private static PixelReader reader;
    public static int SIZE = 8;
    private static Point DIMENSION = new ImmutablePoint(SIZE, SIZE);
    static {
        try {
            reader = new Image(new FileInputStream("img/spritesheet.png")).getPixelReader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Dictionary<Integer, PivotImage> info;
        dictionary.put("head", info = new Hashtable<>());
        info.put(0b0001, createPivotImage(0, 0));
        info.put(0b0010, createPivotImage(3, 3));
        info.put(0b0100, createPivotImage(0, 1));
        info.put(0b1000, createPivotImage(2, 3));

        dictionary.put("body", info = new Hashtable<>());
        info.put(0b0011, createPivotImage(3, 1));
        info.put(0b0101, createPivotImage(1, 2));
        info.put(0b1001, createPivotImage(2, 1));
        info.put(0b0110, createPivotImage(3, 2));
        info.put(0b1010, createPivotImage(2, 0));
        info.put(0b1100, createPivotImage(2, 2));

        dictionary.put("tail", info = new Hashtable<>());
        info.put(0b0100, createPivotImage(1, 3));
        info.put(0b1000, createPivotImage(1, 0));
        info.put(0b0001, createPivotImage(1, 1));
        info.put(0b0010, createPivotImage(3, 0));

        dictionary.put("floor", info = new Hashtable<>());
        info.put(0b0110, createPivotImage(4, 0));
        info.put(0b0111, createPivotImage(5, 0));
        info.put(0b0011, createPivotImage(6, 0));
        info.put(0b1110, createPivotImage(4, 1));
        info.put(0b1111, createPivotImage(5, 1));
        info.put(0b1011, createPivotImage(6, 1));
        info.put(0b1100, createPivotImage(4, 2));
        info.put(0b1101, createPivotImage(5, 2));
        info.put(0b1001, createPivotImage(6, 2));

        dictionary.put("inventory", info = new Hashtable<>());
        info.put(0b0000, createPivotImage(0, 3, 0, -5));

        dictionary.put("shelf", info = new Hashtable<>());
        info.put(0b1000, createPivotImage(3, 5, 0, -7));
        info.put(0b1010, createPivotImage(1, 5, 0, -7));
        info.put(0b1110, createPivotImage(2, 5, 0, -7));
        info.put(0b0010, createPivotImage(0, 5, 0, -7));

        dictionary.put("trail-1", info = new Hashtable<>());
        info.put(0b0100, createPivotImage(0, 6));
        info.put(0b1000, createPivotImage(1, 6));
        info.put(0b0001, createPivotImage(2, 6));
        info.put(0b0010, createPivotImage(3, 6));
        info.put(0b1100, createPivotImage(4, 4));
        info.put(0b0101, createPivotImage(4, 3));
        info.put(0b0110, createPivotImage(7, 4));
        info.put(0b1001, createPivotImage(5, 4));
        info.put(0b1010, createPivotImage(5, 3));
        info.put(0b0011, createPivotImage(6, 4));

        dictionary.put("trail-2", info = new Hashtable<>());
        info.put(0b0100, createPivotImage(0, 7));
        info.put(0b1000, createPivotImage(1, 7));
        info.put(0b0001, createPivotImage(2, 7));
        info.put(0b0010, createPivotImage(3, 7));
        info.put(0b1100, createPivotImage(4, 6));
        info.put(0b0101, createPivotImage(4, 5));
        info.put(0b0110, createPivotImage(7, 6));
        info.put(0b1001, createPivotImage(5, 6));
        info.put(0b1010, createPivotImage(5, 5));
        info.put(0b0011, createPivotImage(6, 6));

        dictionary.put("checked", info = new Hashtable<>());
        info.put(0b0000, createPivotImage(7, 1));

        // info.put()
    }
    private static PivotImage createPivotImage(int column, int row) {
        return createPivotImage(column, row, 0, 0);
    }
    private static PivotImage createPivotImage(int column, int row, int offsetX, int offsetY) {
        return new PivotImage(
                reader,
                Point.add(Point.mul(new Point(column, row), SIZE), new Point(offsetX, offsetY)),
                new Point(SIZE - offsetX, SIZE - offsetY));
    }
}
