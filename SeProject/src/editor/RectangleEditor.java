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
        ((Rectangle) shape).setWidth(width);
    }

    @Override
    public void setHeight(Shape shape, double height) {
        ((Rectangle) shape).setHeight(height);
    }

    @Override
    public void setX(Shape shape, double newX) {
        ((Rectangle) shape).setX(newX);
    }

    @Override
    public void setY(Shape shape, double newY) {
        ((Rectangle) shape).setY(newY);
    }

    @Override
    public double getWidth(Shape shape) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getHeight(Shape shape) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
