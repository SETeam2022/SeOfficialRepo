package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class TextEditor extends ShapeEditor {

    public TextEditor() {
    }

    @Override
    public void setWidth(Shape shape, double width) {
        ((Text) shape).setWrappingWidth(width);
    }

    @Override
    public void setHeight(Shape shape, double height) {
    }

    @Override
    public double getWidth(Shape shape) {
        return ((Text) shape).getWrappingWidth();
    }

    @Override
    public double getHeight(Shape shape) {
        return ((Text) shape).getLayoutBounds().getHeight();
    }

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

    @Override
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException {
        super.saveShape(shape, stream);
        Text text = (Text) shape;
        stream.writeDouble(text.getX());
        stream.writeDouble(text.getY());
        stream.writeUTF(text.getText());
        stream.writeDouble(text.getWrappingWidth());
    }

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
