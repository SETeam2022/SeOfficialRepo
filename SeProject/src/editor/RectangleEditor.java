package editor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleEditor extends ShapeEditor {

    /**
     *
     * @param shape the rectangle you want to edit Width
     * @param width
     */
    @Override
    public void setWidth(Shape shape, double width) {
        ((Rectangle) shape).setWidth(width);
    }

    /**
     *
     * @param shape the rectangle you want to edit Height
     * @param height
     */
    @Override
    public void setHeight(Shape shape, double height) {
        ((Rectangle) shape).setHeight(height);
    }

    /**
     *
     * @param shape the rectangle you want to get Width
     * @return
     */
    @Override
    public double getWidth(Shape shape) {
        return ((Rectangle) shape).getWidth();
    }

    /**
     *
     * @param shape the rectangle you want to get Height
     * @return
     */
    @Override
    public double getHeight(Shape shape) {
        return ((Rectangle) shape).getHeight();
    }

    /**
     *
     * @param shape the rectangle you want to clone
     * @return the cloned rectangle
     */
    @Override
    public Shape clone(Shape shape) {
        Rectangle original = (Rectangle) shape;
        Rectangle clone = (Rectangle) super.clone(shape);
        clone.setHeight(original.getHeight());
        clone.setWidth(original.getWidth());
        return clone;
    }

}
