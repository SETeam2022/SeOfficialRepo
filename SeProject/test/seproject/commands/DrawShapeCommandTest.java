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

public class DrawShapeCommandTest {

    private Pane paper;

    private Shape testShape;

    private DrawShapeCommand com;
    
    private SecureRandom random;
    
    private DrawingArea dw;

    public DrawShapeCommandTest() {
    }

    /**
     * This method instances a new pane, a shape which will be used during the
     * test of the delete functionality and a DrawShapeCommand.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        dw = new DrawingArea(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        paper = dw.getPaper();
        testShape = new Rectangle();
        com = new DrawShapeCommand(testShape, dw);
    }

    /**
     * The method execute the command prepared in the setUp and tests if the
     * thestShape is between the children of the pane.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        com.execute();
        assertTrue(paper.getChildren().contains(testShape));
    }

    /**
     * The method execute the undo of the command prepared in the setUp and
     * tests if the thestShape is removed from the children of the pane.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        com.execute();
        com.undo();
        assertTrue(!paper.getChildren().contains(testShape));
    }

}
