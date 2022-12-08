/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teodo
 */
public class DrawingAreaTest {
    
    DrawingArea d;
    
    public DrawingAreaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       d = new DrawingArea(1920,1080);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of redrawGrid method, of class DrawingArea.
     */
    @Test
    public void testRedrawGrid() {
        System.out.println("redrawGrid");
    }

    /**
     * Test of showGrid method, of class DrawingArea.
     */
    @Test
    public void testShowGrid() {
        System.out.println("showGrid");
        boolean val = false;
        DrawingArea instance = null;
        instance.showGrid(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContainerOfPaperAndGrid method, of class DrawingArea.
     */
    @Test
    public void testGetContainerOfPaperAndGrid() {
        System.out.println("getContainerOfPaperAndGrid");
        DrawingArea instance = null;
        Group expResult = null;
        Group result = instance.getContainerOfPaperAndGrid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPaper method, of class DrawingArea.
     */
    @Test
    public void testGetPaper() {
        System.out.println("getPaper");
        DrawingArea instance = null;
        Pane expResult = null;
        Pane result = instance.getPaper();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPaperWidth method, of class DrawingArea.
     */
    @Test
    public void testSetPaperWidth() {
        System.out.println("setPaperWidth");
        double width = 0.0;
        DrawingArea instance = null;
        instance.setPaperWidth(width);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPaperHeight method, of class DrawingArea.
     */
    @Test
    public void testSetPaperHeight() {
        System.out.println("setPaperHeight");
        double height = 0.0;
        DrawingArea instance = null;
        instance.setPaperHeight(height);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addShape method, of class DrawingArea.
     */
    @Test
    public void testAddShape() {
        System.out.println("addShape");
        Shape shape = null;
        DrawingArea instance = null;
        instance.addShape(shape);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeShape method, of class DrawingArea.
     */
    @Test
    public void testRemoveShape() {
        System.out.println("removeShape");
        Shape shape = null;
        DrawingArea instance = null;
        instance.removeShape(shape);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
