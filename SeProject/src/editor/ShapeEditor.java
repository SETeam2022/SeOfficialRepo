package editor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author alewi
 */
public interface ShapeEditor {
    public void setWidth(Shape shape, double width);
    public void setHeight(Shape shape, double height);
    public void setX(Shape shape, double newX);
    public void setY(Shape shape, double newY);
    
    public default void setStrokeColor(Shape shape, Color newColor){
        shape.setStroke(newColor);
    }  
}
