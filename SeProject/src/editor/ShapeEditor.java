package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * This abstract class provides a series of methods to make some operations on a
 * specific shape.
 *
 * @param <T>
 */
public abstract class ShapeEditor<T extends Shape> {

    public abstract void setWidth(T shape, double width);

    public abstract void setHeight(T shape, double height);

    public abstract double getWidth(T shape);

    public abstract double getHeight(T shape);

    public void saveShape(T shape, ObjectOutputStream stream) throws IOException {
        stream.writeObject(shape.getClass());
        stream.writeDouble(((Color) shape.getFill()).getRed());
        stream.writeDouble(((Color) shape.getFill()).getGreen());
        stream.writeDouble(((Color) shape.getFill()).getBlue());
        stream.writeDouble(((Color) shape.getFill()).getOpacity());
        stream.writeDouble(((Color) shape.getStroke()).getRed());
        stream.writeDouble(((Color) shape.getStroke()).getGreen());
        stream.writeDouble(((Color) shape.getStroke()).getBlue());
        stream.writeDouble(((Color) shape.getStroke()).getOpacity());
        stream.writeDouble(shape.getStrokeWidth());
        stream.writeDouble(shape.getTranslateX());
        stream.writeDouble(shape.getTranslateY());
        stream.writeDouble(shape.getRotate());
        stream.writeDouble(shape.getScaleX());
        stream.writeDouble(shape.getScaleY());
    }

    public T loadShape(Class<T> c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        T s = c.newInstance();
        s.setFill(new Color(stream.readDouble(), stream.readDouble(), stream.readDouble(), stream.readDouble()));
        s.setStroke(new Color(stream.readDouble(), stream.readDouble(), stream.readDouble(), stream.readDouble()));
        s.setStrokeWidth(stream.readDouble());
        s.setTranslateX(stream.readDouble());
        s.setTranslateY(stream.readDouble());
        s.setRotate(stream.readDouble());
        s.setScaleX(stream.readDouble());
        s.setScaleY(stream.readDouble());
        return s;
    }

    public T clone(T shape) {
        try {
            T clone = (T) shape.getClass().newInstance();
            clone.setStroke(shape.getStroke());
            clone.setStrokeWidth(shape.getStrokeWidth());
            clone.setFill(shape.getFill());
            clone.setRotate(shape.getRotate());
            clone.setScaleX(shape.getScaleX());
            clone.setScaleY(shape.getScaleY());
            return clone;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ShapeEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
