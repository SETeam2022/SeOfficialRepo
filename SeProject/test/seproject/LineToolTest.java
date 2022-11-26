package seproject;

import seproject.tools.LineTool;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LineToolTest {
    
    private Pane paper;
    private Line testShape;

    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private LineTool t;
    
    public LineToolTest() {
    }
    
    /**
     * This method create the Test environment, it creates a test line with
     * red stroke and black fill, and istances a line tool.
     */
    @Before
    public void setUp() {
        
        testShape = new Line(443,308,471,308);
        testShape.setStroke(Color.RED);
        testShape.setFill(Color.BLACK);
        paper = new Pane();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();        
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        t = new LineTool(paper,borderColorProperty,fillColorProperty);
        
    }
    
    /**
     * Simulate a mouse click and check if the line added to the paper is in the same start position of
     * a test line and has the same stroke and fill color attribute
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent e = new MouseEvent(  MouseEvent.MOUSE_CLICKED, testShape.getStartX(),testShape.getStartY(), 0, 0, MouseButton.PRIMARY, 1,
                                        true, true, true, true,true, true, true, true, true, true, null);
        t.onMousePressed(e);
        for (Node elem : paper.getChildren()){
             if (elem instanceof Line ){
                Line casted = (Line) elem;
                
                //Checking for positions
                Assert.assertEquals(casted.getStartX(),testShape.getStartX(),0);
                Assert.assertEquals(casted.getStartY(),testShape.getStartY(),0);
                // Checking for colors
                Assert.assertEquals(testShape.getStroke(),casted.getStroke());
                Assert.assertEquals(testShape.getFill(), casted.getFill());
             
             }
        }
        
    }
    
    /**
     * Simulate a mouse press and the drag. Checks if the line added to the paper  
     * has the same length (due to the drag effect)
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("mouseDragged");
        MouseEvent e2 = new MouseEvent(  MouseEvent.MOUSE_CLICKED, testShape.getStartX(),testShape.getStartY(), 0, 0, MouseButton.PRIMARY, 1,
                                        true, true, true, true,true, true, true, true, true, true, null);
        
        MouseEvent e = new MouseEvent(  MouseEvent.MOUSE_DRAGGED, testShape.getEndX(),testShape.getEndY(), 0, 0, MouseButton.PRIMARY, 1,
                                     true, true, true, true,true, true, true, true, true, true, null);
        t.onMousePressed(e2);
        t.onMouseDragged(e);
        for (Node elem : paper.getChildren()){
             if (elem instanceof Line ){
                Line casted = (Line) elem;
                Assert.assertEquals(testShape.getEndX(),casted.getEndX(),0);
                Assert.assertEquals(testShape.getEndY(),casted.getEndY(),0);
            }
        }
        
    }
    
}
