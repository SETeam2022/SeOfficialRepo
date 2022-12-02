package seproject.tools;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        testPaper.getChildren().clear();
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
        selectedShapeManager.setSelectedShape(testShape);
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
        selectedShapeManager.setSelectedShape(expResult);

        Shape result = selectedShapeManager.getSelectedShape();
        assertEquals(expResult, result);
    }

    /**
     * Test of unsetSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testUnsetSelectedShape() {
        System.out.println("unsetSelectedShape");
        selectedShapeManager.setSelectedShape(testShape);
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
        selectedShapeManager.setSelectedShape(testShape);
        assertTrue("Called setSelectedShape but shapeIsSelectedProperty in selectShapeManager is not true", selectedShapeManager.getShapeIsSelectedProperty().get());

    }

    /**
     * Test of deleteSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testDeleteSelectedShape() {
        System.out.println("deleteSelectedShape");
        selectedShapeManager.setSelectedShape(testShape);
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
        selectedShapeManager.setSelectedShape(testShape);
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
        selectedShapeManager.setSelectedShape(testShape);
        selectedShapeManager.changeSelectedShapeStrokeColor(Color.MAGENTA);
        Paint result = selectedShapeManager.getSelectedShape().getStroke();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWidthProperty method, of class SelectedShapeManager.
     */
    @Test
    public void testGetWidthProperty() {
        System.out.println("getWidthProperty");
        Double expResult = 549.0;
        selectedShapeManager.getWidthProperty().set(549.0);
        Double result = selectedShapeManager.getWidthProperty().get();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeightProperty method, of class SelectedShapeManager.
     */
    @Test
    public void testGetHeightProperty() {
        System.out.println("getWidthProperty");
        Double expResult = 549.0;
        selectedShapeManager.getHeightProperty().set(549.0);
        Double result = selectedShapeManager.getHeightProperty().get();
        assertEquals(expResult, result);
    }

    /**
     * Test of copySelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testCopySelectedShape() {
        System.out.println("copySelectedShape");
        selectedShapeManager.setSelectedShape(testShape);
        selectedShapeManager.copySelectedShape();
        selectedShapeManager.pasteShape();
        Integer expectedNumberOfShapes = 2;
        Integer realNumberOfShapes = 0;
        for (Node n : testPaper.getChildren()) {
            if (n instanceof Ellipse) {
                Ellipse foundedEllipse = (Ellipse) n;
                Ellipse testEllipse = (Ellipse) testShape;
                realNumberOfShapes++;
                assertEquals(testEllipse.getCenterX(), foundedEllipse.getCenterX(), 0);
                assertEquals(testEllipse.getCenterY(), foundedEllipse.getCenterY(), 0);
                assertEquals(testEllipse.getRadiusX(), foundedEllipse.getRadiusX(), 0);
                assertEquals(testEllipse.getRadiusY(), foundedEllipse.getRadiusY(), 0);
            }
        }
        assertEquals(expectedNumberOfShapes, realNumberOfShapes);
    }

    /**
     * Test of pasteShape method, of class SelectedShapeManager.
     */
    @Test
    public void testPasteShape() {
        System.out.println("pasteShape");
        selectedShapeManager.setSelectedShape(testShape);
        selectedShapeManager.copySelectedShape();
        selectedShapeManager.pasteShape();
        Integer expectedNumberOfShapes = 2;
        Integer realNumberOfShapes = 0;
        for (Node n : testPaper.getChildren()) {
            if (n instanceof Ellipse) {
                Ellipse foundedEllipse = (Ellipse) n;
                Ellipse testEllipse = (Ellipse) testShape;
                realNumberOfShapes++;
                System.out.println(foundedEllipse);
                assertEquals(testEllipse.getCenterX(), foundedEllipse.getCenterX(), 0);
                assertEquals(testEllipse.getCenterY(), foundedEllipse.getCenterY(), 0);
                assertEquals(testEllipse.getRadiusX(), foundedEllipse.getRadiusX(), 0);
                assertEquals(testEllipse.getRadiusY(), foundedEllipse.getRadiusY(), 0);
            }
        }
        assertEquals(expectedNumberOfShapes, realNumberOfShapes);
    }

    /**
     * Test of cutShape method, of class SelectedShapeManager.
     */
    @Test
    public void testCutShape() {
        System.out.println("cutShape");
        selectedShapeManager.setSelectedShape(testShape);
        selectedShapeManager.cutShape();
        boolean flag = true;
        for (Node n : testPaper.getChildren()) {
            if (n.equals(testShape)) {
                flag = true;
            }
        }
        assertTrue("Called deleteSelectedShape method in a Pane where is only one Shape but it isn't removed from Pane.", flag);
        assertTrue("The shape is cutted but the ShapeIsCopiedProperty is false.", selectedShapeManager.getShapeIsCopiedProperty().get());

    }

    /**
     * Test of getShapeIsCopiedProperty method, of class SelectedShapeManager.
     */
    @Test
    public void testGetShapeIsCopiedProperty() {
        System.out.println("getShapeIsCopiedProperty");
        selectedShapeManager.setSelectedShape(testShape);
        selectedShapeManager.copySelectedShape();
        assertTrue(selectedShapeManager.getShapeIsCopiedProperty().get());
    }

    /**
     * Test of the resizeSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testResizeSelectedShape() {
        System.out.println("resizeSelectedShape");
    }

}
