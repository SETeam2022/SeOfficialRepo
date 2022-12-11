package seproject;

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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import seproject.editor.EllipseEditorTest;

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
            this.file1 = folder.newFile("testfile1.bin");
            this.file2 = folder.newFile("testfile2.bin");
            this.file3 = folder.newFile("testfile3.bin");
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
    public void testSaveAndLoad() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("save");
        try {
            /* Test 1: the pane has no shapes on it */
            fm.save(file1);
            fm.load(file1);
            assertEquals(0, paper.getChildren().size());

            /* Test 2: the pane has a colored shape on it */
            paper.getChildren().add(testRectangle);
            fm.save(file2);
            fm.load(file2);
            /* Check the number of shapes into the paper, which at this point has to be 1. */
            assertEquals(1, paper.getChildren().size());
            /* Check if the shape is an instance of Rectangle */
            assertTrue(paper.getChildren().toArray()[0] instanceof Rectangle);
            Rectangle instancedRectangle = (Rectangle) paper.getChildren().get(0);
            /* Check if the tested rectangle and the instanced rectangle have the same dimensions */
            assertTrue(checkDims(instancedRectangle, testRectangle));
            /* Check if the tested rectangle and the instanced rectangle have the same colors */
            assertTrue(checkColors(instancedRectangle, testRectangle));

            /* Test 3: the pane has multiple different shapes on it */
            paper.getChildren().addAll(testLine, testEllipse);
            fm.save(file3);
            fm.load(file3);
            /* Check the number of shapes into the paper, which at this point has to be 3. */
            assertEquals(3, paper.getChildren().size());
            /* Check the instances' types of the shapes (in order of insertion among the children of the pane) */
            assertTrue(paper.getChildren().toArray()[0] instanceof Rectangle);
            assertTrue(paper.getChildren().toArray()[1] instanceof Line);
            assertTrue(paper.getChildren().toArray()[2] instanceof Ellipse);
            instancedRectangle = (Rectangle) paper.getChildren().get(0);
            /* Check if the tested rectangle and the instanced rectangle have the same dimensions */
            assertTrue(checkDims(instancedRectangle, testRectangle));
            /* Check if the tested rectangle and the instanced rectangle have the same colors */
            assertTrue(checkColors(instancedRectangle, testRectangle));
            Line instancedLine = (Line) paper.getChildren().get(1);
            /* Check if the tested line and the instanced line have the same dimensions */
            assertTrue(checkDims(instancedLine, testLine));
            /* Check if the tested line and the instanced line have the same colors */
            assertEquals(testLine.getStroke(), instancedLine.getStroke());
            Ellipse instancedEllipse = (Ellipse) paper.getChildren().get(2);
            /* Check if the tested ellipse and the ellipse line have the same dimensions */
            assertTrue(checkDims(instancedEllipse, testEllipse));
            /* Check if the tested ellipse and the ellipse line have the same colors */
            assertTrue(checkColors(instancedEllipse, testEllipse));

        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * This is a utility method to create a colored Rectangle in order to allow
     * the testing of the save and load of colored Rectangles to/from files.
     *
     * @return Rectangle
     */
    private Rectangle createRectangle() {
        Rectangle newRect = new Rectangle(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT), random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
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
        Ellipse newEllipse = new Ellipse(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT), random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
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
        Line newLine = new Line(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT), random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        newLine.setFill(Color.VIOLET);
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
        assertEquals(testRectangle.getX(), instancedRectangle.getX(), 0);
        assertEquals(testRectangle.getY(),instancedRectangle.getY(),  0);
        assertEquals(testRectangle.getWidth(), instancedRectangle.getWidth(), 0);
        assertEquals(testRectangle.getHeight(), instancedRectangle.getHeight(), 0);
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
        assertEquals(testLine.getStartX(), instancedLine.getStartX(), 0);
        assertEquals(testLine.getStartY(),instancedLine.getStartY(),  0);
        assertEquals(testLine.getEndX(),instancedLine.getEndX(),  0);
        assertEquals(testLine.getEndY(), instancedLine.getEndY(), 0);
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
        assertEquals(testEllipse.getCenterX(), instancedEllipse.getCenterX(), 0);
        assertEquals(testEllipse.getCenterY(), instancedEllipse.getCenterY(), 0);
        assertEquals(testEllipse.getRadiusX(), instancedEllipse.getRadiusX(), 0);
        assertEquals(testEllipse.getRadiusY(), instancedEllipse.getRadiusY(), 0);
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
        assertEquals(testRectangle.getFill(), instancedRectangle.getFill() );
        assertEquals(testRectangle.getStroke(), instancedRectangle.getStroke() );
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
        assertEquals(testEllipse.getFill(), instancedEllipse.getFill());
        assertEquals(testEllipse.getStroke(), instancedEllipse.getStroke());
        return true;
    }

}
