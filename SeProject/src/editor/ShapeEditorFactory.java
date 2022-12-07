package editor;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ShapeEditorFactory {

    /**
     * Instanced when the program stars. That choise is for better performance
     * because the map is not instanced every time but only once.
     */
    private static final Map<Class<?>, ShapeEditor> editors = new HashMap<>();

    /**
     * Populed when the program stars. That choise is for better performance
     * because the map is not populed every time but only once.
     */
    static {
            editors.put(Rectangle.class, new RectangleEditor());
            editors.put(Ellipse.class, new EllipseEditor());
            editors.put(Line.class, new LineEditor());
            editors.put(Text.class, new TextEditor());
            editors.put(Text.class, new PolygonEditor());
    }

    /**
     *
     * @param shapeClass is the is the shape's class you want to edit.
     *
     * @return the ShapeEditor for your shape. Indeed, it is easy to see that
     * you can use it for shape editing.
     */
    public static ShapeEditor getInstance(Class<?> shapeClass) {
        return editors.get(shapeClass);
    }
}
