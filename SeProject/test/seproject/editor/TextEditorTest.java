package seproject.editor;

import editor.TextEditor;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alewi
 */
public class TextEditorTest {
    
    public TextEditorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
        TextEditor instance = new TextEditor();
        instance.setWidth(shape, width);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        Shape shape = null;
        double height = 0.0;
        TextEditor instance = new TextEditor();
        instance.setHeight(shape, height);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Shape shape = null;
        TextEditor instance = new TextEditor();
        double expResult = 0.0;
        double result = instance.getWidth(shape);
        assertEquals(expResult, result, 0);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Shape shape = null;
        TextEditor instance = new TextEditor();
        double expResult = 0.0;
        double result = instance.getHeight(shape);
        assertEquals(expResult, result, 0);
        fail("The test case is a prototype.");
    }

    @Test
    public void testClone() {
        System.out.println("clone");
        Shape shape = null;
        TextEditor instance = new TextEditor();
        Shape expResult = null;
        Shape result = instance.clone(shape);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
