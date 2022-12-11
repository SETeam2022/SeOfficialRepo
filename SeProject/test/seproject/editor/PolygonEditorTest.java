package seproject.editor;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import seproject.Constants;

public class PolygonEditorTest {
    
    private Polyline testShape;
    private ShapeEditor editor;
    private SecureRandom random;
    private List <Double> list;
    private File file;
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    public PolygonEditorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        list = new ArrayList <>();
        this.random = new SecureRandom();
        this.testShape = new Polyline();
        this.testShape.setFill(Color.PINK);
        this.testShape.setStroke(Color.PINK);
        for(int i=0; i<Constants.NUM_VERTICES*2; i++) {
            list.add(random.nextDouble()); 
        }
        try {
            this.file = folder.newFile("test.bin");
        } catch (IOException ex) {
            Logger.getLogger(EllipseEditorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.testShape.getPoints().addAll(list);
        this.editor = ShapeEditorFactory.getInstance(testShape.getClass());
    }

    @After
    public void tearDown() throws Exception {
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
        equalPolygons(testShape, polygon);
    }

    /**
     * Test of saveShape method, of class PolygonEditor.
     */
    @Test
    public void testSaveLoadShape() throws Exception {
        System.out.println("saveLoadShape");
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            editor.saveShape(testShape, out);
        }
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            Class c = (Class) in.readObject();
            Polyline readPoly = (Polyline) editor.loadShape(c,in);
            equalPolygons(testShape, readPoly);
        }
        
    }
    
    /**
     * Utility method to assert that two polylines are equal.
     * @param testShape
     * @param polygon 
     */
    public void equalPolygons(Polyline testShape, Polyline polygon){
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
