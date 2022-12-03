package seproject.tools;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.*;

public class EllipseToolTest {

    private Pane paper;
    private Ellipse testShape;
    private EllipseTool t;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;

    public EllipseToolTest() {
    }

    /**
     * This methods create the Test environment, it creates a test Ellipse with
     * red stroke and black fill, and istances an EllipseTool.
     */
    @Before
    public void setUp() {
        testShape = new Ellipse(0, 0);
        testShape.setRadiusX(10);
        testShape.setRadiusY(10);
        testShape.setStroke(Color.RED);
        testShape.setFill(Color.BLACK);
        paper = new Pane();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        t = new EllipseTool(paper, borderColorProperty, fillColorProperty);
    }

    /**
     * Simulate a mouse click and check if the ellipse added to the paper is in
     * the same position of a test ellipse and has the same stroke and fill
     * color attribute
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");

        MouseEvent e = new MouseEvent(MouseEvent.MOUSE_CLICKED, testShape.getCenterX(), testShape.getCenterY(), 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null);

        t.onMousePressed(e);

        for (Node elem : paper.getChildren()) {
            if (elem instanceof Ellipse) {
                Ellipse casted = (Ellipse) elem;
                // Checking for Position.
                Assert.assertEquals(testShape.getCenterX(), casted.getCenterX(), 0);
                Assert.assertEquals(testShape.getCenterY(), casted.getCenterY(), 0);
                // Checking for Color.
                Assert.assertEquals(testShape.getStroke(), casted.getStroke());
                Assert.assertEquals(testShape.getFill(), casted.getFill());

            }
        }

    }

    /**
     * Simulate a mouse press and the drag. Checks if the added ellipse has the
     * same radius (due to the drag effect)
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("mouseDragged");
        MouseEvent e2 = new MouseEvent(MouseEvent.MOUSE_CLICKED, testShape.getCenterX(), testShape.getCenterY(), 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null);

        MouseEvent e = new MouseEvent(MouseEvent.MOUSE_DRAGGED, testShape.getRadiusX(), testShape.getRadiusY(), 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true, true, true, true, true, null);
        t.onMousePressed(e2);
        t.onMouseDragged(e);
        for (Node elem : paper.getChildren()) {
            if (elem instanceof Ellipse) {
                Ellipse casted = (Ellipse) elem;
                Assert.assertEquals(testShape.getRadiusX(), casted.getRadiusX(), 0);
                Assert.assertEquals(testShape.getRadiusY(), casted.getRadiusY(), 0);
            }
        }

    }

    @Test
    public void testOnMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent e2 = new MouseEvent(MouseEvent.MOUSE_CLICKED, testShape.getCenterX(), testShape.getCenterY(), 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null);

        MouseEvent e3 = new MouseEvent(MouseEvent.MOUSE_RELEASED, testShape.getRadiusX(), testShape.getRadiusY(), 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true, true, true, true, true, null);
        t.onMousePressed(e2);
        t.onMouseReleased(e3);
        for (Node elem : paper.getChildren()) {
            if (elem instanceof Ellipse) {
                Ellipse casted = (Ellipse) elem;
                Assert.assertEquals(testShape.getRadiusX(), casted.getRadiusX(), 0);
                Assert.assertEquals(testShape.getRadiusY(), casted.getRadiusY(), 0);
            }
        }
    }

}
