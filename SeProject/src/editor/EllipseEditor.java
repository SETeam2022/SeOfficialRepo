package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
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

    @Override
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException{
        Ellipse ell = (Ellipse) shape;
        stream.writeDouble(ell.getCenterX());
        stream.writeDouble(ell.getCenterY());
        stream.writeDouble(ell.getRadiusX());
        stream.writeDouble(ell.getRadiusY());
        stream.writeObject(ell.getFill());
        stream.writeObject(ell.getStroke());
        stream.writeDouble(ell.getTranslateX());
        stream.writeDouble(ell.getTranslateY());
        stream.writeDouble(ell.getRotate());
    }

    @Override
    public Shape loadShape(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        Ellipse ell = new Ellipse();
        ell.setCenterX(stream.readDouble());
        ell.setCenterY(stream.readDouble());
        ell.setRadiusX(stream.readDouble());
        ell.setRadiusY(stream.readDouble());
        ell.setFill((Color)stream.readObject());
        ell.setStroke((Color)stream.readObject());
        ell.setTranslateX(stream.readDouble());
        ell.setTranslateY(stream.readDouble());
        ell.setRotate(stream.readDouble());
        return ell;
    }

}
