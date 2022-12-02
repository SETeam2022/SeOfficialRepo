/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package editor;

import java.security.SecureRandom;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alewi
 */
public class EllipseEditorTest {
    private Ellipse testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private static final int maxValue = 10000;
    
    public EllipseEditorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        this.testShape = new Ellipse();
        this.editor = ShapeEditorFactory.getInstance(testShape.getClass());
        this.random = new SecureRandom();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        Shape shape = null;
        double width = 0.0;
        EllipseEditor instance = new EllipseEditor();
        instance.setWidth(shape, width);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        Shape shape = null;
        double height = 0.0;
        EllipseEditor instance = new EllipseEditor();
        instance.setHeight(shape, height);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Shape shape = null;
        EllipseEditor instance = new EllipseEditor();
        double expResult = 0.0;
        double result = instance.getWidth(shape);
        assertEquals(expResult, result, 0);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Shape shape = null;
        EllipseEditor instance = new EllipseEditor();
        double expResult = 0.0;
        double result = instance.getHeight(shape);
        assertEquals(expResult, result, 0);
        fail("The test case is a prototype.");
    }
    
}
