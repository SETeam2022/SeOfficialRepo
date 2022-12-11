package seproject.commands;

import java.security.SecureRandom;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.customComponents.DrawingArea;
import seproject.Constants;
import seproject.customComponents.LayeredPaper;

public class DeleteShapeCommandTest {

    private LayeredPaper paper;
    
    private DrawingArea dw;
   
    private SecureRandom random;

    private Shape testShape;

    private DeleteShapeCommand com;

    public DeleteShapeCommandTest() {
    }

    /**
     * This method instances a new pane, a shape which will be used during the
     * test of the delete functionality, and a DeleteShapeCommand.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        paper = new DrawingArea(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        testShape = new Rectangle();
        com = new DeleteShapeCommand(testShape, dw);
    }

    /**
     * This method executes the command prepared in the setUp and tests if the
     * thestShape is among the children of the pane.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        com.execute();
        assertTrue(!paper.paperContains(testShape));
    }

    /**
     * This method executes the undo of the command prepared in the setUp and
     * tests if the thestShape has been removed from the children of the pane.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        com.execute();
        com.undo();
        assertTrue(paper.paperContains(testShape));
    }

}
