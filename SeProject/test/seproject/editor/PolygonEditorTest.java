package seproject.editor;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Polyline;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.Constants;

public class PolygonEditorTest {
    
    private Polyline testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private List <Double> list;
    
    public PolygonEditorTest() {
    }
    
    @Before
    public void setUp() {
        list = new ArrayList <>();
        this.random = new SecureRandom();
        this.testShape = new Polyline();
        for(int i=0; i<Constants.NUM_VERTICES*2; i++) {
            list.add(random.nextDouble()); 
        }
        this.testShape.getPoints().addAll(list);
        this.editor = ShapeEditorFactory.getInstance(testShape.getClass());
    }

    /**
     * Test of setWidth method, of class PolygonEditor.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of setHeight method, of class PolygonEditor.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        assertTrue(testHeightShape());
    }

    /**
     * Test of getWidth method, of class PolygonEditor.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        assertTrue(testWidthShape());
    }

    /**
     * Test of getHeight method, of class PolygonEditor.
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
        double expectedWidth = random.nextInt(Constants.MAX_WIDTH);
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
        double expectedHeight = random.nextInt(Constants.MAX_HEIGHT);
        double actualHeight;
        editor.setHeight(testShape, expectedHeight);
        actualHeight = editor.getHeight(testShape);
        return actualHeight == expectedHeight;
    }

    /**
     * Test of clone method, of class PolygonEditor.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Polyline polygon = (Polyline) editor.clone(testShape);
        int i=0; 
        for (double d : polygon.getPoints()){
            assertEquals(testShape.getPoints().get(i), d,0);
            i++;
        }
        assertEquals(testShape.getFill(), polygon.getFill());
        assertEquals(testShape.getStroke(), polygon.getStroke());
        assertEquals(testShape.getStrokeWidth(), polygon.getStrokeWidth(),0);
    }
    
}
