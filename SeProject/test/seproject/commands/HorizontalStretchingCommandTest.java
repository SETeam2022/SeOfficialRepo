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
public class HorizontalStretchingCommandTest {
    
    private Rectangle rect;
    private HorizontalStretchingCommand cmd;
    private double prevStretchingValue, newStretchingValue;
    
    @Before
    public void setUp() {
        rect = new Rectangle();
        newStretchingValue = 2;
        prevStretchingValue = rect.getScaleX();
        cmd = new HorizontalStretchingCommand(rect,newStretchingValue);
    }

    /**
     * Test of execute method, of class HorizontalStretchingCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd.execute();
        assertEquals(newStretchingValue,rect.getScaleX(),0);
    }

    /**
     * Test of undo method, of class HorizontalStretchingCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        cmd.execute();
        assertEquals(newStretchingValue,rect.getScaleX(),0);
        cmd.undo();
        assertEquals(prevStretchingValue,rect.getScaleX(),0);

    }
    
}
