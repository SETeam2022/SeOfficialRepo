package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.text.Text;

/**
 * This class provides a series of methods to make some operations on a Text.
 *
 */
public class TextEditor extends ShapeEditor<Text> {

    public TextEditor() {
    }

    /**
     * This method allows to set the width of a Text.
     *
     * @param shape
     * @param width
     */
    @Override
    public void setWidth(Text shape, double width) {
        shape.setWrappingWidth(width);
    }

    /**
     * This method allows to set the height of a Text.
     *
     * @param shape
     * @param height
     */
    @Override
    public void setHeight(Text shape, double height) {
        shape.setStyle("-fx-font-size: " + height + "px;");
    }

    /**
     * This method allows to retrieve the width of a Text.
     *
     * @param shape
     * @return
     */
    @Override
    public double getWidth(Text shape) {
        return shape.getWrappingWidth();
    }

    /**
     * This method allows ro retrieve the height of a Text.
     *
     * @param shape
     * @return
     */
    @Override
    public double getHeight(Text shape) {
        return shape.getFont().getSize();
    }

    /**
     * This method allows to clone a Text.
     *
     * @param shape
     * @return
     */
    @Override
    public Text clone(Text shape) {
        Text original = (Text) shape;
        Text clone = (Text) super.clone(original);
        clone.setX(original.getX());
        clone.setY(original.getY());
        clone.setText(original.getText());
        clone.setWrappingWidth(original.getWrappingWidth());
        clone.setStyle("-fx-font-size: " + original.getFont().getSize() + "px;");
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
    public void saveShape(Text shape, ObjectOutputStream stream) throws IOException {
        super.saveShape(shape, stream);

        stream.writeDouble(shape.getX());
        stream.writeDouble(shape.getY());
        stream.writeUTF(shape.getText());
        stream.writeDouble(shape.getFont().getSize());
        stream.writeDouble(shape.getWrappingWidth());
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
    public Text loadShape(Class c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Text text = super.loadShape(c, stream);
        text.setX(stream.readDouble());
        text.setY(stream.readDouble());
        text.setText(stream.readUTF());
        text.setStyle("-fx-font-size: " + stream.readDouble() + "px;");
        text.setWrappingWidth(stream.readDouble());
        return text;
    }

}
