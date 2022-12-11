package seproject.editor;

import editor.EditorNotFoundException;
import editor.EllipseEditor;
import editor.LineEditor;
import editor.RectangleEditor;
import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import javafx.scene.shape.Arc;
import javafx.scene.shape.CubicCurve;

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

    /**
     * Checks whether the getInstance method throws an EditorNotFoundException
     * when the ShapeEditor instance for that specific Shape is not found.
     *
     */
    @Test(expected = EditorNotFoundException.class)
    public void testGetInstanceException() {
        System.out.println("getInstanceException");
        ShapeEditorFactory.getInstance(Arc.class);
    }

    /**
     * It is checked if the new inserted instance is actually present in the
     * ShapeEditorFactory map
     */
    @Test
    public void testAddInstance() {
        System.out.println("addInstance");

        ShapeEditorFactory.addInstance(CubicCurve.class, new ShapeEditor<CubicCurve>() {
            @Override
            public void setWidth(CubicCurve shape, double width) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void setHeight(CubicCurve shape, double height) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public double getWidth(CubicCurve shape) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public double getHeight(CubicCurve shape) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

        ShapeEditorFactory.getInstance(CubicCurve.class);

    }

}
