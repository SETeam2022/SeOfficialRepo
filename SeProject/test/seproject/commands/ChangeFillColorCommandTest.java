/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import junit.framework.Assert;
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
