/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package editor;

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
public class RectangleEditorTest {
    
    public RectangleEditorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        Shape shape = null;
        double width = 0.0;
        RectangleEditor instance = new RectangleEditor();
        instance.setWidth(shape, width);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        Shape shape = null;
        double height = 0.0;
        RectangleEditor instance = new RectangleEditor();
        instance.setHeight(shape, height);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetX() {
        System.out.println("setX");
        Shape shape = null;
        double newX = 0.0;
        RectangleEditor instance = new RectangleEditor();
        instance.setX(shape, newX);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetY() {
        System.out.println("setY");
        Shape shape = null;
        double newY = 0.0;
        RectangleEditor instance = new RectangleEditor();
        instance.setY(shape, newY);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Shape shape = null;
        RectangleEditor instance = new RectangleEditor();
        double expResult = 0.0;
        double result = instance.getWidth(shape);
        assertEquals(expResult, result, 0);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Shape shape = null;
        RectangleEditor instance = new RectangleEditor();
        double expResult = 0.0;
        double result = instance.getHeight(shape);
        assertEquals(expResult, result, 0);
        fail("The test case is a prototype.");
    }
    
}
