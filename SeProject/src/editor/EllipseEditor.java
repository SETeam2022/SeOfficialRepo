package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * This class provides a series of methods to make some operations on an
 * Ellipse.
 *
 */
public class EllipseEditor extends ShapeEditor<Ellipse> {

    public EllipseEditor() {
    }

    /**
     * This method allows to change the width of an ellipse.
     *
     * @param shape the ellipse you want to modify.
     * @param width you want to set.
     */
    @Override
    public void setWidth(Ellipse shape, double width) {
        ((Ellipse) shape).setRadiusX(width / 2);
    }

    /**
     * This method allows to change the height of an ellipse.
     *
     * @param shape the ellipse you want to modify.
     * @param height you want to set.
     */
    @Override
    public void setHeight(Ellipse shape, double height) {
        ((Ellipse) shape).setRadiusY(height / 2);
    }

    /**
     * This method allows to retrieve the width of an ellipse.
     *
     * @param shape the ellipse you want to get width.
     * @return the ellipse width.
     */
    @Override
    public double getWidth(Ellipse shape) {
        return shape.layoutBoundsProperty().get().getWidth();
    }

    /**
     * This method allows to retrieve the height of an ellipse.
     *
     * @param shape the ellipse you want to get height.
     * @return the ellipse height.
     */
    @Override
    public double getHeight(Ellipse shape) {
        return shape.layoutBoundsProperty().get().getHeight();
    }

    /**
     * This method allows to clone an ellipse.
     *
     * @param shape the ellipse you want to clone.
     * @return the cloned ellipse (as Shape).
     */
    @Override
    public Ellipse clone(Ellipse original) {
        Ellipse clone = super.clone(original);
        clone.setCenterX(original.getCenterX());
        clone.setCenterY(original.getCenterY());
        clone.setRadiusX(original.getRadiusX());
        clone.setRadiusY(original.getRadiusY());
        return clone;
    }

    /**
     * This method allows to save an ellipse.
     *
     * @param shape
     * @param stream
     * @throws IOException
     */
    @Override
    public void saveShape(Ellipse shape, ObjectOutputStream stream) throws IOException {
        super.saveShape(shape, stream);
        stream.writeDouble(shape.getCenterX());
        stream.writeDouble(shape.getCenterY());
        stream.writeDouble(shape.getRadiusX());
        stream.writeDouble(shape.getRadiusY());
    }

    /**
     * This method allows to load an ellipse.
     *
     * @param c
     * @param stream
     * @return the loaded ellipse
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public Ellipse loadShape(Class<Ellipse> c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Ellipse ell = super.loadShape(c, stream);
        ell.setCenterX(stream.readDouble());
        ell.setCenterY(stream.readDouble());
        ell.setRadiusX(stream.readDouble());
        ell.setRadiusY(stream.readDouble());
        return ell;
    }

}
