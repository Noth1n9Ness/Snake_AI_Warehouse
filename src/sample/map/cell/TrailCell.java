package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.misc.ImageManager;
import sample.misc.PivotImage;
import sample.misc.Point;

import java.util.ArrayList;

public class TrailCell extends EmptyCell{
    private ArrayList<PivotImage> lowerTrails = new ArrayList<>();
    private ArrayList<PivotImage> higherTrails = new ArrayList<>();
    private boolean[] check = new boolean[0b10000];
    public TrailCell(Point point) {
        super(point.getX(), point.getY());
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin, int scale) {
        for (PivotImage lowerTrail : lowerTrails) lowerTrail.draw(graphics, Point.add(origin, Point.mul(point, scale * SCALE * ImageManager.SIZE)), scale * SCALE);
        for (PivotImage higherTrail : higherTrails) higherTrail.draw(graphics, Point.add(origin, Point.mul(point, scale * SCALE * ImageManager.SIZE)), scale * SCALE);

    }

    public void addFlag(int flag) {
        if (!check[flag]) {
            lowerTrails.add(ImageManager.getPivotImage("trail-1", flag));
            higherTrails.add(ImageManager.getPivotImage("trail-2", flag));
            check[flag] = true;
        }
    }
    public void clear() {
        lowerTrails.clear();
        higherTrails.clear();
    }
}
