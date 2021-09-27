package sample.misc;

import javafx.scene.canvas.GraphicsContext;

public interface IDrawable {
    void draw(GraphicsContext graphics, Point origin);
}
