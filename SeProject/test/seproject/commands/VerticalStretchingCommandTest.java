package seproject.commands;

import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bvs
 */
public class VerticalStretchingCommandTest {
    
    private Rectangle rect;
    private VerticalStretchingCommand cmd;
    private double prevStretchingValue, newStretchingValue;
    
    @Before
    public void setUp() {
        rect = new Rectangle();
        newStretchingValue = 2;
        prevStretchingValue = rect.getScaleY();
        cmd = new VerticalStretchingCommand(rect,newStretchingValue);
    }

    /**
     * Test of execute method, of class HorizontalStretchingCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd.execute();
        assertEquals(newStretchingValue,rect.getScaleY(),0);
    }

    /**
     * Test of undo method, of class HorizontalStretchingCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        cmd.execute();
        assertEquals(newStretchingValue,rect.getScaleY(),0);
        cmd.undo();
        assertEquals(prevStretchingValue,rect.getScaleY(),0);
    }
    
}