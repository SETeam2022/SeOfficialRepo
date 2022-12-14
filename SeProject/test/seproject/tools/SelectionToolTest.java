package seproject.tools;

import java.security.SecureRandom;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seproject.customComponents.DrawingArea;
import seproject.EventGenerator;
import seproject.Constants;

public class SelectionToolTest {

    private Pane paper;
    private DrawingArea dw;
    private EllipseTool ell;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private SelectionTool st;
    private Ellipse instancedEllipse;
    private SecureRandom random;

    @Before
    public void setUp() {

        this.random = new SecureRandom();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        dw = new DrawingArea(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        paper = dw.getPaper();
        ell = new EllipseTool(dw, borderColorProperty, fillColorProperty);
        st = new SelectionTool(dw,dw.scaleXProperty(),dw.scaleYProperty());
        SelectedShapeManager.setSelectedShapeManagerPaper(dw);
        
        ell.onMousePressed(EventGenerator.PrimaryButtonMousePressed(paper, paper,100, 200));
        ell.onMouseDragged(EventGenerator.PrimaryButtonMouseDragged(paper,paper,200,300));
        
        instancedEllipse = (Ellipse) paper.getChildren().get(0);
        
    }

    /**
     * Test of onMousePressed method, of class SelectionTool.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        st.onMousePressed(EventGenerator.PrimaryButtonMousePressed(paper, instancedEllipse,100, 200));
        Ellipse selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Ellipse);
        Ellipse casted = (Ellipse) elem;
        // Checking for Positions
        Assert.assertEquals(casted.getCenterX(),selectedEllipse.getCenterX(), 0);
        Assert.assertEquals(casted.getCenterY(),selectedEllipse.getCenterY(), 0);
        // Checking for Color
        Assert.assertEquals(Color.RED, selectedEllipse.getStroke());
        Assert.assertEquals(Color.BLACK, selectedEllipse.getFill());

     
    }

    /**
     * Test of onMouseDragged method, of class SelectionTool.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("onMouseDragged");
        //Clicking on the shape
        st.onMousePressed(EventGenerator.PrimaryButtonMousePressed(paper, instancedEllipse,100, 200));
        //Dragging the selected shape
        st.onMouseDragged(EventGenerator.PrimaryButtonMouseDragged(paper, instancedEllipse,40, 40));
        //Getting the selected shape
        Ellipse selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();        
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Ellipse);
        Ellipse casted = (Ellipse) elem;
        Bounds newShape = casted.getBoundsInParent();
        Bounds selectedShape = selectedEllipse.getBoundsInParent();
        Assert.assertEquals(newShape, selectedShape);        
    }

    /**
     * Test of onMouseReleased method, of class SelectionTool.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("onMouseReleased");
        //Clicking on the shape
        st.onMousePressed(EventGenerator.PrimaryButtonMousePressed(paper, instancedEllipse,100, 200));
        //Release the mouse in another position
        st.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(paper,instancedEllipse, 40, 10));
        //Getting the clicked shape
        Ellipse selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Ellipse);
        Ellipse casted = (Ellipse) elem;
        Bounds newShape = casted.getBoundsInParent();
        Bounds selectedShape = selectedEllipse.getBoundsInParent();
        Assert.assertEquals(newShape, selectedShape);   
    }

}
