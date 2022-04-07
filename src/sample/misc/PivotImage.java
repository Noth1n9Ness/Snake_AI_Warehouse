package sample.misc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class PivotImage implements IDrawable {
    private Point pivot, size;
    private WritableImage image;
    public PivotImage(PixelReader pixelReader, Point position, Point size) {
        image = new WritableImage(pixelReader, position.getX(), position.getY(), size.getX(), size.getY());
        this.pivot = new Point(0,  - size.getY());
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin, int scale) {
        graphics.drawImage(
                image,
                origin.getX() + pivot.getX() * scale,
                origin.getY() + pivot.getY() * scale,
                size.getX() * scale,
                size.getY() * scale);
    }
}
