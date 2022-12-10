package seproject.editor;

import editor.EllipseEditor;
import editor.LineEditor;
import editor.RectangleEditor;
import editor.ShapeEditorFactory;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShapeEditorFactoryTest {

    public ShapeEditorFactoryTest() {
    }

    /**
     * Test of the getInstance methods, of class ShapeEditorFactory.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Class<?> expectedClass;
        Class<?> actualClass;
        Shape testShape;
        // Test 1
        expectedClass = RectangleEditor.class;
        testShape = new Rectangle();
        actualClass = ShapeEditorFactory.getInstance(testShape.getClass()).getClass();
        assertEquals(expectedClass, actualClass);
        // Test 2
        expectedClass = LineEditor.class;
        testShape = new Line();
        actualClass = ShapeEditorFactory.getInstance(testShape.getClass()).getClass();
        assertEquals(expectedClass, actualClass);
        // Test 3
        expectedClass = EllipseEditor.class;
        testShape = new Ellipse();
        actualClass = ShapeEditorFactory.getInstance(testShape.getClass()).getClass();
        assertEquals(expectedClass, actualClass);
    }

}
