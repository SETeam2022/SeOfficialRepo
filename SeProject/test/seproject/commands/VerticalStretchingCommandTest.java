package seproject.commands;

import java.security.SecureRandom;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VerticalStretchingCommandTest {
    
    private Rectangle rect;
    private VerticalStretchingCommand cmd;
    private double prevStretchingValue, newStretchingValue;
    private SecureRandom random;

    
    @Before
    public void setUp() {
        rect = new Rectangle();
        random = new SecureRandom();
        newStretchingValue = random.nextInt(1000);
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
