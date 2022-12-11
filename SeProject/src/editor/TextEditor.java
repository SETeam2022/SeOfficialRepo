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
     * @param text
     * @param width
     */
    @Override
    public void setWidth(Text text, double width) {
        text.setWrappingWidth(width);
    }

    /**
     * This method allows to set the height of a Text.
     *
     * @param text
     * @param height
     */
    @Override
    public void setHeight(Text text, double height) {
        text.setStyle("-fx-font-size: " + height + "px;");
    }

    /**
     * This method allows to retrieve the width of a Text.
     *
     * @param text
     * @return
     */
    @Override
    public double getWidth(Text text) {
        return text.getWrappingWidth();
    }

    /**
     * This method allows ro retrieve the height of a Text.
     *
     * @param text
     * @return
     */
    @Override
    public double getHeight(Text text) {
        return text.getFont().getSize();
    }

    /**
     * This method allows to clone a Text.
     *
     * @param text
     * @return
     */
    @Override
    public Text clone(Text text) {
        Text clone = (Text) super.clone(text);
        clone.setX(text.getX());
        clone.setY(text.getY());
        clone.setText(text.getText());
        clone.setWrappingWidth(text.getWrappingWidth());
        clone.setStyle("-fx-font-size: " + text.getFont().getSize() + "px;");
        return clone;
    }

    /**
     * This method allows to save a Text.
     *
     * @param text
     * @param stream
     * @throws IOException
     */
    @Override
    public void saveShape(Text text, ObjectOutputStream stream) throws IOException {
        super.saveShape(text, stream);
        stream.writeDouble(text.getX());
        stream.writeDouble(text.getY());
        stream.writeUTF(text.getText());
        stream.writeDouble(text.getFont().getSize());
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
