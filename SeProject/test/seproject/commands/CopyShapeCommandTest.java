package seproject.commands;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.tools.SelectedShapeManager;

public class CopyShapeCommandTest {
    
    private Shape rect;
    private CopyShapeCommand cmd;
    
    /**
     * This method instances a Rectangle and a CopyShapeCommand. 
     */
    @Before
    public void setUp() {
        rect = new Rectangle(0,0,0,0);
        cmd = new CopyShapeCommand(rect);
    }
    
    
    /**
     * This method execute the copy and verify that the shape is equal to shape copied.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd.execute();
        assertEquals(SelectedShapeManager.getSelectedShapeManager().getCopiedShape(),rect);
    }

    /**
     * This method execute the copy and execute the undu, then verify that the shape copied is null.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        cmd.execute();
        cmd.undo();
        assertEquals(SelectedShapeManager.getSelectedShapeManager().getCopiedShape(),null);
    }
    
}
