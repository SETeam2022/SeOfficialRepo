package seproject.commands;

import java.security.SecureRandom;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.DrawingArea;
import seproject.TestConstants;
import seproject.tools.SelectedShapeManager;

public class BringToBackCommandTest {

    private SelectedShapeManager ssm;
    private SecureRandom random;
    private DrawingArea paper;
    private Rectangle rect;
    private Ellipse ell;
    private Line line;
    private BringToBackCommand cmdRect, cmdLine, cmdEll;

    /**
     * This method instances a new pane and a series of shapes which will be used during the 
     * test of the bring to back functionality.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        this.paper = new DrawingArea(1920,1080);
        this.rect = new Rectangle(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        this.ell = new Ellipse(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        this.line = new Line(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT), random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        SelectedShapeManager.setSelectedShapeManagerPaper(this.paper);
        this.ssm = SelectedShapeManager.getSelectedShapeManager();
    }

    /**
     * Test of execute method, of class BringToBackCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        insertAndBringToBack();
    }

    /**
     * Test of undo method, of class BringToBackCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        insertAndBringToBack();
        
        /* Test 1: undo of the BringToBackCommand on the line */
        this.cmdLine.undo();
        assertTrue(this.paper.getPaper().getChildren().indexOf(this.line) > this.paper.getPaper().getChildren().indexOf(this.ell));
        assertTrue(this.paper.getPaper().getChildren().indexOf(this.rect) > this.paper.getPaper().getChildren().indexOf(this.ell));
        
        /* Test 2: undo of the BringToBackCommand on the ellipse */
        this.cmdEll.undo();
        assertTrue(this.paper.getPaper().getChildren().indexOf(this.ell) > this.paper.getPaper().getChildren().indexOf(this.rect));
        
        /* Test 3: undo of the BringToBackCommand on the rectangle */
        this.cmdRect.undo();
        assertEquals(0, this.paper.getPaper().getChildren().indexOf(this.rect), 0);
    }

    /**
     * Utility method to insert shapes and perform some bring to back operations on them
     * simulating the classic utilization flow of a user.
     */
    private void insertAndBringToBack() {
        /* Test 1: there's just one shape inside the pane */
        this.paper.getChildren().add(rect);
        this.cmdRect = createCommandAndExecute(this.rect, this.cmdRect);
        assertEquals(0, this.paper.getChildren().indexOf(this.ssm.getSelectedShape()), 0);
        
        /* Test 2: there are two shapes inside the pane */
        this.paper.getChildren().add(ell);
        this.cmdEll = createCommandAndExecute(this.ell, this.cmdEll);
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        
        /* Test 3: there are three shapes inside the pane */
        this.paper.getChildren().add(line);
        this.cmdLine = createCommandAndExecute(this.line, this.cmdLine);
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        assertTrue(this.paper.getChildren().indexOf(this.ell) > this.paper.getChildren().indexOf(this.line));
    }

    /**
     * This is a utility method to create and execute a command on the given shape.
     * @param s
     * @param cmd
     * @return BringToBackCommand
     */
    private BringToBackCommand createCommandAndExecute(Shape s, BringToBackCommand cmd) {
        SelectedShapeManager.getSelectedShapeManager().setSelectedShape(s);
        cmd = new BringToBackCommand(s, this.paper);
        cmd.execute();
        return cmd;
    }

}
