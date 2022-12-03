package editor;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleEditor implements ShapeEditor {

    @Override
    public void setWidth(Shape shape, double width) {
        ((Rectangle) shape).setWidth(width);
    }

    @Override
    public void setHeight(Shape shape, double height) {
        ((Rectangle) shape).setHeight(height);
    }

    @Override
    public double getWidth(Shape shape) {
        Rectangle rectangle = (Rectangle) shape;
        return rectangle.getWidth();
    }

    @Override
    public double getHeight(Shape shape) {
        Rectangle rectangle = (Rectangle) shape;
        return rectangle.getHeight();
    }

}
