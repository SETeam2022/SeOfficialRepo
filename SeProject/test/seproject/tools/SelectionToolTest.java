package seproject.tools;

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
        SelectedShapeManager.setSelectedShapeManagerPaper(paper);
        
        ell.onMousePressed(new MouseEvent(paper,paper,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        
        ell.onMouseDragged(new MouseEvent(paper,paper,MouseEvent.MOUSE_DRAGGED, 200, 300,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        
        for (Node node : paper.getChildren()){
            if (node instanceof Ellipse){
                instancedEllipse = (Ellipse) node;
                break;
            }
        }
        
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
    
    @Test
    public void testOnMouseReleased() {
        System.out.println("onMouseReleased");
        st.onMousePressed(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        //Dragging the selected shape
        st.onMouseReleased(new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_RELEASED, 40, 40,
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
