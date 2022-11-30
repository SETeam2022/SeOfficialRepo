package seproject.tools;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alewi
 */
public class SelectedShapeManagerTest {

    private SelectedShapeManager selectedShapeManager;
    private static Pane testPaper;
    private Shape testShape;

    public SelectedShapeManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testPaper = new Pane();
        SelectedShapeManager.setSelectedShapeManagerPaper(testPaper);
    }

    @Before
    public void setUp() {
        selectedShapeManager = SelectedShapeManager.getSelectedShapeManager();
        testShape = new Ellipse();
        testPaper.getChildren().add(testShape);

    }

    /**
     * Test of getSelectedShapeManager method, of class SelectedShapeManager.
     */
    @Test
    public void testGetSelectedShapeManager() {
        System.out.println("getSelectedShapeManager");
        SelectedShapeManager expResult = selectedShapeManager;
        SelectedShapeManager result = SelectedShapeManager.getSelectedShapeManager();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSelectedShapeManagerPaper method, of class
     * SelectedShapeManager.
     */
    @Test
    public void testSetSelectedShapeManagerPaper() {
        System.out.println("setSelectedShapeManagerPaper");
    }

    /**
     * Test of getSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testGetSelectedShape() {
        System.out.println("getSelectedShape");
        Shape expResult = testShape;
        selectedShapeManager.setSelectedShape(testShape, new Rectangle());
        Shape result = selectedShapeManager.getSelectedShape();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testSetSelectedShape() {
        System.out.println("setSelectedShape");

        Shape expResult = testShape;
        selectedShapeManager.setSelectedShape(expResult, new Rectangle());

        Shape result = selectedShapeManager.getSelectedShape();
        assertEquals(expResult, result);
    }

    /**
     * Test of unsetSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testUnsetSelectedShape() {
        System.out.println("unsetSelectedShape");
        selectedShapeManager.setSelectedShape(testShape, new Rectangle());
        selectedShapeManager.unsetSelectedShape();
        assertNull("Called unsetSelectedShape but shape in selectShapeManager is not null", selectedShapeManager.getSelectedShape());
        assertFalse("Called unsetSelectedShape but shapeIsSelectedProperty in selectShapeManager is not false", selectedShapeManager.getShapeIsSelectedProperty().get());
    }

    /**
     * Test of getShapeIsSelectedProperty method, of class SelectedShapeManager.
     */
    @Test
    public void testGetShapeIsSelectedProperty() {
        System.out.println("getShapeIsSelectedProperty");
        selectedShapeManager.unsetSelectedShape();
        assertFalse("Called unsetSelectedShape but shapeIsSelectedProperty in selectShapeManager is not false", selectedShapeManager.getShapeIsSelectedProperty().get());
        selectedShapeManager.setSelectedShape(testShape, new Rectangle());
        assertTrue("Called setSelectedShape but shapeIsSelectedProperty in selectShapeManager is not true", selectedShapeManager.getShapeIsSelectedProperty().get());

    }

    /**
     * Test of deleteSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testDeleteSelectedShape() {
        System.out.println("deleteSelectedShape");
        selectedShapeManager.setSelectedShape(testShape, new Rectangle());
        selectedShapeManager.deleteSelectedShape();
        boolean flag = true;
        for (Node n : testPaper.getChildren()) {
            if (n.equals(testShape)) {
                flag = true;
            }
        }
        assertTrue("Called deleteSelectedShape method in a Pane where is only one Shape but it isn't removed from Pane.", flag);

    }

    /**
     * Test of changeSelectedShapeFillColor method, of class
     * SelectedShapeManager.
     */
    @Test
    public void testChangeSelectedShapeFillColor() {
        System.out.println("changeSelectedShapeFillColor");
        Paint expResult = (Paint) Color.MAGENTA;
        selectedShapeManager.setSelectedShape(testShape, new Rectangle());
        selectedShapeManager.changeSelectedShapeFillColor(Color.MAGENTA);
        Paint result = selectedShapeManager.getSelectedShape().getFill();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeSelectedShapeStrokeColor method, of class
     * SelectedShapeManager.
     */
    @Test
    public void testChangeSelectedShapeStrokeColor() {
        System.out.println("changeSelectedShapeStrokeColor");
        Paint expResult = (Paint) Color.MAGENTA;
        selectedShapeManager.setSelectedShape(testShape, new Rectangle());
        selectedShapeManager.changeSelectedShapeStrokeColor(Color.MAGENTA);
        Paint result = selectedShapeManager.getSelectedShape().getStroke();
        assertEquals(expResult, result);
    }

}
