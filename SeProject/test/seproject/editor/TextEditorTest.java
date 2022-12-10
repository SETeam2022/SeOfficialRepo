package seproject.editor;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import java.security.SecureRandom;
import javafx.embed.swing.JFXPanel;
import javafx.scene.text.Text;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seproject.TestConstants;

public class TextEditorTest {

    private Text testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private static final String EXAMPLE_STRING = "HEY THERE!";

    public TextEditorTest() {
    }

    @Before
    public void setUp() {
        new JFXPanel();
        this.random = new SecureRandom();
        this.testShape = new Text(this.random.nextInt(TestConstants.MAX_WIDTH / 2), this.random.nextInt(TestConstants.MIN_HEIGHT / 2), EXAMPLE_STRING);

        this.editor = ShapeEditorFactory.getInstance(testShape.getClass());

    }

    /**
     * Test of the TextEditor class' getSetWidth method.
     */
    @Test
    public void testGetSetWidth() {
        System.out.println("setWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of the TextEditor class' getSetHeight method.
     */
    @Test
    public void testGetSetHeight() {
        System.out.println("setHeight");
        assertTrue(testWidthShape());
    }
    
    /**
     * Test of the TextEditor class' clone method.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Text actualShape = (Text) editor.clone(testShape);
        assertEquals(testShape.getX(), actualShape.getX(), 0);
        assertEquals(testShape.getY(), actualShape.getY(), 0);
        assertEquals(testShape.getText(), actualShape.getText());
        assertEquals(testShape.getFont(), actualShape.getFont());
        assertEquals(testShape.getWrappingWidth(), actualShape.getWrappingWidth(), 0);
        assertEquals(testShape.getStroke(), actualShape.getStroke());
        assertEquals(testShape.getFill(), actualShape.getFill());
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
