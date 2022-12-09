package editor;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class EllipseEditor extends ShapeEditor {

    public EllipseEditor() {
    }

    /**
     *
     * @param shape the ellipse you want to modify.
     * @param width you want to set.
     */
    @Override
    public void setWidth(Shape shape, double width) {
        ((Ellipse) shape).setRadiusX(width / 2);
    }

    /**
     *
     * @param shape the ellipse you want to modify.
     * @param height you want to set.     
     */
    @Override
    public void setHeight(Shape shape, double height) {
        ((Ellipse) shape).setRadiusY(height / 2);
    }

    /**
     *
     * @param shape the ellipse you want to get width.
     * @return the ellipse width.
     */
    @Override
    public double getWidth(Shape shape) {
        return ((Ellipse) shape).layoutBoundsProperty().get().getWidth();
    }

    /**
     *
     * @param shape the ellipse you want to get height.
     * @return the ellipse height.
     */
    @Override
    public double getHeight(Shape shape) {
        return ((Ellipse) shape).layoutBoundsProperty().get().getHeight();
    }

    /**
     *
     * @param shape the ellipse you want to clone.
     * @return the cloned ellipse (as Shape).
     */
    @Override
    public Shape clone(Shape shape) {
        Ellipse original = (Ellipse) shape;
        Ellipse clone = (Ellipse) super.clone(shape);
        
        clone.setCenterX(original.getCenterX());
        clone.setCenterY(original.getCenterY());
        
        clone.setRadiusX(original.getRadiusX());
        clone.setRadiusY(original.getRadiusY());
        
        return clone;
    }

}
