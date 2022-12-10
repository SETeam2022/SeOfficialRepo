package seproject.commands;

import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MirrorHorizontalCommandTest {
    
    private Rectangle rect;
    private MirrorHorizontalCommand cmd;
    private double prevMirroring;
    
    @Before
    public void setUp() {
        rect = new Rectangle();
        cmd = new MirrorHorizontalCommand(rect);
        prevMirroring = rect.getScaleY();
    }

    /**
     * Test of execute method, of class MirrorHorizontalCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd.execute();
        assertEquals((-1*prevMirroring),rect.getScaleY(),0);
    }

    /**
     * Test of undo method, of class MirrorHorizontalCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        cmd.execute();
        assertEquals((-1*prevMirroring),rect.getScaleY(),0);
        cmd.undo();
        assertEquals(prevMirroring,rect.getScaleY(),0);
    }
    
}
