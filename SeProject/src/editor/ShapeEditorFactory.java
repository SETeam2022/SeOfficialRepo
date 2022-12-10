package editor;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class ShapeEditorFactory {

    /**
     * Instanced when the program stars. That choice is for obtaining better performances,
     * since the map is not instanced every single time, but only once.
     */
    private static final Map<Class< ? extends Shape>, ShapeEditor> editors = new HashMap<>();

    /**
     * Populed when the program stars. That choice is for obtaining better performances,
     * since the map is not populed every single time, but only once.
     */
    static {
            editors.put(Rectangle.class, new RectangleEditor());
            editors.put(Ellipse.class, new EllipseEditor());
            editors.put(Line.class, new LineEditor());
            editors.put(Text.class, new TextEditor());
            editors.put(Polyline.class, new PolygonEditor());
    }

    /**
     *
     * @param shapeClass is the shape's class to be edited.
     *
     * @return the ShapeEditor for a specific shape. Indeed, it is easy to see that
     * you can use it for shape editing.
     */
    public static ShapeEditor getInstance(Class<? extends Shape> shapeClass) throws EditorNotFoundException{
        ShapeEditor editor = editors.get(shapeClass);
        if (editor == null) throw new EditorNotFoundException("Doesn't exist an editor for the class that you searched");
        return editor;
    }
    
    public static void addInstance(Class<? extends Shape> shapeClass, ShapeEditor editor){
          editors.put(shapeClass,editor);
    }
}
