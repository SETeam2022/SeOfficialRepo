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

    /**
     * This method instances a rectangle, a random number, prevStretchingValue and the HorizontalStretchingCommand.
     */
    @Before
    public void setUp() {
        rect = new Rectangle();
        random = new SecureRandom();
        newStretchingValue = random.nextInt(1000);
        prevStretchingValue = rect.getScaleY();
        cmd = new VerticalStretchingCommand(rect,newStretchingValue);
    }

    /**
     * This method execute the stretching with the new value and verify that new value is equal to shape's value.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd.execute();
        assertEquals(newStretchingValue,rect.getScaleY(),0);
    }

    /**
     * This method execute the undo stretching and verify that shape's value is equal to prevStretchingValue.
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
