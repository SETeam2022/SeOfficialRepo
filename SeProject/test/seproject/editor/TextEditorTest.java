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
import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import seproject.Constants;

public class TextEditorTest {

    private Text testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private static final String EXAMPLE_STRING = "HEY THERE!";
    private File file;
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    public TextEditorTest() {
    }

    @Before
    public void setUp() {
        new JFXPanel();
        this.random = new SecureRandom();
        this.testShape = new Text(this.random.nextInt(Constants.MAX_WIDTH / 2), this.random.nextInt(Constants.MIN_HEIGHT / 2), EXAMPLE_STRING);
        this.testShape.setFill(Color.BLUE);
        this.testShape.setStroke(Color.BLUE);
        try {
            this.file = folder.newFile("test.bin");
        } catch (IOException ex) {
            Logger.getLogger(EllipseEditorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.editor = ShapeEditorChooser.getInstance(testShape.getClass());

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
        equalTexts(testShape, actualShape);
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
     * Test of saveShape method, of class TextEditor.
     */
    @Test
    public void testSaveLoadShape() throws Exception {
        System.out.println("saveShape");
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            editor.saveShape(testShape, out);
        }
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            Class c = (Class) in.readObject();
            Text readText = (Text) editor.loadShape(c,in);
            equalTexts(testShape, readText);
        }
    }

    /**
     * Utility method to assert that two texts are equal.
     * @param testShape
     * @param actualShape 
     */
    public void equalTexts(Text testShape, Text actualShape){
        assertEquals(testShape.getX(), actualShape.getX(), 0);
        assertEquals(testShape.getY(), actualShape.getY(), 0);
        assertEquals(testShape.getText(), actualShape.getText());
        assertEquals(testShape.getFont(), actualShape.getFont());
        assertEquals(testShape.getWrappingWidth(), actualShape.getWrappingWidth(), 0);
        assertEquals(testShape.getStroke(), actualShape.getStroke());
        assertEquals(testShape.getFill(), actualShape.getFill());
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
