package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    @Override
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException {
        Rectangle rect = (Rectangle) shape;
        stream.writeDouble(rect.getX());
        stream.writeDouble(rect.getY());
        stream.writeDouble(rect.getWidth());
        stream.writeDouble(rect.getHeight());
        
    }

    @Override
    public Shape loadShape(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
