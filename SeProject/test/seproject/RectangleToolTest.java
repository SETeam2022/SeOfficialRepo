/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject;

import seproject.tools.RectangleTool;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author teodo
 */
public class RectangleToolTest {
    
    private Pane paper;
    private Rectangle testShape;
    private RectangleTool t;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    
    public RectangleToolTest() {
    }
    
    
    /**
     * This methods create the Test environment, it makes a Blue rectangole (shape and fill)
     * and istances a Drawing Tool
     */
    @Before
    public void setUp() {
        testShape = new Rectangle(0,0,10,20);
        testShape.setStroke(Color.RED);
        testShape.setFill(Color.BLACK);
        
        paper = new Pane();
        
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();        
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        t = new RectangleTool(paper,borderColorProperty,fillColorProperty);
        
    }
    


    /**
     * Simulate a mouse click and check if the shape added to the paper is in the same position of
     * a test shape.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("mousePressed");
        
        MouseEvent e = new MouseEvent(MouseEvent.MOUSE_CLICKED, testShape.getX(),testShape.getY(), 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,true, true, true, true, true, true, null); 
        
        t.onMousePressed(e);
        
        for (Node elem : paper.getChildren()){
             if (elem instanceof Rectangle ){
                Rectangle casted = (Rectangle) elem;
                //Checking for Position
                Assert.assertEquals(casted.getX(),testShape.getX(),0);
                Assert.assertEquals(casted.getY(),testShape.getY(),0);
                //Checking for Color.
                Assert.assertEquals(testShape.getStroke(),casted.getStroke());
                Assert.assertEquals(testShape.getFill(), casted.getFill());
             }
        }
        
    }
    
    
    @Test
    public void testOnMouseDragged(){
        System.out.println("mouseDragged");
        MouseEvent e2 = new MouseEvent(MouseEvent.MOUSE_CLICKED, testShape.getX(),testShape.getY(), 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,true, true, true, true, true, true, null); 

        MouseEvent e = new MouseEvent(  MouseEvent.MOUSE_DRAGGED, testShape.getWidth(),testShape.getHeight(), 0, 0, MouseButton.PRIMARY, 1,
                                        true, true, true, true,true, true, true, true, true, true, null);
        t.onMousePressed(e2);
        t.onMouseDragged(e);
        for (Node elem : paper.getChildren()){
             if (elem instanceof Rectangle ){
                Rectangle casted = (Rectangle) elem;
                Assert.assertEquals(testShape.getWidth(),casted.getWidth(),0);
                Assert.assertEquals(testShape.getHeight(),casted.getHeight(),0);
            }
        }
    
        
    }



    
}
