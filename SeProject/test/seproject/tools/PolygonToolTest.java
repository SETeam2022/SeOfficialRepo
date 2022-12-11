package seproject.tools;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.customComponents.DrawingArea;
import seproject.EventGenerator;
import seproject.Constants;

/**
 * This class is used to test the PolygonTool class.
 * 
 */
public class PolygonToolTest {
    
    private Pane paper;
    private DrawingArea dw;
    private Polyline testShape;
    private PolygonTool t;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private SecureRandom random;
    private List <Double> coordinates;
    private MouseEvent pressEvent;
    
    public PolygonToolTest() {
    }
    
    /**
     * This method sets up the configuration which is required
     * to test the PolygonTool class' methods.
     * A pane, a polyline (representing our tested shape) with its own 
     * characteristics (in terms of color and stroke) and 7 vertices (of
     * random generated coordinates), the properties for the color of 
     * the fill and the stroke of the shape respectively, and of course
     * a polygon tool, are created. 
     */
    @Before
    public void setUp() {
        paper = new Pane();
        
        this.random = new SecureRandom();
        dw = new DrawingArea(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        paper = dw.getPaper();
        
        testShape = new Polyline();
        coordinates = new ArrayList <> ();
        for(int i=0; i<Constants.NUM_VERTICES*2; i++) {
            if (i > 1 && i < (Constants.NUM_VERTICES*2)-2) {
                coordinates.add(random.nextDouble() + Constants.DELTA);
            }else{
                coordinates.add(random.nextDouble());
            }  
        }
        
        testShape.getPoints().addAll(coordinates);
        testShape.setFill(Color.PINK);
        testShape.setStroke(Color.VIOLET);
        
        fillColorProperty = new SimpleObjectProperty<>();
        fillColorProperty.set(Color.PINK);
        borderColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.VIOLET);
        
        t = new PolygonTool(dw, borderColorProperty, fillColorProperty);
        pressEvent = EventGenerator.PrimaryButtonMouseDragged(paper,paper,coordinates.get(0), coordinates.get(1));
    }

    /**
     * Test of onMousePressed method, of class PolygonTool.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        /* Test 1: first press on the paper */
        int i;
        paper.getChildren().clear();
        t.onMousePressed(pressEvent);
        Node node = (Node) paper.getChildren().get(0);
        assertTrue(node instanceof Polyline);
        Polyline polyInstance = (Polyline) node;
        assertEquals(testShape.getPoints().get(0), polyInstance.getPoints().get(0));
        assertEquals(testShape.getPoints().get(1), polyInstance.getPoints().get(1));
        checkColors(testShape,polyInstance);
        /* Test 2: More presses on the paper */
        polyInstance.getPoints().removeAll(coordinates);
        for (i = 0; i<Constants.NUM_VERTICES*2; i += 2) pressEvent = EventGenerator.PrimaryButtonMouseDragged(paper,paper,coordinates.get(i), coordinates.get(i+1));
        i = 0;
        for (Double d : polyInstance.getPoints()) {
            assertEquals(testShape.getPoints().get(i), d);
            i++;
        }
    }

    /**
     * Test of onMouseDragged method, of class PolygonTool.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("onMouseDragged");
        paper.getChildren().clear();
        t.onMousePressed(pressEvent);
        double width = testShape.getPoints().get(2), height = testShape.getPoints().get(3);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDragged(paper,paper,width,height));
        testEndOfEdge(testShape,width,height);
    }

    /**
     * Test of onMouseReleased method, of class PolygonTool.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("onMouseReleased");
        paper.getChildren().clear();
        t.onMousePressed(pressEvent);
        double width = testShape.getPoints().get(2), height = testShape.getPoints().get(3);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDragged(paper,paper,width,height));
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(paper, paper, width,height));
        testEndOfEdge(testShape,width,height);
    }
    
    /**
     * Utility method to check the if the colors of two shapes are the same.
     * 
     * @param actual
     * @param expected 
     */
    private void checkColors(Polyline expected, Polyline actual){
        assertEquals(expected.getFill(), actual.getFill());
        assertEquals(expected.getStroke(), actual.getStroke());
    }
    
    /**
     * Utility method to check if the coordinates of the last release or drag 
     * event correspond to the expected ones.
     * 
     * @param actual
     * @param width
     * @param height 
     */
    private void testEndOfEdge(Polyline actual, double width, double height){
        Node node = paper.getChildren().get(0);
        assertTrue(node instanceof Polyline);
        Polyline polyInstance = (Polyline) node;
        assertEquals(width, polyInstance.getPoints().get(2),0);
        assertEquals(height, polyInstance.getPoints().get(3),0);
        checkColors(actual,polyInstance);
    }
    
}
