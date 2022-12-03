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
import seproject.tools.SelectedShapeManager;

public class BringToBackCommandTest {

    private static final int MAX_VALUE = 10000;
    private SelectedShapeManager ssm;
    private SecureRandom random;
    private Pane paper;
    private Rectangle rect;
    private Ellipse ell;
    private Line line;
    private BringToBackCommand cmdRect, cmdLine, cmdEll;

    /**
     * This method instances a new pane and a series of shape which will be used during the 
     * test of the bring to back functionality.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        this.paper = new Pane();
        this.rect = new Rectangle(random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE));
        this.ell = new Ellipse(random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE));
        this.line = new Line(random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE), random.nextInt(MAX_VALUE));
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
        assertEquals(2, this.paper.getChildren().indexOf(this.line), 0);
        assertEquals(0, this.paper.getChildren().indexOf(this.ell), 0);
        assertEquals(1, this.paper.getChildren().indexOf(this.rect), 0);
        /* Test 2: undo of the BringToBackCommand on the ellipse */
        this.cmdEll.undo();
        assertEquals(1, this.paper.getChildren().indexOf(this.ell), 0);
        assertEquals(0, this.paper.getChildren().indexOf(this.rect), 0);
        /* Test 3: undo of the BringToBackCommand on the rectangle */
        this.cmdLine.undo();
        assertEquals(0, this.paper.getChildren().indexOf(this.rect), 0);
    }

    /**
     * Utility method to insert shapes and perform some bring to back operations on them
     * simulating the classic utilization flow of a user.
     */
    private void insertAndBringToBack() {
        this.paper.getChildren().clear();
        
        /* Test 1: there's just one shape inside the pane */
        this.paper.getChildren().add(rect);
        this.cmdRect = createCommandAndExecute(this.rect, this.cmdRect);
        assertEquals(0, this.paper.getChildren().indexOf(this.ssm.getSelectedShape()), 0);
        
        /* Test 2: there are two shapes inside the pane */
        this.paper.getChildren().add(ell);
        this.cmdEll = createCommandAndExecute(this.ell, this.cmdEll);
        assertEquals(0, this.paper.getChildren().indexOf(this.ell), 0);
        assertEquals(1, this.paper.getChildren().indexOf(this.rect), 0);
        
        /* Test 3: there are three shapes inside the pane */
        this.paper.getChildren().add(line);
        this.cmdLine = createCommandAndExecute(this.line, this.cmdLine);
        assertEquals(0, this.paper.getChildren().indexOf(this.line), 0);
        assertEquals(1, this.paper.getChildren().indexOf(this.ell), 0);
        assertEquals(2, this.paper.getChildren().indexOf(this.rect), 0);
    }

    private BringToBackCommand createCommandAndExecute(Shape s, BringToBackCommand cmd) {
        SelectedShapeManager.getSelectedShapeManager().setSelectedShape(s);
        cmd = new BringToBackCommand(s, this.paper);
        cmd.execute();
        return cmd;
    }

}
