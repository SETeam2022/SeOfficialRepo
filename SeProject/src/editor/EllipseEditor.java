package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * This class provides a series of methods to make some operations on an Ellipse.
 * 
 */
public class EllipseEditor extends ShapeEditor {

    public EllipseEditor() {
    }

    /**
     * This method allows to change the width of an ellipse.
     * 
     * @param shape the ellipse you want to modify.
     * @param width you want to set.
     */
    @Override
    public void setWidth(Shape shape, double width) {
        ((Ellipse) shape).setRadiusX(width / 2);
    }

    /**
     * This method allows to change the height of an ellipse.
     *
     * @param shape the ellipse you want to modify.
     * @param height you want to set.     
     */
    @Override
    public void setHeight(Shape shape, double height) {
        ((Ellipse) shape).setRadiusY(height / 2);
    }

    /**
     * This method allows to retrieve the width of an ellipse.
     *
     * @param shape the ellipse you want to get width.
     * @return the ellipse width.
     */
    @Override
    public double getWidth(Shape shape) {
        return ((Ellipse) shape).layoutBoundsProperty().get().getWidth();
    }

    /**
     * This method allows to retrieve the height of an ellipse.
     *
     * @param shape the ellipse you want to get height.
     * @return the ellipse height.
     */
    @Override
    public double getHeight(Shape shape) {
        return ((Ellipse) shape).layoutBoundsProperty().get().getHeight();
    }

    /**
     * This method allows to clone an ellipse. 
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

    /**
     * This method allows to save an ellipse.
     * 
     * @param shape
     * @param stream
     * @throws IOException 
     */
    @Override
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException{
        super.saveShape(shape, stream);
        Ellipse ell = (Ellipse) shape;
        stream.writeDouble(ell.getCenterX());
        stream.writeDouble(ell.getCenterY());
        stream.writeDouble(ell.getRadiusX());
        stream.writeDouble(ell.getRadiusY());
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
    public Shape loadShape(Class c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Ellipse ell = (Ellipse) super.loadShape(c, stream);
        ell.setCenterX(stream.readDouble());
        ell.setCenterY(stream.readDouble());
        ell.setRadiusX(stream.readDouble());
        ell.setRadiusY(stream.readDouble());
        return ell;
    }

}
