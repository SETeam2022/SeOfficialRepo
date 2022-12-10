package seproject.customComponents;

import java.security.SecureRandom;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.Node;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.TestConstants;

public class DrawingAreaTest {
    
    private DrawingArea drawing;
    private int newGridSize;
    private SecureRandom random;
    private Pane paper;
    
    public DrawingAreaTest() {
    }


    @Before
    public void setUp() {
        random = new SecureRandom();
        newGridSize = random.nextInt(5)+2;
        drawing = new DrawingArea(TestConstants.MAX_WIDTH,TestConstants.MAX_HEIGHT);
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
    public void testAddShape(){
        Shape s = new Rectangle();
        drawing.addShape(s);
        assertTrue("The paper should contain the new shape",paper.getChildren().contains(s));         
    }
    
    /*
     * This method adds a shape into the drawing area and tests if the shape is 
     * correctly added into the inner pane.
     */
    @Test
    public void testRemoveShape(){
        Shape s = new Rectangle();
        drawing.addShape(s);
        drawing.removeShape(s);
        assertFalse("The paper shouldn't contain the shape",paper.getChildren().contains(s));          
    }
    
}
