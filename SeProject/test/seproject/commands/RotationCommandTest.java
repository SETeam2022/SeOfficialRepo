package seproject.commands;

import java.security.SecureRandom;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RotationCommandTest {
    
    private Rectangle rect;
    private RotationCommand cmd;
    private double startRotate, valueRotate;
    private SecureRandom random;
    
    @Before
    public void setUp() {
        rect = new Rectangle();
        startRotate = rect.getRotate();
        random = new SecureRandom();
        valueRotate = random.nextInt(361);
        cmd = new RotationCommand(valueRotate, rect);
    }

    /**
     * Test of execute method, of class RotationCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd.execute();
        assertEquals(valueRotate,rect.getRotate(),0);  
    }

    /**
     * Test of undo method, of class RotationCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        cmd.execute();
        assertEquals(valueRotate,rect.getRotate(),0);
        cmd.undo();
        assertEquals(startRotate,rect.getRotate(),0);  
    }
    
}
