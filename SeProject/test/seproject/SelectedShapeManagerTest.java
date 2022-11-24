/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bvs
 */
public class SelectedShapeManagerTest {
    
    private Pane paper;
    private EllipseTool ell;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private SelectedShapeManager ssm;
    
    
    
    @Before
    public void setUp() {
        // Dovrei creare una figura all'interno del pannello 
        paper = new Pane();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();        
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        //
        ell = new EllipseTool(paper,borderColorProperty,fillColorProperty);
        ssm = new SelectedShapeManager(paper,borderColorProperty,fillColorProperty);
    }

    /**
     * Test of onMousePressed method, of class SelectedShapeManager.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        
        ell.onMousePressed(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                0, 0, 0, MouseButton.PRIMARY, 1, 
                true, true, true, true,true, true, 
                true, true, true, true, null));
        ell.onMouseDragged(new MouseEvent(MouseEvent.MOUSE_DRAGGED, 10,10, 
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true,true, true, 
                true, true, true, true, null));
        
        ssm.onMousePressed(new MouseEvent(MouseEvent.MOUSE_CLICKED, 2,
                2, 0, 0, MouseButton.PRIMARY, 1, 
                true, true, true, true,true, true, 
                true, true, true, true, null));
        
        Ellipse selectedEllipse = (Ellipse)ssm.getSelectedShape();
                
        for (Node elem : paper.getChildren()){
            if (elem instanceof Ellipse ){
                Ellipse casted = (Ellipse) elem;
                // Checking for Positions
                Assert.assertEquals(casted.getCenterX(), 
                        selectedEllipse.getCenterX(), 0);
                Assert.assertEquals(casted.getCenterY(), 
                        selectedEllipse.getCenterY(), 0);
                // Checking for Color
                Assert.assertEquals(Color.RED, selectedEllipse.getStroke());
                Assert.assertEquals(Color.BLACK, selectedEllipse.getFill());
            }
        }
    }

    /**
     * Test of onMouseDragged method, of class SelectedShapeManager.
     */
    @Test
    public void testOnMouseDragged() {
        
    };
    
}
