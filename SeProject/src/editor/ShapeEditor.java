package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class ShapeEditor {

    public abstract void setWidth(Shape shape, double width);

    public abstract void setHeight(Shape shape, double height);

    public abstract double getWidth(Shape shape);

    public abstract double getHeight(Shape shape);
    
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException{
        stream.writeObject(shape.getClass());
        stream.writeDouble(((Color)shape.getFill()).getRed());
        stream.writeDouble(((Color)shape.getFill()).getGreen());
        stream.writeDouble(((Color)shape.getFill()).getBlue());
        stream.writeDouble(((Color)shape.getFill()).getOpacity());
        stream.writeDouble(((Color)shape.getStroke()).getRed());
        stream.writeDouble(((Color)shape.getStroke()).getGreen());
        stream.writeDouble(((Color)shape.getStroke()).getBlue());
        stream.writeDouble(((Color)shape.getStroke()).getOpacity());
        stream.writeDouble(shape.getStrokeWidth());
        stream.writeDouble(shape.getTranslateX());
        stream.writeDouble(shape.getTranslateY());
        stream.writeDouble(shape.getRotate());
        stream.writeDouble(shape.getScaleX());
        stream.writeDouble(shape.getScaleY());
    }
    
    public Shape loadShape(Class c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Shape s = (Shape) c.newInstance();
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

    public Shape clone(Shape shape) {
        try {
            Shape clone = shape.getClass().newInstance();
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
