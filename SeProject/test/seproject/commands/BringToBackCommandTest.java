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
import seproject.customComponents.DrawingArea;
import seproject.Constants;
import seproject.tools.SelectedShapeManager;

public class BringToBackCommandTest {

    private SelectedShapeManager ssm;
    private SecureRandom random;
    private DrawingArea dw;
    private Pane paper;
    private Rectangle rect;
    private Ellipse ell;
    private Line line;
    private Invoker invoker;
    

    /*
     * This method instances a new pane and a series of shapes which will be used during the 
     * test of the bring to back functionality.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        this.dw = new DrawingArea(1920,1080);
        this.paper = dw.getPaper();
        this.rect = new Rectangle(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT), random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        this.ell = new Ellipse(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT), random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        this.line = new Line(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT), random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        SelectedShapeManager.setSelectedShapeManagerPaper(dw);
        this.ssm = SelectedShapeManager.getSelectedShapeManager();
        this.invoker = Invoker.getInvoker();
    }

    /*
     * Test of execute method, of class BringToBackCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        insertAndBringToBack();
    }

    /*
     * Test of undo method, of class BringToBackCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        insertAndBringToBack();

        /* Test 1: undo of the BringToBackCommand on the line */
        invoker.undoLastCommand();
        assertTrue(this.paper.getChildren().indexOf(this.line) > this.paper.getChildren().indexOf(this.ell));
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        
        /* Test 2: undo of the BringToBackCommand on the ellipse */
        invoker.undoLastCommand();
        assertTrue(this.paper.getChildren().indexOf(this.ell) > this.paper.getChildren().indexOf(this.rect));
        
        /* Test 3: undo of the BringToBackCommand on the rectangle */
        invoker.undoLastCommand();
        assertEquals(0, this.paper.getChildren().indexOf(this.rect), 0);
    }

    /*
     * Utility method to insert shapes and perform some bring to back operations on them
     * simulating the classic utilization flow of a user.
     */
    private void insertAndBringToBack() {
        /* Test 1: there's just one shape inside the pane */
        this.paper.getChildren().add(rect);
        createCommandAndExecute(this.rect);
        assertEquals(0, this.paper.getChildren().indexOf(this.ssm.getSelectedShape()), 0);
        
        /* Test 2: there are two shapes inside the pane */
        this.paper.getChildren().add(ell);
        createCommandAndExecute(this.ell);
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        
        /* Test 3: there are three shapes inside the pane */
        this.paper.getChildren().add(line);
        createCommandAndExecute(this.line);
        assertTrue(this.paper.getChildren().indexOf(this.rect) > this.paper.getChildren().indexOf(this.ell));
        assertTrue(this.paper.getChildren().indexOf(this.ell) > this.paper.getChildren().indexOf(this.line));
    }
    /**
     * This is a utility method to create and execute a command on the given shape.
     * @param s
     * @param cmd
     * @return BringToBackCommand
     */
    private void createCommandAndExecute(Shape s) {
        SelectedShapeManager.getSelectedShapeManager().setSelectedShape(s);
        invoker.executeCommand(new BringToBackCommand(s, this.dw));
    }

}