package editor;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleEditor implements ShapeEditor {

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
        Rectangle clone = new Rectangle();
        clone.setHeight(original.getHeight());
        clone.setWidth(original.getWidth());
        clone.setStroke(original.getStroke());
        clone.setStrokeWidth(original.getStrokeWidth());
        clone.setFill(original.getFill());
        return clone;
    }

}
