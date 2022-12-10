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
     * @param line the line you want to edit Width
     * @param width the new line width
     */
    @Override
    public void setWidth(Line line, double width) {
        int old = (int) Math.round(this.getWidth(line));
        if (old == width) {
            return;
        }

        line.setEndX(line.getStartX() + width);
    }

    /**
     * This method allows to change the height of a line.
     *
     * @param line the line you want to edit Height
     * @param height
     */
    @Override
    public void setHeight(Line line, double height) {
        int old = (int) Math.round(this.getHeight(line));
        if (old == height) {
            return;
        }

        if (line.getEndY() < line.getStartY()) {
            line.setEndY(line.getStartY() - height);
        } else {
            line.setEndY(line.getStartY() + height);
        }
    }

    /**
     * This method allows to retrieve the width of a line.
     *
     * @param line the line you want to get Width
     * @return the width of the lines
     */
    @Override
    public double getWidth(Line line) {
        return Math.abs(line.getEndX() - line.getStartX());
    }

    /**
     * This method allows to retrieve the height of a line.
     *
     * @param line the line you want to get Height
     * @return the height of the line
     */
    @Override
    public double getHeight(Line line) {
        return Math.abs(line.getEndY() - line.getStartY());
    }

    /**
     * This method allows to clone a line.
     *
     * @param line the line you want to clone.
     * @return the cloned line.
     */
    @Override
    public Line clone(Line line) {
        Line clone = super.clone(line);

        clone.setStartX(line.getStartX());
        clone.setStartY(line.getStartY());

        clone.setEndX(line.getEndX());
        clone.setEndY(line.getEndY());
        return clone;
    }

    /**
     * This method allows to save a line.
     *
     * @param line
     * @param stream
     * @throws IOException
     */
    @Override
    public void saveShape(Line line, ObjectOutputStream stream) throws IOException {
        super.saveShape(line, stream);
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
    public Line loadShape(Class<Line> c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Line line = super.loadShape(c, stream);
        line.setStartX(stream.readDouble());
        line.setStartY(stream.readDouble());
        line.setEndX(stream.readDouble());
        line.setEndY(stream.readDouble());
        return line;
    }

}
