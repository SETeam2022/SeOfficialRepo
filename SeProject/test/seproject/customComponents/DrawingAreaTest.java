package seproject.customComponents;

import java.security.SecureRandom;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.Constants;

public class DrawingAreaTest {
    
    private DrawingArea drawing;
    private int newGridSize;
    private SecureRandom random;
    private Pane paper;
    
    public DrawingAreaTest() {
    }


    @Before
    public void setUp() {
        new JFXPanel();
        random = new SecureRandom();
        newGridSize = random.nextInt(5)+2;
        drawing = new DrawingArea(Constants.MAX_WIDTH,Constants.MAX_HEIGHT);
        paper = drawing.getPaper();
    }

    @After
    public void tearDown() throws Exception {
    }
    

    /**
     * Test of redrawGrid, this test redraws the grid with a new distance 
     * <b>x</b> of a random quantity between 2 and 5.
     * It tests if the first line (vertical and orizontal) of the grid is 
     * <b>x</b> times the original one.
     */
    @Test
    public void testRedrawGrid() {
        // Drawing a grid
        System.out.println("RedrawGrid");
        
        Group originalGrid = drawing.getGrid();
    
        for(Node n : originalGrid.getChildren()){
            assertTrue("The grid should contains only line",n instanceof Line);
        }
        
        Line lineVertical = (Line) originalGrid.getChildren().get(0);
        Line lineOrizontal = (Line) originalGrid.getChildren().get((int)Math.ceil(drawing.getPrefWidth()/37.7952755906));
        
        drawing.redrawGrid(newGridSize);
        
        Group newGrid = drawing.getGrid();
        
        Line lineVertical1 = (Line) newGrid.getChildren().get(0);
        Line lineOrizontal1 = (Line) newGrid.getChildren().get((int)Math.ceil(drawing.getPrefWidth()/(37.7952755906*newGridSize)));
        
        assertEquals("The X of the new first line isn't in the correct position", lineVertical1.getStartX(),newGridSize*lineVertical.getStartX(),0 );
        assertEquals("The Y of the new first line isn't in the correct position", lineVertical1.getStartY(), lineVertical.getStartY(), 0 );
        
        assertEquals("The X of the new first line isn't in the correct position", lineOrizontal1.getStartX(),lineOrizontal.getStartX(),0 );
        assertEquals("The Y of the new first line isn't in the correct position", lineOrizontal1.getStartY(), newGridSize*lineOrizontal.getStartY(), 0 );     
        
    }
    
    /*
     * This method verifies that the visible property of the grid is originally 
     * false and then test if the grid becomes visible.
     */
    @Test
    public void testShowGrid(){
        Group grid = drawing.getGrid();
        assertFalse("The gris should not be seen by default", grid.visibleProperty().getValue());
        drawing.showGrid(true);
        assertTrue("The grid should be visible", grid.visibleProperty().getValue());
    }
    
    /*
     * This method adsd a shape into the drawing area and tests if the shape is 
     * correctly added into the inner pane.
     */
    @Test
    public void testAddInPaper(){
        System.out.println("addInPaper");
        Shape s = new Rectangle();
        drawing.addInPaper(s);
        assertTrue("The paper should contain the new shape",paper.getChildren().contains(s));         
    }
    
    /*
     * This method adsd a shape into the drawing area and tests if the shape is 
     * correctly added into the inner pane.
     */
    @Test
    public void testAddInPaper2(){
        System.out.println("addInPaper with index");
        Shape s1 = new Rectangle();
        Shape s2 = new Rectangle();
        drawing.addInPaper(0,s1);
        drawing.addInPaper(1, s2);
        
        assertEquals(0,paper.getChildren().indexOf(s1));
        assertEquals(1,paper.getChildren().indexOf(s2)); 
        
        Shape s3 = new Rectangle();
        drawing.addInPaper(1, s3);
        
        assertEquals(0,paper.getChildren().indexOf(s1));
        assertEquals(1,paper.getChildren().indexOf(s3));
        assertEquals(2,paper.getChildren().indexOf(s2));
        
    }
    
    /*
     * This method adds a shape into the drawing area and tests if the shape is 
     * correctly added into the inner pane.
     */
    @Test
    public void testRemoveFromPaper(){
        System.out.println("removeFromPaper");
        Shape s = new Rectangle();
        drawing.addInPaper(s);
        drawing.removeFromPaper(s);
        assertFalse("The paper shouldn't contain the shape",paper.getChildren().contains(s));          
    }
    /**
     * This method verify if the paper of the drawing area correctly contains a given element
    */
    @Test
    public void testPaperContains(){
        System.out.println("paperContains");
        Shape s = new Rectangle();
        drawing.addInPaper(s);
        assertTrue(drawing.paperContains(s));
        drawing.removeFromPaper(s);
        assertFalse(drawing.paperContains(s));
    }
    /**
     * This method adds a generic grapich element (Node) into the top layer of the DrawingArea
     */
    @Test
    public void TestAddInTopLayer(){
        System.out.println("addInTopLayer");
        TextArea t = new TextArea();
        assertTrue(!drawing.getChildren().contains(t));
        drawing.addInTopLayer(t);
        assertTrue(drawing.getChildren().contains(t));
    }
    
}
