package seproject;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * This class' aim is the testing of the save and load methods of the
 * FileManager class.
 *
 */
public class FileManagerTest {

    private FileManager fm;
    private Pane paper;
    private File file1, file2, file3;
    private SecureRandom random;
    private Rectangle testRectangle;
    private Line testLine;
    private Ellipse testEllipse;

    public FileManagerTest() {

    }

    /**
     * The following folder and its content will be deleted after tests are run,
     * even if failures of exceptions occur.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * This will be executed before each test, a Pane, a FileManager and all
     * temporary files are created.
     *
     */
    @Before
    public void setUp() {
        paper = new Pane();
        fm = new FileManager(paper);
        try {
            this.file1 = folder.newFile("testfile1.xml");
            this.file2 = folder.newFile("testfile2.xml");
            this.file3 = folder.newFile("testfile3.xml");
            this.random = new SecureRandom();
            this.testRectangle = createRectangle();
            this.testEllipse = createEllipse();
            this.testLine = createLine();
        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of save method, of class FileManager.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        testDrawingIO();
    }

    /**
     * Test of load method, of class FileManager.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        testDrawingIO();
    }

    /**
     * This is a utility method to create a colored Rectangle in order to allow
     * the testing of the save and load of colored Rectangles to/from files.
     *
     * @return Rectangle
     */
    private Rectangle createRectangle() {
        Rectangle newRect = new Rectangle(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        newRect.setFill(Color.PINK);
        newRect.setStroke(Color.RED);
        return newRect;
    }

    /**
     * This is a utility method to create a colored Ellipse in order to allow
     * the testing of the save and load of colored Ellipses to/from files.
     *
     * @return Rectangle
     */
    private Ellipse createEllipse() {
        Ellipse newEllipse = new Ellipse(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        newEllipse.setFill(Color.GREEN);
        newEllipse.setStroke(Color.BLUE);
        return newEllipse;
    }

    /**
     * This is a utility method to create a colored Line in order to allow the
     * testing of the save and load of colored Lines to/from files.
     *
     * @return Rectangle
     */
    private Line createLine() {
        Line newLine = new Line(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        newLine.setStroke(Color.VIOLET);
        return newLine;
    }

    /**
     * This is a utility method to check if two rectangles have the same
     * dimensions.
     *
     * @param instancedRectangle
     * @param testRectangle
     * @return boolean
     */
    private boolean checkDims(Rectangle instancedRectangle, Rectangle testRectangle) {
        assertEquals(instancedRectangle.getX(), testRectangle.getX(), 0);
        assertEquals(instancedRectangle.getY(), testRectangle.getY(), 0);
        assertEquals(instancedRectangle.getWidth(), testRectangle.getWidth(), 0);
        assertEquals(instancedRectangle.getHeight(), testRectangle.getHeight(), 0);
        return true;
    }

    /**
     * This is a utility method to check if two lines have the same dimensions.
     *
     * @param instancedLine
     * @param testLine
     * @return boolean
     */
    private boolean checkDims(Line instancedLine, Line testLine) {
        assertEquals(instancedLine.getStartX(), testLine.getStartX(), 0);
        assertEquals(instancedLine.getStartY(), testLine.getStartY(), 0);
        assertEquals(instancedLine.getEndX(), testLine.getEndX(), 0);
        assertEquals(instancedLine.getEndY(), testLine.getEndY(), 0);
        return true;
    }

    /**
     * his is a utility method to check if two ellipses have the same
     * dimensions.
     *
     * @param instancedEllipse
     * @param testEllipse
     * @return boolean
     */
    private boolean checkDims(Ellipse instancedEllipse, Ellipse testEllipse) {
        assertEquals(instancedEllipse.getCenterX(), testEllipse.getCenterX(), 0);
        assertEquals(instancedEllipse.getCenterY(), testEllipse.getCenterY(), 0);
        assertEquals(instancedEllipse.getRadiusX(), testEllipse.getRadiusX(), 0);
        assertEquals(instancedEllipse.getRadiusY(), testEllipse.getRadiusY(), 0);
        return true;
    }

    /**
     * This is a utility method to check if two rectangles have the same colors.
     *
     * @param instancedRectangle
     * @param testRectangle
     * @return boolean
     */
    private boolean checkColors(Rectangle instancedRectangle, Rectangle testRectangle) {
        assertEquals(instancedRectangle.getFill(), testRectangle.getFill());
        assertEquals(instancedRectangle.getStroke(), testRectangle.getStroke());
        return true;
    }

    /**
     * This is a utility method to check if two ellipses have the same colors.
     *
     * @param instancedEllipse
     * @param testEllipse
     * @return boolean
     */
    private boolean checkColors(Ellipse instancedEllipse, Ellipse testEllipse) {
        assertEquals(instancedEllipse.getFill(), testEllipse.getFill());
        assertEquals(instancedEllipse.getStroke(), testEllipse.getStroke());
        return true;
    }

    /**
     * This method performs the test of the save and load methods, calling each
     * other.
     */
    private void testDrawingIO() {
        try {

            /* Test 1: the pane has no shapes on it */
            fm.save(file1);
            fm.load(file1);
            assertEquals(paper.getChildren().toArray().length, 0);

            /* Test 2: the pane has a colored shape on it */
            paper.getChildren().add(testRectangle);
            fm.save(file2);
            fm.load(file2);
            /* Check the number of shapes into the paper, which at this point has to be 1. */
            assertEquals(paper.getChildren().toArray().length, 1);
            /* Check if the shape is an instance of Rectangle */
            assertTrue(paper.getChildren().toArray()[0] instanceof Rectangle);
            Rectangle instancedRectangle = (Rectangle) paper.getChildren().toArray()[0];
            /* Check if the tested rectangle and the instanced rectangle have the same dimensions */
            assertTrue(checkDims(instancedRectangle, testRectangle));
            /* Check if the tested rectangle and the instanced rectangle have the same colors */
            assertTrue(checkColors(instancedRectangle, testRectangle));

            /* Test 3: the pane has multiple different shapes on it */
            paper.getChildren().addAll(testLine, testEllipse);
            fm.save(file3);
            fm.load(file3);
            /* Check the number of shapes into the paper, which at this point has to be 3. */
            assertEquals(paper.getChildren().toArray().length, 3);
            /* Check the instances' types of the shapes (in order of insertion among the children of the pane) */
            assertTrue(paper.getChildren().toArray()[0] instanceof Rectangle);
            assertTrue(paper.getChildren().toArray()[1] instanceof Line);
            assertTrue(paper.getChildren().toArray()[2] instanceof Ellipse);
            instancedRectangle = (Rectangle) paper.getChildren().toArray()[0];
            /* Check if the tested rectangle and the instanced rectangle have the same dimensions */
            assertTrue(checkDims(instancedRectangle, testRectangle));
            /* Check if the tested rectangle and the instanced rectangle have the same colors */
            assertTrue(checkColors(instancedRectangle, testRectangle));
            Line instancedLine = (Line) paper.getChildren().toArray()[1];
            /* Check if the tested line and the instanced line have the same dimensions */
            assertTrue(checkDims(instancedLine, testLine));
            /* Check if the tested line and the instanced line have the same colors */
            assertEquals(instancedLine.getStroke(), testLine.getStroke());
            Ellipse instancedEllipse = (Ellipse) paper.getChildren().toArray()[2];
            /* Check if the tested ellipse and the ellipse line have the same dimensions */
            assertTrue(checkDims(instancedEllipse, testEllipse));
            /* Check if the tested ellipse and the ellipse line have the same colors */
            assertTrue(checkColors(instancedEllipse, testEllipse));

        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
