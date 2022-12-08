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
import seproject.DrawingArea;
import seproject.TestConstants;
import seproject.tools.SelectedShapeManager;

public class BringToFrontCommandTest {

    private SelectedShapeManager ssm;
    private SecureRandom random;
    private Pane paper;
    private Rectangle rect;
    private Ellipse ell;
    private Line line;
    private BringToFrontCommand cmdRect, cmdLine, cmdEll;
    private DrawingArea dw;

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
        this.cmdLine.undo();
        assertTrue(this.paper.getChildren().indexOf(this.line) > this.paper.getChildren().indexOf(this.rect));
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        
        /* Test 2: undo of the BringToFrontCommand on the ellipse */
        this.cmdEll.undo();
        assertTrue(this.paper.getChildren().indexOf(this.ell) > this.paper.getChildren().indexOf(this.rect));
        
        /* Test 3: undo of the BringToFrontCommand on the rectangle */
        this.cmdRect.undo();
        assertEquals(0, this.paper.getChildren().indexOf(this.rect), 0);
    }
    
    /**
     * Utility method to insert shapes and perform some bring to front operations on them
     * simulating the classic utilization flow of a user.
     */
    private void insertAndBringToFront() {
        this.paper.getChildren().clear();
        
        /* Test 1: there's just one shape inside the pane */
        this.paper.getChildren().add(rect);
        this.cmdRect = createCommandAndExecute(this.rect, this.cmdRect);
        assertEquals(0, this.paper.getChildren().indexOf(this.ssm.getSelectedShape()), 0);
        
        /* Test 2: there are two shapes inside the pane */
        this.paper.getChildren().add(ell);
        this.cmdEll = createCommandAndExecute(this.rect, this.cmdEll);
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        
        /* Test 3: there are three shapes inside the pane */
        this.paper.getChildren().add(line);
        this.cmdLine = createCommandAndExecute(this.ell, this.cmdLine);
        assertTrue(this.paper.getChildren().indexOf(this.ell) > this.paper.getChildren().indexOf(this.line));
        assertTrue(this.paper.getChildren().indexOf(this.line) > this.paper.getChildren().indexOf(this.rect));
    }

    /**
     * This is a utility method to create and execute a command on the given shape.
     * @param s
     * @param cmd
     * @return BringToFrontCommand
     */
    private BringToFrontCommand createCommandAndExecute(Shape s, BringToFrontCommand cmd) {
        SelectedShapeManager.getSelectedShapeManager().setSelectedShape(s);
        cmd = new BringToFrontCommand(s, dw);
        cmd.execute();
        return cmd;
    }

}
