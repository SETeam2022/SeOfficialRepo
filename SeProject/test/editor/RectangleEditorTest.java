package editor;

import java.security.SecureRandom;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleEditorTest {

    private Rectangle testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private static final int maxValue = 10000;

    public RectangleEditorTest() {
    }

    @Before
    public void setUp() {
        this.testShape = new Rectangle();
        this.editor = ShapeEditorFactory.getInstance(testShape.getClass());
        this.random = new SecureRandom();
    }

    /**
     * Test of the RectangleEditor class' setWidth method.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of the RectangleEditor class' setHieght method.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        assertTrue(testHeightShape());
    }

    /**
     * Test of the RectangleEditor class' getWidth method.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of the RectangleEditor class' getHeight method.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        assertTrue(testHeightShape());
    }

    /**
     * Utility method which performs the comparison between the expected and the
     * actual width of a shape.
     *
     * @return true or false
     */
    private boolean testWidthShape() {
        double expectedWidth = random.nextInt(maxValue);
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
    private boolean testHeightShape() {
        double expectedHeight = random.nextInt(maxValue);
        double actualHeight;
        editor.setHeight(testShape, expectedHeight);
        actualHeight = editor.getHeight(testShape);
        return actualHeight == expectedHeight;
    }

    @Test
    public void testClone() {
        System.out.println("clone");
        Rectangle actualShape = (Rectangle) editor.clone(testShape);
        assertEquals(testShape.getX(),actualShape.getX(),0);
        assertEquals(testShape.getY(),actualShape.getY(),0);
        assertEquals(testShape.getWidth(),actualShape.getWidth(),0);
        assertEquals(testShape.getHeight(),actualShape.getHeight(),0);
        assertEquals(testShape.getFill(),actualShape.getFill());
        assertEquals(testShape.getStroke(),actualShape.getStroke());
    }

}
