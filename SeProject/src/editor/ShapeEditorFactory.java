package editor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            editors.put(Class.forName("javafx.scene.shape.Rectangle"), new RectangleEditor());
            editors.put(Class.forName("javafx.scene.shape.Line"), new LineEditor());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShapeEditorFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param shapeClass is the is the shape's class you want to edit.
     *
     * @return the ShapeEditor for your shape. Indeed, it is easy to see that
     * you can use for shape editing.
     */
    public static ShapeEditor getInstance(Class<?> shapeClass) {
        return editors.get(shapeClass);
    }
}
