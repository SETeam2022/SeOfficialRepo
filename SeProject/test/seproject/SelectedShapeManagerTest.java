package seproject;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import seproject.tools.SelectedShapeManager;
import static org.junit.Assert.*;
import seproject.tools.EllipseTool;

/**
 *
 * @author alewi
 */
public class SelectedShapeManagerTest {
    private SelectedShapeManager selectedShapeManager;
    private static Pane testPaper;
    private Shape testShape;
    private EllipseTool ell;
    private Ellipse instancedEllipse;

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
        SelectedShapeManager instance = null;
        Shape expResult = null;
        Shape result = instance.getSelectedShape();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testSetSelectedShape() {
        System.out.println("setSelectedShape");
        Shape selectedShape = null;
        SelectedShapeManager instance = null;
        instance.setSelectedShape(selectedShape);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unsetSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testUnsetSelectedShape() {
        System.out.println("unsetSelectedShape");
        SelectedShapeManager instance = null;
        instance.unsetSelectedShape();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShapeIsSelectedProperty method, of class SelectedShapeManager.
     */
    @Test
    public void testGetShapeIsSelectedProperty() {
        System.out.println("getShapeIsSelectedProperty");
        SelectedShapeManager instance = null;
        SimpleBooleanProperty expResult = null;
        SimpleBooleanProperty result = instance.getShapeIsSelectedProperty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testDeleteSelectedShape() {
        System.out.println("deleteSelectedShape");
        SelectedShapeManager instance = null;
        instance.deleteSelectedShape();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeSelectedShapeFillColor method, of class
     * SelectedShapeManager.
     */
    @Test
    public void testChangeSelectedShapeFillColor() {
        System.out.println("changeSelectedShapeFillColor");
        Color color = null;
        SelectedShapeManager instance = null;
        instance.changeSelectedShapeFillColor(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeSelectedShapeStrokeColor method, of class
     * SelectedShapeManager.
     */
    @Test
    public void testChangeSelectedShapeStrokeColor() {
        System.out.println("changeSelectedShapeStrokeColor");
        Color color = null;
        SelectedShapeManager instance = null;
        instance.changeSelectedShapeStrokeColor(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
