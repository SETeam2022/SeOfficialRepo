package seproject.commands;

import java.security.SecureRandom;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seproject.customComponents.DrawingArea;
import seproject.TestConstants;
import seproject.tools.SelectedShapeManager;

public class BringToFrontCommandTest {

    private SelectedShapeManager ssm;
    private SecureRandom random;
    private Pane paper;
    private Rectangle rect;
    private Ellipse ell;
    private Line line;
    private DrawingArea dw;
    private Invoker invoker;
    /**
     * This method instances a new pane and a series of shapes which will be used during the 
     * test of the bring to front functionality.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        this.dw = new DrawingArea(1920,1080);
        this.paper = dw.getPaper();
        this.rect = new Rectangle(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        this.ell = new Ellipse(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        this.line = new Line(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        SelectedShapeManager.setSelectedShapeManagerPaper(dw);
        this.ssm = SelectedShapeManager.getSelectedShapeManager();
        this.invoker = Invoker.getInvoker();
    }

    /**
     * Test of execute method, of class BringToFrontCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        insertAndBringToFront();
    }

    /**
     * Test of undo method, of class BringToFrontCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        insertAndBringToFront();
        
        /* Test 1: undo of the BringToFrontCommand on the line */
        invoker.undoLastCommand();
        assertTrue(this.paper.getChildren().indexOf(this.line) > this.paper.getChildren().indexOf(this.rect));
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        
        /* Test 2: undo of the BringToFrontCommand on the ellipse */
        invoker.undoLastCommand();
        assertTrue(this.paper.getChildren().indexOf(this.ell) > this.paper.getChildren().indexOf(this.rect));
        
        /* Test 3: undo of the BringToFrontCommand on the rectangle */
        invoker.undoLastCommand();
        assertEquals(0, this.paper.getChildren().indexOf(this.rect), 0);
    }
    
    /**
     * Utility method to insert shapes and perform some bring to front operations on them
     * simulating the classic utilization flow of a user.
     */
    private void insertAndBringToFront() {
        //this.paper.getChildren().clear();
        
        /* Test 1: there's just one shape inside the pane */
        this.paper.getChildren().add(rect);
        createCommandAndExecute(this.rect);
        assertEquals(0, this.paper.getChildren().indexOf(this.ssm.getSelectedShape()), 0);
        
        /* Test 2: there are two shapes inside the pane */
        this.paper.getChildren().add(ell);
        createCommandAndExecute(this.rect);
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        
        /* Test 3: there are three shapes inside the pane */
        this.paper.getChildren().add(line);
        createCommandAndExecute(this.ell);
        assertTrue(this.paper.getChildren().indexOf(this.ell) > this.paper.getChildren().indexOf(this.line));
        assertTrue(this.paper.getChildren().indexOf(this.line) > this.paper.getChildren().indexOf(this.rect));
    }

    /**
     * This is a utility method to create and execute a command on the given shape.
     * @param s
     * @param cmd
     * @return BringToFrontCommand
     */
    private void createCommandAndExecute(Shape s) {
        SelectedShapeManager.getSelectedShapeManager().setSelectedShape(s);
        invoker.executeCommand(new BringToFrontCommand(s, dw));
    }

}
