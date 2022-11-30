/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
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
import seproject.tools.EllipseTool;
import seproject.tools.SelectedShapeManager;
import seproject.tools.SelectionTool;

/**
 *
 * @author bvs
 */
public class SelectionToolTest {
    
    private Pane paper;
    private EllipseTool ell;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private SelectionTool st;
    private Ellipse instancedEllipse;
    
    @Before
    public void setUp() {
        paper = new Pane();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        ell = new EllipseTool(paper, borderColorProperty, fillColorProperty);
        
        st = new SelectionTool(paper);
        
        ell.onMousePressed(new MouseEvent(paper,paper,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        
        ell.onMouseDragged(new MouseEvent(paper,paper,MouseEvent.MOUSE_DRAGGED, 200, 300,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        Node instancedNode = paper.getChildren().get(0);
        Assert.assertTrue("Instanced Node is not an Ellipse", instancedNode instanceof Ellipse);
        instancedEllipse = (Ellipse) instancedNode;
        
        
    }
    

    /**
     * Test of onMousePressed method, of class SelectionTool.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        st.onMousePressed(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));

        Ellipse selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();
        
        for (Node elem : paper.getChildren()) {
            
            if (elem instanceof Ellipse) {
               
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
     * Test of onMouseDragged method, of class SelectionTool.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("onMouseDragged");
        st.onMousePressed(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        //Dragging the selected shape
        st.onMouseDragged(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_DRAGGED, 40, 40,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));

        Ellipse selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();

        for (Node elem : paper.getChildren()) {
            if (elem instanceof Ellipse) {
                Ellipse casted = (Ellipse) elem;
                Bounds newShape = casted.getBoundsInParent();
                Bounds selectedShape = selectedEllipse.getBoundsInParent();
                Assert.assertEquals(newShape, selectedShape);
            }
        }
    }
    
}
