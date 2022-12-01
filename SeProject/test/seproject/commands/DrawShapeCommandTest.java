package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teodo
 */
public class DrawShapeCommandTest {
    
    private Pane paper;
    
    private Shape testShape;
    
    private DrawShapeCommand com;
    
    public DrawShapeCommandTest() {
    }
    
    @Before
    public void setUp() {
        paper = new Pane();
        testShape = new Rectangle();
        com = new DrawShapeCommand(testShape,paper);
    }
    
    /**
     * The method execute the command prepared in the setUp and tests if the
     * thestShape is between the children of the pane
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        com.execute();
        assertTrue(paper.getChildren().contains(testShape));
    }

    /**
     * The method execute the undo of the command prepared in the setUp and tests if the
     * thestShape is removed from the children of the pane
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        com.execute();
        com.undo();
        assertTrue(!paper.getChildren().contains(testShape));
    }
    
}
