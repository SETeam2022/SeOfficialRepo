package seproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public void setUp(){
        paper = new Pane();
        fm = new FileManager(paper);
        try {
            file1 = folder.newFile("testfile1.xml");
            file2 = folder.newFile("testfile2.xml");
            file3 = folder.newFile("testfile3.xml");
        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of save method, of class FileManager.
     */
    @Test
    public void testSave() {
        try {
            
            System.out.println("save");
            
            // Test 1: the pane has no shapes on it
            fm.save(file1);
            fm.load(file1); // Now the saved drawing has been loaded into the pane
            assertEquals(paper.getChildren().toArray().length, 0);
            
            // Test 2: the pane has a colored shape on it
            Rectangle rect = new Rectangle(0,0,6,2);
            rect.setFill(Color.PINK);
            rect.setStroke(Color.RED);
            paper.getChildren().add(rect);
            fm.save(file2);
            fm.load(file2); // Now the saved drawing has been loaded into the pane
            // check the number of shapes into the paper    
            assertEquals(paper.getChildren().toArray().length, 1);  
            // check if the shape is a Rectangle
            assertTrue(paper.getChildren().toArray()[0] instanceof Rectangle); 
            Rectangle rect2 = (Rectangle) paper.getChildren().toArray()[0];
            // check dims
            assertEquals(rect2.getX(), rect.getX(), 0);
            assertEquals(rect2.getY(), rect.getY(), 0);
            assertEquals(rect2.getWidth(), rect.getWidth(), 0);
            assertEquals(rect2.getHeight(), rect.getHeight(), 0);
            // check colors
            assertEquals(rect2.getFill(), rect.getFill());
            assertEquals(rect2.getStroke(), rect.getStroke());
                        
            // Test 3: the pane has multiple different shapes on it
            Line line = new Line(0,0,4,4);
            Ellipse ellipse = new Ellipse(0, 0,4, 2);
            paper.getChildren().addAll(line, ellipse);
            fm.save(file3);
            fm.load(file3); // Now the saved drawing has been loaded into the pane
            // check the number of shapes into the paper
            assertEquals(paper.getChildren().toArray().length, 3); 
            // check the instances' type
            assertTrue(paper.getChildren().toArray()[0] instanceof Rectangle);
            assertTrue(paper.getChildren().toArray()[1] instanceof Line);
            assertTrue(paper.getChildren().toArray()[2] instanceof Ellipse);
            rect2 = (Rectangle) paper.getChildren().toArray()[0];
            // check rect dims
            assertEquals(rect2.getX(), rect.getX(), 0);
            assertEquals(rect2.getY(), rect.getY(), 0);
            assertEquals(rect2.getWidth(), rect.getWidth(), 0);
            assertEquals(rect2.getHeight(), rect.getHeight(), 0);
            // check rect colors
            assertEquals(rect2.getFill(), rect.getFill());
            assertEquals(rect2.getStroke(), rect.getStroke());
            Line line2 = (Line) paper.getChildren().toArray()[1];
            // check line dims
            assertEquals(line.getStartX(), line2.getStartX(),0);
            assertEquals(line.getStartY(), line2.getStartY(),0);
            assertEquals(line.getEndX(), line2.getEndX(),0);
            assertEquals(line.getEndY(), line2.getEndY(),0);
            // check line color
            assertEquals(line.getStroke(), line2.getStroke());
            Ellipse ellipse2 = (Ellipse) paper.getChildren().toArray()[2];
            // check ell dims
            assertEquals(ellipse2.getCenterX(), ellipse.getCenterX(),0);
            assertEquals(ellipse2.getCenterY(), ellipse.getCenterY(),0);
            assertEquals(ellipse2.getRadiusX(), ellipse.getRadiusX(),0);
            assertEquals(ellipse2.getRadiusY(), ellipse.getRadiusY(),0);
            // check ellipse's colors
            assertEquals(ellipse2.getFill(), ellipse.getFill());
            assertEquals(ellipse2.getStroke(), ellipse.getStroke());
            
        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Test of load method, of class FileManager.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        try {
            
            // Test 1: the pane has no shapes on it
            fm.save(file1);
            fm.load(file1); // Now the saved drawing has been loaded into the pane
            assertEquals(paper.getChildren().toArray().length, 0);
            
            // Test 2: the pane has a colored shape on it
            Rectangle rect = new Rectangle(0,0,6,2);
            rect.setFill(Color.PINK);
            rect.setStroke(Color.RED);
            paper.getChildren().add(rect);
            fm.save(file2);
            fm.load(file2); // Now the saved drawing has been loaded into the pane
            // check the number of shapes into the paper     
            assertEquals(paper.getChildren().toArray().length, 1); 
            // check if the shape is a Rectangle
            assertTrue(paper.getChildren().toArray()[0] instanceof Rectangle); 
            Rectangle rect2 = (Rectangle) paper.getChildren().toArray()[0];
            // check dims
            assertEquals(rect2.getX(), rect.getX(), 0);
            assertEquals(rect2.getY(), rect.getY(), 0);
            assertEquals(rect2.getWidth(), rect.getWidth(), 0);
            assertEquals(rect2.getHeight(), rect.getHeight(), 0);
            // check colors
            assertEquals(rect2.getFill(), rect.getFill());
            assertEquals(rect2.getStroke(), rect.getStroke());
                        
            // Test 3: the pane has multiple different shapes on it
            Line line = new Line(0,0,4,4);
            Ellipse ellipse = new Ellipse(0, 0,4, 2);
            paper.getChildren().addAll(line, ellipse);
            fm.save(file3);
            fm.load(file3); // Now the saved drawing has been loaded into the pane
            // check the number of shapes into the paper
            assertEquals(paper.getChildren().toArray().length, 3); 
            // check the instances' type
            assertTrue(paper.getChildren().toArray()[0] instanceof Rectangle);
            assertTrue(paper.getChildren().toArray()[1] instanceof Line);
            assertTrue(paper.getChildren().toArray()[2] instanceof Ellipse);
            rect2 = (Rectangle) paper.getChildren().toArray()[0];
            // check rect dims
            assertEquals(rect2.getX(), rect.getX(), 0);
            assertEquals(rect2.getY(), rect.getY(), 0);
            assertEquals(rect2.getWidth(), rect.getWidth(), 0);
            assertEquals(rect2.getHeight(), rect.getHeight(), 0);
            // check rect colors
            assertEquals(rect2.getFill(), rect.getFill());
            assertEquals(rect2.getStroke(), rect.getStroke());
            Line line2 = (Line) paper.getChildren().toArray()[1];
            // check line dims
            assertEquals(line.getStartX(), line2.getStartX(),0);
            assertEquals(line.getStartY(), line2.getStartY(),0);
            assertEquals(line.getEndX(), line2.getEndX(),0);
            assertEquals(line.getEndY(), line2.getEndY(),0);
            // check line color
            assertEquals(line.getStroke(), line2.getStroke());
            Ellipse ellipse2 = (Ellipse) paper.getChildren().toArray()[2];
            // check ell dims
            assertEquals(ellipse2.getCenterX(), ellipse.getCenterX(),0);
            assertEquals(ellipse2.getCenterY(), ellipse.getCenterY(),0);
            assertEquals(ellipse2.getRadiusX(), ellipse.getRadiusX(),0);
            assertEquals(ellipse2.getRadiusY(), ellipse.getRadiusY(),0);
            // check ellipse's colors
            assertEquals(ellipse2.getFill(), ellipse.getFill());
            assertEquals(ellipse2.getStroke(), ellipse.getStroke());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
