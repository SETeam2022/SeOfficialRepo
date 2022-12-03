package editor;

import javafx.scene.shape.Shape;

public interface ShapeEditor {

    public void setWidth(Shape shape, double width);

    public void setHeight(Shape shape, double height);

    public double getWidth(Shape shape);

    public double getHeight(Shape shape);
    
    public Shape clone(Shape shape);
}
