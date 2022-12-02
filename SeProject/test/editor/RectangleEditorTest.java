/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package editor;

import java.security.SecureRandom;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alewi
 */
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
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        assertTrue(testWidthShape());
    }

    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        assertTrue(testHeightShape());
    }

    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        assertTrue(testWidthShape());
    }

    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        assertTrue(testHeightShape());
    }
    private boolean testWidthShape(){
        double expectedWidth = random.nextInt(maxValue);
        double actualWidth;
        editor.setWidth(testShape, expectedWidth);
        actualWidth = editor.getWidth(testShape);
        return actualWidth==expectedWidth;
    }
    
    private boolean testHeightShape(){
        double expectedHeight = random.nextInt(maxValue);
        double actualHeight;
        editor.setHeight(testShape, expectedHeight);
        actualHeight = editor.getHeight(testShape);
        return actualHeight==expectedHeight;
    }
    
    
}
