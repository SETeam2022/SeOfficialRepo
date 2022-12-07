/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject.tools;

import javafx.scene.input.MouseEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alewi
 */
public class TextToolTest {
    
    public TextToolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        MouseEvent event = null;
        TextTool instance = null;
        instance.onMousePressed(event);
        fail("The test case is a prototype.");
    }

    @Test
    public void testOnMouseDragged() {
        System.out.println("onMouseDragged");
        MouseEvent event = null;
        TextTool instance = null;
        instance.onMouseDragged(event);
        fail("The test case is a prototype.");
    }

    @Test
    public void testOnMouseReleased() {
        System.out.println("onMouseReleased");
        MouseEvent event = null;
        TextTool instance = null;
        instance.onMouseReleased(event);
        fail("The test case is a prototype.");
    }
    
}
