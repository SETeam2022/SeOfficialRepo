package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 * This class provides a series of methods to make some operations on a Text.
 * 
 */
public class TextEditor extends ShapeEditor {

    public TextEditor() {
    }

    /**
     * This method allows to set the width of a Text.
     * 
     * @param shape
     * @param width 
     */
    @Override
    public void setWidth(Shape shape, double width) {
        ((Text) shape).setWrappingWidth(width);
    }

    /**
     * This method allows to set the height of a Text.
     * 
     * @param shape
     * @param height 
     */
    @Override
    public void setHeight(Shape shape, double height) {
    }

    /**
     * This method allows to retrieve the width of a Text.
     * 
     * @param shape
     * @return 
     */
    @Override
    public double getWidth(Shape shape) {
        return ((Text) shape).getWrappingWidth();
    }

    /**
     * This method allows ro retrieve the height of a Text.
     * 
     * @param shape
     * @return 
     */
    @Override
    public double getHeight(Shape shape) {
        return ((Text) shape).getLayoutBounds().getHeight();
    }

    /**
     * This method allows to clone a Text.
     * 
     * @param shape
     * @return 
     */
    @Override
    public Shape clone(Shape shape) {
        Text original = (Text) shape;
        Text clone = (Text) super.clone(original);
        clone.setX(original.getX());
        clone.setY(original.getY());
        clone.setText(original.getText());
        clone.setWrappingWidth(original.getWrappingWidth());
        return clone;
    }

    /**
     * This method allows to save a Text.
     * 
     * @param shape
     * @param stream
     * @throws IOException 
     */
    @Override
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException {
        super.saveShape(shape, stream);
        Text text = (Text) shape;
        stream.writeDouble(text.getX());
        stream.writeDouble(text.getY());
        stream.writeUTF(text.getText());
        stream.writeDouble(text.getWrappingWidth());
    }

    /**
     * This method allows to load a Text.
     * 
     * @param c
     * @param stream
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    @Override
    public Shape loadShape(Class c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Text text = (Text) super.loadShape(c, stream);
        text.setX(stream.readDouble());
        text.setY(stream.readDouble());
        text.setText(stream.readUTF());
        text.setWrappingWidth(stream.readDouble());
        return text;
    }

}
