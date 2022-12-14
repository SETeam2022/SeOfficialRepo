package seproject.tools;

import java.security.SecureRandom;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import seproject.customComponents.DrawingArea;
import seproject.EventGenerator;
import seproject.Constants;

public class EllipseToolTest {

    private DrawingArea dw;
    private Pane paper;
    private Ellipse testShape;
    private EllipseTool t;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private MouseEvent clickOnBlankPaper;
    private SecureRandom random;

    public EllipseToolTest() {
    }

    /**
     * This methods creates the Test environment, it creates a test Ellipse with
     * red stroke and black fill, and istances an EllipseTool.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        testShape = new Ellipse(0, 0);
        testShape.setRadiusX(10);
        testShape.setRadiusY(10);
        testShape.setStroke(Color.RED);
        testShape.setFill(Color.BLACK);
        dw = new DrawingArea(random.nextInt(Constants.MAX_WIDTH), random.nextInt(Constants.MAX_HEIGHT));
        paper = dw.getPaper();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        t = new EllipseTool(dw, borderColorProperty, fillColorProperty);
        clickOnBlankPaper = EventGenerator.PrimaryButtonMousePressed(paper, paper, testShape.getCenterX(), testShape.getCenterY());
    }

    /**
     * Simulates a mouse click and check if the ellipse added to the paper is in
     * the same position of a test ellipse and has the same stroke and fill
     * color attribute.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("mousePressed");

        t.onMousePressed(clickOnBlankPaper);

        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape", elem instanceof Ellipse);
        Ellipse casted = (Ellipse) elem;
        // Checking for Position.
        Assert.assertEquals(testShape.getCenterX(), casted.getCenterX(), 0);
        Assert.assertEquals(testShape.getCenterY(), casted.getCenterY(), 0);
        // Checking for Color.
        Assert.assertEquals(testShape.getStroke(), casted.getStroke());
        Assert.assertEquals(testShape.getFill(), casted.getFill());

    }

    /**
     * Simulates a mouse press and the drag. Checks if the added ellipse has the
     * same radius (due to the drag effect).
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("mouseDragged");
        t.onMousePressed(clickOnBlankPaper);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDragged(paper, paper, testShape.getRadiusX(), testShape.getRadiusY()));
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape", elem instanceof Ellipse);
        Ellipse casted = (Ellipse) elem;
        Assert.assertEquals(testShape.getRadiusX(), casted.getRadiusX(), 0);
        Assert.assertEquals(testShape.getRadiusY(), casted.getRadiusY(), 0);

    }

    /**
     * Simulates a mouse press and the release. Checks if the added ellipse has the
     * same radius (due to the drag effect).
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("mouseReleased");
        t.onMousePressed(clickOnBlankPaper);
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(paper, paper, testShape.getRadiusX(), testShape.getRadiusY()));
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape", elem instanceof Ellipse);
        Ellipse casted = (Ellipse) elem;
        Assert.assertEquals(testShape.getRadiusX(), casted.getRadiusX(), 0);
        Assert.assertEquals(testShape.getRadiusY(), casted.getRadiusY(), 0);
    }

}
