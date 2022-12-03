package editor;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class EllipseEditor implements ShapeEditor {

    public EllipseEditor() {
    }

    @Override
    public void setWidth(Shape shape, double width) {
        ((Ellipse) shape).setRadiusX(width / 2);
    }

    @Override
    public void setHeight(Shape shape, double height) {
        ((Ellipse) shape).setRadiusY(height / 2);
    }

    @Override
    public double getWidth(Shape shape) {
        return ((Ellipse) shape).layoutBoundsProperty().get().getWidth();
    }

    @Override
    public double getHeight(Shape shape) {
        return ((Ellipse) shape).layoutBoundsProperty().get().getHeight();
    }

}
