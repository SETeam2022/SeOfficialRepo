package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seproject.tools.SelectedShapeManager;

public class BringToFrontCommandTest {

    private SelectedShapeManager ssm;
    private Pane paper;
    private Rectangle rect;
    int beforeBringToFront, afterBringToFront, afterUndo;
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
     * Test of execute method, of class BringToFrontCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        beforeBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        cmd = new BringToFrontCommand(ssm.getSelectedShape(), paper);
        cmd.execute();  // level 0 -> level 1
        afterBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToFront > beforeBringToFront);
    }

    /**
     * Test of undo method, of class BringToFrontCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        beforeBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        cmd = new BringToFrontCommand(ssm.getSelectedShape(), paper);
        cmd.execute();  // level 0 -> level 1
        afterBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToFront > beforeBringToFront);
        
        // undo
        cmd.undo(); // level 1 -> level 0
        afterUndo = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterUndo == beforeBringToFront);
    }

}