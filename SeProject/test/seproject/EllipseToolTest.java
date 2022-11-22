/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author teodo
 */
public class EllipseToolTest {
    
    private Pane paper;
    private Ellipse testShape;
    private EllipseTool t;
    
    public EllipseToolTest() {
    }
    
    
    
        /**
     * This methods create the Test environment, it makes a Blue rectangole (shape and fill)
     * and istances an EllipseTool
     */
    @Before
    public void setUp() {
        testShape = new Ellipse(0,0);
        paper = new Pane();
        t = new EllipseTool(paper);
    }
    


    /**
     * Simulate a mouse click and check if the ellipse added to the paper is in the same position of
     * a test elipse.
     */
    @Test
    public void testMouseClick() {
        System.out.println("mouseClick");
        
        MouseEvent e = new MouseEvent(MouseEvent.MOUSE_CLICKED, testShape.getCenterX(),testShape.getCenterY(), 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,true, true, true, true, true, true, null); 
        
        t.mouseClick(e);
        
        for (Node elem : paper.getChildren()){
             if (elem instanceof Ellipse ){
                Ellipse casted = (Ellipse) elem;
                Assert.assertEquals(casted.getCenterX(),testShape.getCenterX(),0);
                Assert.assertEquals(casted.getCenterY(),testShape.getCenterY(),0);
            }
        }
        
    }
    
}
