package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Line;

/**
 * This class provieds a series of methods to make some operations on a line.
 * 
 */
public class LineEditor extends ShapeEditor<Line> {

    /**
     * This method allows to change the width of a line.
     *
     * @param shape the line you want to edit Width
     * @param width the new line width
     */
    @Override
    public void setWidth(Line shape, double width) {
        int old = (int) Math.round(this.getWidth(shape));
        if (old == width) {
            return;
        }

        shape.setEndX(shape.getStartX() + width);
    }

    /**
     * This method allows to change the height of a line.
     *
     * @param shape the line you want to edit Height
     * @param height
     */
    @Override
    public void setHeight(Line shape, double height) {
        int old = (int) Math.round(this.getHeight(shape));
        if (old == height) {
            return;
        }

        if ( shape.getEndY() < shape.getStartY()) {
            shape.setEndY( shape.getStartY() - height);
        } else {
            shape.setEndY(shape.getStartY() + height);
        }
    }

    /**
     * This method allows to retrieve the width of a line.
     *
     * @param shape the line you want to get Width
     * @return the width of the lines
     */
    @Override
    public double getWidth(Line shape) {
        return Math.abs(shape.getEndX() - shape.getStartX());
    }

    /**
     * This method allows to retrieve the height of a line.
     *
     * @param shape the line you want to get Height
     * @return the height of the line
     */
    @Override
    public double getHeight(Line shape) {
        return Math.abs(shape.getEndY() - shape.getStartY());
    }
    
    /**
     * This method allows to clone a line.
     * 
     * @param original the line you want to clone.
     * @return the cloned line.
     */
    @Override
    public Line clone(Line original) {
        Line clone = super.clone(original);

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
    public void saveShape(Line shape, ObjectOutputStream stream) throws IOException {
        super.saveShape(shape, stream);
        stream.writeDouble(shape.getStartX());
        stream.writeDouble(shape.getStartY());
        stream.writeDouble(shape.getEndX());
        stream.writeDouble(shape.getEndY());
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
    public Line loadShape(Class<Line> c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Line line = super.loadShape(c, stream);
        line.setStartX(stream.readDouble());
        line.setStartY(stream.readDouble());
        line.setEndX(stream.readDouble());
        line.setEndY(stream.readDouble());
        return line;
    }

}
