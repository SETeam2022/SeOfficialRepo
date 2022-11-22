/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alewi
 */
public class LineToolTest {
    
    private Pane paper;
    private Line testShape;
    private LineTool t;
    
    public LineToolTest() {
    }
    
    
    @Before
    public void setUp() {
        testShape = new Line(2,2,10,10);
        paper = new Pane();
        t = new LineTool(paper);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testMousePressed() {
        MouseEvent e = new MouseEvent(  MouseEvent.MOUSE_CLICKED, testShape.getStartX(),testShape.getStartY(), 0, 0, MouseButton.PRIMARY, 1,
                                        true, true, true, true,true, true, true, true, true, true, null);
        t.mousePressed(e);
        for (Node elem : paper.getChildren()){
             if (elem instanceof Line ){
                Line casted = (Line) elem;
                Assert.assertEquals(casted.getStartX(),testShape.getStartX(),0);
                Assert.assertEquals(casted.getStartY(),testShape.getStartY(),0);
            }
        }
        
    }
    
    @Test
    public void testOnMouseDragged() {
                MouseEvent e = new MouseEvent(  MouseEvent.MOUSE_DRAGGED, testShape.getEndX(),testShape.getEndY(), 0, 0, MouseButton.PRIMARY, 1,
                                        true, true, true, true,true, true, true, true, true, true, null);
        t.mousePressed(e);
        for (Node elem : paper.getChildren()){
             if (elem instanceof Line ){
                Line casted = (Line) elem;
                Assert.assertEquals(casted.getEndX(),testShape.getEndX(),0);
                Assert.assertEquals(casted.getEndY(),testShape.getEndY(),0);
            }
        }
        
    }
    
}
