package editor;

import java.util.HashMap;
import java.util.Map;

public class ShapeEditorFactory {

     private static final Map <String,ShapeEditor> editors = new HashMap<>();
     
      static {
            editors.put("javafx.scene.shape.Rectangle",new RectangleEditor());
      }
      
      public static ShapeEditor getInstance(String type) {
            return editors.get(type);
      }
}