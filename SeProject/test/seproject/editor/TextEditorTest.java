package seproject.editor;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import java.security.SecureRandom;
import javafx.scene.text.Text;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.TestConstants;

/**
 *
 * @author alewi
 */
public class TextEditorTest {

    private Text testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private static final String EXAMPLE_STRING = "HEY THERE!";

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
        //this.random = new SecureRandom();
        //this.testShape = new Text(50,50,"HEY THERE");
        //this.testShape = new Text(this.random.nextInt(TestConstants.MAX_WIDTH/2),this.random.nextInt(TestConstants.MIN_HEIGHT/2),EXAMPLE_STRING);
        //this.editor = ShapeEditorFactory.getInstance(testShape.getClass());
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetWidth() {
        //System.out.println("setWidth");
        //assertTrue(testWidthShape());
    }

    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        /*Shape shape = null;
        double height = 0.0;
        TextEditor instance = new TextEditor();
        instance.setHeight(shape, height);
        fail("The test case is a prototype.");*/
    }

    @Test
    public void testGetWidth() {
        /*System.out.println("getWidth");
        Shape shape = null;
        TextEditor instance = new TextEditor();
        double expResult = 0.0;
        double result = instance.getWidth(shape);
        assertEquals(expResult, result, 0);
        fail("The test case is a prototype.");*/
    }

    @Test
    public void testGetHeight() {
        /*System.out.println("getHeight");
        Shape shape = null;
        TextEditor instance = new TextEditor();
        double expResult = 0.0;
        double result = instance.getHeight(shape);
        assertEquals(expResult, result, 0);
        fail("The test case is a prototype.");*/
    }

    @Test
    public void testClone() {
        System.out.println("clone");
        /*Shape shape = null;
        TextEditor instance = new TextEditor();
        Shape expResult = null;
        Shape result = instance.clone(shape);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");*/
    }

    /**
     * Utility method which performs the comparison between the expected and the
     * actual width of a shape.
     *
     * @return true or false
     */
    private boolean testWidthShape() {
        double expectedWidth = random.nextInt(TestConstants.MAX_WIDTH);
        double actualWidth;
        editor.setWidth(testShape, expectedWidth);
        actualWidth = editor.getWidth(testShape);
        return actualWidth == expectedWidth;
    }

    /**
     * Utility method which performs the comparison between the expected and the
     * actual height of a shape.
     *
     * @return true or false
     */
    boolean testHeightShape() {
        double expectedHeight = random.nextInt(TestConstants.MAX_HEIGHT);
        double actualHeight;
        editor.setHeight(testShape, expectedHeight);
        actualHeight = editor.getHeight(testShape);
        return actualHeight == expectedHeight;
    }

}
