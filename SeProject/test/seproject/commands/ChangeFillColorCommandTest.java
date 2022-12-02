package seproject.commands;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChangeFillColorCommandTest {

    private ChangeFillColorCommand com;
    
    
    /**
     * Test of execute method, of class ChangeFillColorCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        Rectangle rect = new Rectangle();
        com = new ChangeFillColorCommand(Color.RED,rect.getFill(),rect);
        com.execute();
        assertEquals(rect.getFill(), Color.RED);
        
    }

    /**
     * Test of undo method, of class ChangeFillColorCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        Rectangle rect = new Rectangle();
        rect.setFill(Color.BLACK);
        com = new ChangeFillColorCommand(Color.RED,rect.getFill(),rect);
        com.execute();
        com.undo();
        assertEquals(rect.getFill(), Color.BLACK);
    }
    
}
