package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.tools.SelectedShapeManager;

public class BringToBackCommandTest {

    private SelectedShapeManager ssm;
    private Pane paper;
    private Rectangle rect;
    int beforeBringToFront, afterBringToFront, afterUndo, afterBringToBack,beforeBringToBack;
    private Command cmd;

    @Before
    public void setUp() {
        paper = new Pane();
        rect = new Rectangle(10, 10, 10, 10);
        paper.getChildren().clear();
        paper.getChildren().add(rect);
        SelectedShapeManager.setSelectedShapeManagerPaper(paper);
        ssm = SelectedShapeManager.getSelectedShapeManager();
        ssm.setSelectedShape(rect);

    }

    /**
     * Test of execute method, of class BringToBackCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        beforeBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        cmd = new BringToFrontCommand(ssm.getSelectedShape(), paper);
        cmd.execute(); // bring to front  level 0 -> level 1
        afterBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToFront > beforeBringToFront);
        cmd = new BringToBackCommand(ssm.getSelectedShape(), paper);
        cmd.execute(); // bring to back    level 1 -> level 0
        afterBringToBack = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToBack == 0);
    }

    /**
     * Test of undo method, of class BringToBackCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        beforeBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        cmd = new BringToFrontCommand(ssm.getSelectedShape(), paper);
        cmd.execute();   // bring to front  level 0 -> level 1
        afterBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToFront > beforeBringToFront);
        beforeBringToBack = afterBringToFront;
        cmd = new BringToBackCommand(ssm.getSelectedShape(), paper);
        cmd.execute();  // bring to back    level 1 -> level 0
        afterBringToBack = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToBack < afterBringToFront);
        
        // undo
        cmd.undo();     // undo bring to back    level 0 -> level 1
        afterUndo = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterUndo == beforeBringToBack);

    }

}
