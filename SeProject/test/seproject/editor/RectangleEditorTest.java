package seproject.editor;

import editor.ShapeEditor;
import editor.ShapeEditorChooser;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import seproject.Constants;

public class RectangleEditorTest {

    private Rectangle testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private File file;
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    public RectangleEditorTest() {
    }

    @Before
    public void setUp() {
        this.testShape = new Rectangle();
        this.testShape.setFill(Color.RED);
        this.testShape.setStroke(Color.RED);
        try {
            this.file = folder.newFile("test.bin");
        } catch (IOException ex) {
            Logger.getLogger(EllipseEditorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.editor = ShapeEditorChooser.getInstance(testShape.getClass());
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
    boolean testHeightShape() {
        double expectedHeight = random.nextInt(Constants.MAX_HEIGHT);
        double actualHeight;
        editor.setHeight(testShape, expectedHeight);
        actualHeight = editor.getHeight(testShape);
        return actualHeight == expectedHeight;
    }

    /**
     * Test of the RectangleEditor class' clone method.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Rectangle actualShape = (Rectangle) editor.clone(testShape);
        equalRectangles(testShape, actualShape);
    }

    /**
     * Test of saveShape method, of class RectangleEditor.
     */
    @Test
    public void testSaveLoadShape() throws Exception {
        System.out.println("saveShape");
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
                editor.saveShape(testShape, out);
            }
            try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
                Class c = (Class) in.readObject();
                Rectangle readRectangle = (Rectangle) editor.loadShape(c,in);
                equalRectangles(testShape, readRectangle);
            }
    }

    /**
     * Utility method to assert that two rectangles are equal.
     * @param testShape
     * @param actualShape 
     */
    public void equalRectangles(Rectangle testShape, Rectangle actualShape){
        assertEquals(testShape.getX(),actualShape.getX(),0);
        assertEquals(testShape.getY(),actualShape.getY(),0);
        assertEquals(testShape.getWidth(),actualShape.getWidth(),0);
        assertEquals(testShape.getHeight(),actualShape.getHeight(),0);
        assertEquals(testShape.getFill(),actualShape.getFill());
        assertEquals(testShape.getStroke(),actualShape.getStroke());
    }
    
        /**
     * Test of the IOException thrown both by the save and load methods.
     */
    @Test(expected=IOException.class)
    public void testIOException() throws Exception{
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(""))))){
            editor.saveShape(testShape, out);
        }
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(""))))){
            Class c = Class.forName("ciao");
            editor.loadShape(c,in);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EllipseEditorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Test of the ClassNotFoundException thrown by the load method.
     */
    @Test(expected=ClassNotFoundException.class)
    public void testClassNotFoundException() throws Exception{
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            editor.saveShape(testShape, out);
        }
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            Class c = Class.forName("Ciao");
            editor.loadShape(c,in);
        } 
    }
    
    /**
     * Test of the InstantiationException thrown by the load method.
     */
    @Test(expected=InstantiationException.class)
    public void testInstantiationException() throws Exception{
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            editor.saveShape(testShape, out);
        }
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            editor.loadShape(Shape.class,in);
        } 
    }

}
