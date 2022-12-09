package seproject.tools;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import java.security.SecureRandom;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.customComponents.DrawingArea;
import seproject.TestConstants;

public class SelectedShapeManagerTest {

    private SelectedShapeManager selectedShapeManager;
    private Shape testShape, testShape2, testShape3;
    private SecureRandom random;
    private Pane testPaper;
    private DrawingArea dw;

    public SelectedShapeManagerTest() {
    }

    @Before
    public void setUp() {
        selectedShapeManager = SelectedShapeManager.getSelectedShapeManager();
        this.random = new SecureRandom();
        this.dw = new DrawingArea(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        this.testPaper = dw.getPaper();
        SelectedShapeManager.setSelectedShapeManagerPaper(dw);
        testShape = new Ellipse();
        testShape2 = new Rectangle();
        testShape3 = new Line();
        this.random = new SecureRandom();
        testPaper.getChildren().clear();
        testPaper.getChildren().addAll(testShape, testShape2, testShape3);

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
        assertTrue("Called deleteSelectedShape method in a Pane where is only one Shape but it isn't removed from Pane.", !testPaper.getChildren().contains(testShape));

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
     * Test of testBringToFrontShape method, of class SelectedShapeManager.
     */
    @Test
    public void testBringToFrontShape() {
        System.out.println("bringToFrontShape");
        int beforeBringToFront, afterBringToFront;
        SelectedShapeManager.setSelectedShapeManagerPaper(dw);
        SelectedShapeManager ssm = SelectedShapeManager.getSelectedShapeManager();
        ssm.setSelectedShape(testShape);
        beforeBringToFront = testPaper.getChildren().indexOf(ssm.getSelectedShape());
        ssm.bringToFrontShape();
        afterBringToFront = testPaper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToFront > beforeBringToFront);
    }

    /**
     * Test of testBringToBackShape method, of class SelectedShapeManager.
     */
    @Test
    public void testBringToBackShape() {
        System.out.println("bringToBackShape");
        int beforeBringToBack, afterBringToBack;
        SelectedShapeManager.setSelectedShapeManagerPaper(dw);
        SelectedShapeManager ssm = SelectedShapeManager.getSelectedShapeManager();
        ssm.setSelectedShape(testShape);
        ssm.bringToFrontShape();
        beforeBringToBack = testPaper.getChildren().indexOf(ssm.getSelectedShape());
        ssm.bringToBackShape();
        afterBringToBack = testPaper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToBack < beforeBringToBack);
    }

    /**
     * Test of copySelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testCopyPasteSelectedShape() {
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
     * Test of cutShape method, of class SelectedShapeManager.
     */
    @Test
    public void testCutShape() {
        System.out.println("cutShape");
        selectedShapeManager.setSelectedShape(testShape);
        selectedShapeManager.cutShape();
        assertTrue("Called deleteSelectedShape method in a Pane where is only one Shape but it isn't removed from Pane.", !testPaper.getChildren().contains(testShape));
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
        double expectedWidth = random.nextInt(TestConstants.MAX_WIDTH), expectedHeight = random.nextInt(TestConstants.MIN_HEIGHT);
        /* Resize Ellipse */
        selectedShapeManager.setSelectedShape(testShape);
        selectedShapeManager.resizeSelectedShape(expectedWidth, expectedHeight);
        assertEquals(expectedHeight, ((Ellipse) testShape).getLayoutBounds().getHeight(), 0);
        assertEquals(expectedWidth, ((Ellipse) testShape).getLayoutBounds().getWidth(), 0);
        /* Resize Rectangle */
        selectedShapeManager.setSelectedShape(testShape2);
        selectedShapeManager.resizeSelectedShape(expectedWidth, expectedHeight);
        assertEquals(expectedHeight, ((Rectangle) testShape2).getLayoutBounds().getHeight(), 0);
        assertEquals(expectedWidth, ((Rectangle) testShape2).getLayoutBounds().getWidth(), 0);
        /* Resize Line */
        selectedShapeManager.setSelectedShape(testShape3);
        selectedShapeManager.resizeSelectedShape(expectedWidth, expectedHeight);
        ShapeEditor se = ShapeEditorFactory.getInstance(selectedShapeManager.getSelectedShape().getClass());
        assertEquals(expectedHeight, se.getHeight(testShape3), 0);
        assertEquals(expectedWidth, se.getWidth(testShape3), 0);
    }
    
    /**
     * Test of the RotationShape method, of class SelectedShapeManager.
     */
    @Test
    public void testRotationShape(){
        System.out.println("rotationShape");
        selectedShapeManager.setSelectedShape(testShape);
        selectedShapeManager.rotationShape(45.0);
        assertEquals(45,selectedShapeManager.getSelectedShape().getRotate(),0);
    }
    
    /**
     * Test of the MirrorVerticalShape method, of class SelectedShapeManager.
     */
    @Test
    public void testMirrorVerticalShape(){
        System.out.println("mirrorVerticalShape");
        selectedShapeManager.setSelectedShape(testShape);
        double prevMirroring = selectedShapeManager.getSelectedShape().getScaleX();
        selectedShapeManager.mirrorVerticalShape();
        assertEquals((-1*prevMirroring),selectedShapeManager.getSelectedShape().getScaleX(),0);
    }
    
    /**
     * Test of the MirrorHorizontalShape method, of class SelectedShapeManager.
     */
    @Test
    public void testMirrorHorizontalShape(){
        System.out.println("mirrorHorizontalShape");
        selectedShapeManager.setSelectedShape(testShape);
        double prevMirroring = selectedShapeManager.getSelectedShape().getScaleY();
        selectedShapeManager.mirrorHorizontalShape();
        assertEquals((-1*prevMirroring),selectedShapeManager.getSelectedShape().getScaleY(),0);
    }
    
}
