package editor;

import java.security.SecureRandom;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EllipseEditorTest {

    private Ellipse testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private static final int maxValue = 10000;

    public EllipseEditorTest() {
    }

    @Before
    public void setUp() {
        this.testShape = new Ellipse();
        this.editor = ShapeEditorFactory.getInstance(testShape.getClass());
        this.random = new SecureRandom();
    }

    /**
     * Test of the EllipseEditor class' setWidth method.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of the EllipseEditor class' setHeight method.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        assertTrue(testHeightShape());
    }

    /**
     * Test of the EllipseEditor class' getWidth method.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of the EllipseEditor class' getHeight method.
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
        Ellipse actualShape = (Ellipse) editor.clone(testShape);
        assertEquals(testShape.getCenterX(),actualShape.getCenterX(),0);
        assertEquals(testShape.getCenterY(),actualShape.getCenterY(),0);
        assertEquals(testShape.getRadiusX(),actualShape.getRadiusX(),0);
        assertEquals(testShape.getRadiusY(),actualShape.getRadiusY(),0);
        assertEquals(testShape.getRadiusY(),actualShape.getRadiusY(),0);
        assertEquals(testShape.getStroke(),actualShape.getStroke());
        assertEquals(testShape.getFill(),actualShape.getFill());   
    }

}
