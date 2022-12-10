package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Rectangle;

/**
 * This class provides a series of methods to make some opearations on a Rectangle.
 * 
 */
public class RectangleEditor extends ShapeEditor<Rectangle> {

    /**
     * This method allows to set the width of a rectangle.
     *
     * @param rectangle the rectangle you want to edit Width
     * @param width
     */
    @Override
    public void setWidth(Rectangle rectangle, double width) {
        rectangle.setWidth(width);
    }

    /**
     * This method allows to set the theight of a rectangle.
     *
     * @param rectangle the rectangle you want to edit Height
     * @param height
     */
    @Override
    public void setHeight(Rectangle rectangle, double height) {
        rectangle.setHeight(height);
    }

    /**
     * This method allows to retrieve the width of a rectangle.
     * 
     * @param rectangle the rectangle you want to get Width
     * @return the width
     */
    @Override
    public double getWidth(Rectangle rectangle) {
        return rectangle.getWidth();
    }

    /**
     * This method allows to retrieve the height of a rectangle.
     *
     * @param rectangle the rectangle you want to get Height
     * @return
     */
    @Override
    public double getHeight(Rectangle rectangle) {
        return rectangle.getHeight();
    }

    /**
     * This method allows to clone a rectangle.
     *
     * @param rectangle the rectangle you want to clone
     * @return the cloned rectangle
     */
    @Override
    public Rectangle clone(Rectangle rectangle) {
        Rectangle clone = super.clone(rectangle);
        clone.setHeight(rectangle.getHeight());
        clone.setWidth(rectangle.getWidth());
        return clone;
    }
    
    /**
     * This method allows to save a rectangle.
     * 
     * @param rectangle
     * @param stream
     * @throws IOException 
     */
    @Override
    public void saveShape(Rectangle rectangle, ObjectOutputStream stream) throws IOException {
        super.saveShape(rectangle, stream);
        stream.writeDouble(rectangle.getX());
        stream.writeDouble(rectangle.getY());
        stream.writeDouble(rectangle.getWidth());
        stream.writeDouble(rectangle.getHeight());  
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
    public Rectangle loadShape(Class<Rectangle> c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Rectangle rect = super.loadShape(c, stream);
        rect.setX(stream.readDouble());
        rect.setY(stream.readDouble());
        rect.setWidth(stream.readDouble());
        rect.setHeight(stream.readDouble());
        return rect;
    }

}
