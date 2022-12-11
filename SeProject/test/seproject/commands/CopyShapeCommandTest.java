/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject.commands;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.tools.SelectedShapeManager;

/**
 *
 * @author bvs
 */
public class CopyShapeCommandTest {
    
    private Shape rect;
    private CopyShapeCommand cmd;
    
    @Before
    public void setUp() {
        rect = new Rectangle(0,0,0,0);
        cmd = new CopyShapeCommand(rect);
    }
    
    
    /**
     * Test of execute method, of class CopyShapeCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd.execute();
        assertEquals(SelectedShapeManager.getSelectedShapeManager().getCopiedShape(),rect);
    }

    /**
     * Test of undo method, of class CopyShapeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        cmd.undo();
        assertEquals(SelectedShapeManager.getSelectedShapeManager().getCopiedShape(),null);
    }
    
}
