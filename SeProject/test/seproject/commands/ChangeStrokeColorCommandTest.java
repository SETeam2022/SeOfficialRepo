package seproject.commands;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChangeStrokeColorCommandTest {
    
    private ChangeStrokeColorCommand com;
    
    
    /**
     * Test of execute method, of class ChangeFillColorCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        Rectangle rect = new Rectangle();
        com = new ChangeStrokeColorCommand(Color.RED,rect);
        com.execute();
        assertEquals(rect.getStroke(), Color.RED);
        
    }

    /**
     * Test of undo method, of class ChangeFillColorCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        Rectangle rect = new Rectangle();
        rect.setStroke(Color.BLACK);
        com = new ChangeStrokeColorCommand(Color.RED,rect);
        com.execute();
        com.undo();
        assertEquals(rect.getStroke(), Color.BLACK);
    }
    
}
