package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Ellipse;

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
     * @param ellipse the ellipse you want to modify.
     * @param width you want to set.
     */
    @Override
    public void setWidth(Ellipse ellipse, double width) {
        ellipse.setRadiusX(width / 2);
    }

    /**
     * This method allows to change the height of an ellipse.
     *
     * @param ellipse the ellipse you want to modify.
     * @param height you want to set.
     */
    @Override
    public void setHeight(Ellipse ellipse, double height) {
        ellipse.setRadiusY(height / 2);
    }

    /**
     * This method allows to retrieve the width of an ellipse.
     *
     * @param ellipse the ellipse you want to get width.
     * @return the ellipse width.
     */
    @Override
    public double getWidth(Ellipse ellipse) {
        return ellipse.getRadiusX()*2;
    }

    /**
     * This method allows to retrieve the height of an ellipse.
     *
     * @param ellipse the ellipse you want to get height.
     * @return the ellipse height.
     */
    @Override
    public double getHeight(Ellipse ellipse) {
        return ellipse.getRadiusY()*2;
    }

    /**
     * This method allows to clone an ellipse.
     *
     * @param ellipse the ellipse you want to clone.
     * @return the cloned ellipse (as Shape).
     */
    @Override
    public Ellipse clone(Ellipse ellipse) {
        Ellipse clone = super.clone(ellipse);
        clone.setCenterX(ellipse.getCenterX());
        clone.setCenterY(ellipse.getCenterY());
        clone.setRadiusX(ellipse.getRadiusX());
        clone.setRadiusY(ellipse.getRadiusY());
        return clone;
    }

    /**
     * This method allows to save an ellipse.
     *
     * @param ellipse
     * @param stream
     * @throws IOException
     */
    @Override
    public void saveShape(Ellipse ellipse, ObjectOutputStream stream) throws IOException {
        super.saveShape(ellipse, stream);
        stream.writeDouble(ellipse.getCenterX());
        stream.writeDouble(ellipse.getCenterY());
        stream.writeDouble(ellipse.getRadiusX());
        stream.writeDouble(ellipse.getRadiusY());
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
        Ellipse ellipse = super.loadShape(c, stream);
        ellipse.setCenterX(stream.readDouble());
        ellipse.setCenterY(stream.readDouble());
        ellipse.setRadiusX(stream.readDouble());
        ellipse.setRadiusY(stream.readDouble());
        return ellipse;
    }

}
