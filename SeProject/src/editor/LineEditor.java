package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 * This class provieds a series of methods to make some operations on a line.
 * 
 */
public class LineEditor extends ShapeEditor {

    /**
     * This method allows to change the width of a line.
     *
     * @param shape the line you want to edit Width
     * @param width the new line width
     */
    @Override
    public void setWidth(Shape shape, double width) {
        int old = (int) Math.round(this.getWidth(shape));
        if (old == width) {
            return;
        }

        ((Line) shape).setEndX(((Line) shape).getStartX() + width);
    }

    /**
     * This method allows to change the height of a line.
     *
     * @param shape the line you want to edit Height
     * @param height
     */
    @Override
    public void setHeight(Shape shape, double height) {
        int old = (int) Math.round(this.getHeight(shape));
        if (old == height) {
            return;
        }

        if (((Line) shape).getEndY() < ((Line) shape).getStartY()) {
            ((Line) shape).setEndY(((Line) shape).getStartY() - height);
        } else {
            ((Line) shape).setEndY(((Line) shape).getStartY() + height);
        }
    }

    /**
     * This method allows to retrieve the width of a line.
     *
     * @param shape the line you want to get Width
     * @return the width of the lines
     */
    @Override
    public double getWidth(Shape shape) {
        return Math.abs(((Line) shape).getEndX() - ((Line) shape).getStartX());
    }

    /**
     * This method allows to retrieve the height of a line.
     *
     * @param shape the line you want to get Height
     * @return the height of the line
     */
    @Override
    public double getHeight(Shape shape) {
        return Math.abs(((Line) shape).getEndY() - ((Line) shape).getStartY());
    }
    
    /**
     * This method allows to clone a line.
     * 
     * @param shape the line you want to clone.
     * @return the cloned line.
     */
    @Override
    public Shape clone(Shape shape) {
        Line original = (Line) shape;
        Line clone = (Line) super.clone(shape);

        clone.setStartX(original.getStartX());
        clone.setStartY(original.getStartY());

        clone.setEndX(original.getEndX());
        clone.setEndY(original.getEndY());
        return clone;
    }

    /**
     * This method allows to save a line.
     * 
     * @param shape
     * @param stream
     * @throws IOException 
     */
    @Override
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException {
        super.saveShape(shape, stream);
        Line line = (Line) shape;
        stream.writeDouble(line.getStartX());
        stream.writeDouble(line.getStartY());
        stream.writeDouble(line.getEndX());
        stream.writeDouble(line.getEndY());
    }

    /**
     * This method allows to load a line.
     * 
     * @param c
     * @param stream
     * @return the loaded line
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    @Override
    public Shape loadShape(Class c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Line line = (Line) super.loadShape(c, stream);
        line.setStartX(stream.readDouble());
        line.setStartY(stream.readDouble());
        line.setEndX(stream.readDouble());
        line.setEndY(stream.readDouble());
        return line;
    }

}
