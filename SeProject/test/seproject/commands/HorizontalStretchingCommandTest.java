package seproject.commands;

import java.security.SecureRandom;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HorizontalStretchingCommandTest {
    
    private Rectangle rect;
    private HorizontalStretchingCommand cmd;
    private double prevStretchingValue, newStretchingValue;
    private SecureRandom secure;
    
    @Before
    public void setUp() {
        rect = new Rectangle();
        secure = new SecureRandom();
        newStretchingValue = secure.nextInt(100);
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
