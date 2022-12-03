package editor;

import java.security.SecureRandom;
import javafx.scene.shape.Line;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LineEditorTest {

    private Line testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private static final int maxValue = 10000;

    public LineEditorTest() {
    }

    @Before
    public void setUp() {
        this.testShape = new Line();
        this.editor = ShapeEditorFactory.getInstance(testShape.getClass());
        this.random = new SecureRandom();
    }

    /**
     * Test of the LineEditor class' setWidth method.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of the LineEditor class' setHeight method.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        assertTrue(testHeightShape());
    }

    /**
     * Test of the LineEditor class' getWidth method.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of the LineEditor class' getHeight method.
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

}
