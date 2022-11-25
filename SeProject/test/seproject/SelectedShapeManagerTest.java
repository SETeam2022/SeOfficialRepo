package seproject;

import seproject.tools.SelectedShapeManager;
import seproject.tools.EllipseTool;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SelectedShapeManagerTest {

    private Pane paper;
    private EllipseTool ell;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private SelectedShapeManager ssm;
    private Ellipse instancedEllipse;
    @Before
    public void setUp() {
        //Set up of a temporary pane and a temporary drawing tool for an ellipse
        paper = new Pane();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        ell = new EllipseTool(paper, borderColorProperty, fillColorProperty);
        // Creation of the SelectedShapeManager under test
        ssm = new SelectedShapeManager(paper, borderColorProperty, fillColorProperty);

        // We draw an ellipse on the pane so that we can test the selection
        // and all methods on it
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
     * Test of onMousePressed method, of class SelectedShapeManager.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        
        ssm.onMousePressed(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));

        Ellipse selectedEllipse = (Ellipse) ssm.getSelectedShape();
        
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
     * Test of onMouseDragged method, of class SelectedShapeManager.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("onMouseDragged");
        // Selecting a shape
        ssm.onMousePressed(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        //Dragging the selected shape
        ssm.onMouseDragged(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_DRAGGED, 40, 40,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));

        Ellipse selectedEllipse = (Ellipse) ssm.getSelectedShape();

        for (Node elem : paper.getChildren()) {
            if (elem instanceof Ellipse) {
                Ellipse casted = (Ellipse) elem;
                Bounds newShape = casted.getBoundsInParent();
                Bounds selectedShape = selectedEllipse.getBoundsInParent();
                Assert.assertEquals(newShape, selectedShape);
            }
        }

    }

    ;
    
    /*
    * This test simulate a selection of a shape and try to delete it, it verify
    * if a shape is selected and if after deletion the selectedShape is effectivly
    * null.
    */
    @Test
    public void testDeleteSelectedShape() {
        System.out.println("deleteSelectedShape");
        // try to delete a selected shape, it first select something
        ssm.onMousePressed(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));

        Assert.assertNotNull(ssm.getSelectedShape());
        ssm.deleteSelectedShape();
        Assert.assertNull(ssm.getSelectedShape());

    }

}
