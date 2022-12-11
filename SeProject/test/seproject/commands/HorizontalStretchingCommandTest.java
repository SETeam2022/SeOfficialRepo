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
    private SecureRandom random;
    
    @Before
    public void setUp() {
        rect = new Rectangle();
        random = new SecureRandom();
        newStretchingValue = random.nextInt(1000);
        prevStretchingValue = rect.getScaleX();
        cmd = new HorizontalStretchingCommand(rect,newStretchingValue);
    }

    /**
     * This method strrtch the shape and verify that it's effectively changed 
     * after the execution of the command
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd.execute();
        assertEquals(newStretchingValue,rect.getScaleX(),0);
    }

    /**
     * This method verify that the stetch is reported to the original sizes
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
