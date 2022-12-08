/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject.commands;

import javafx.scene.shape.Rectangle;
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
