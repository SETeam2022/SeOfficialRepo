package editor;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author alewi
 */
public class RectangleEditor implements ShapeEditor {

    @Override
    public void setWidth(Shape shape, double width) {
        Rectangle rectangle = (Rectangle) shape;
        rectangle.setWidth(width);
    }

    @Override
    public void setHeight(Shape shape, double height) {
        Rectangle rectangle = (Rectangle) shape;
        rectangle.setHeight(height);
    }

    @Override
    public void setX(Shape shape, double newX) {
        Rectangle rectangle = (Rectangle) shape;
        rectangle.setX(newX);
    }

    @Override
    public void setY(Shape shape, double newY) {
        Rectangle rectangle = (Rectangle) shape;
        rectangle.setX(newY);
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
