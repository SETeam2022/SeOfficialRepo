package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * This class provides a series of methods to make some opearations on a Rectangle.
 * 
 */
public class RectangleEditor extends ShapeEditor {

    /**
     * This method allows to set the width of a rectangle.
     *
     * @param shape the rectangle you want to edit Width
     * @param width
     */
    @Override
    public void setWidth(Shape shape, double width) {
        ((Rectangle) shape).setWidth(width);
    }

    /**
     * This method allows to set the theight of a rectangle.
     *
     * @param shape the rectangle you want to edit Height
     * @param height
     */
    @Override
    public void setHeight(Shape shape, double height) {
        ((Rectangle) shape).setHeight(height);
    }

    /**
     * This method allows to retrieve the width of a rectangle.
     * 
     * @param shape the rectangle you want to get Width
     * @return the width
     */
    @Override
    public double getWidth(Shape shape) {
        return ((Rectangle) shape).getWidth();
    }

    /**
     * This method allows to retrieve the height of a rectangle.
     *
     * @param shape the rectangle you want to get Height
     * @return
     */
    @Override
    public double getHeight(Shape shape) {
        return ((Rectangle) shape).getHeight();
    }

    /**
     * This method allows to clone a rectangle.
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
    
    /**
     * This method allows to save a rectangle.
     * 
     * @param shape
     * @param stream
     * @throws IOException 
     */
    @Override
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException {
        super.saveShape(shape, stream);
        Rectangle rect = (Rectangle) shape;
        stream.writeDouble(rect.getX());
        stream.writeDouble(rect.getY());
        stream.writeDouble(rect.getWidth());
        stream.writeDouble(rect.getHeight());
        
    }

    /**
     * This method allows to load a rectangle.
     * 
     * @param c
     * @param stream
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    @Override
    public Shape loadShape(Class c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Rectangle rect = (Rectangle) super.loadShape(c, stream);
        rect.setX(stream.readDouble());
        rect.setY(stream.readDouble());
        rect.setWidth(stream.readDouble());
        rect.setHeight(stream.readDouble());
        return rect;
    }

}
