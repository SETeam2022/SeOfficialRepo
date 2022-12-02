/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject.commands;

import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teodoroadinolfi
 */
public class ResizeCommandTest {
    
    private ResizeCommand resize;
    private Rectangle testShape;
    private double previousWidth;
    private double previousHeight;
    private double newWidth;
    private double newHeight;
    
    public ResizeCommandTest() {
    }
    
    @Before
    public void setUp() {
        testShape = new Rectangle();
        previousWidth = 500;
        previousHeight = 500;
        newWidth = 1000;
        newHeight = 1000;
        testShape.setWidth(previousWidth);
        testShape.setHeight(previousHeight);
        resize = new ResizeCommand(testShape,newWidth,newHeight);
    }
    

    /**
     * Test of execute method, of class ResizeCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("Execute");
        resize.execute();
        assertEquals(testShape.getWidth(),newWidth,0);
        assertEquals(testShape.getHeight(),newHeight,0);
    }

    /**
     * Test of undo method, of class ResizeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        resize.execute();
        resize.undo();
        assertEquals(testShape.getWidth(),previousWidth,0);
        assertEquals(testShape.getHeight(),previousHeight,0);
    }
    
}
