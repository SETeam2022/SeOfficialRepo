package editor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Shape;

public abstract class ShapeEditor {

    public abstract void setWidth(Shape shape, double width);

    public abstract void setHeight(Shape shape, double height);

    public abstract double getWidth(Shape shape);

    public abstract double getHeight(Shape shape);

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
