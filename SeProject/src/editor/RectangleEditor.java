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
        return ((Rectangle) shape).getWidth();
    }

    @Override
    public double getHeight(Shape shape) {
        return ((Rectangle) shape).getHeight();
    }

    @Override
    public Shape clone(Shape shape) {
        Rectangle original = (Rectangle) shape;
        Rectangle clone = new Rectangle();
        clone.setHeight(original.getHeight());
        clone.setWidth(original.getWidth());
        clone.setStroke(original.getStroke());
        clone.setFill(original.getFill());
        return clone;
    }

}
