package seproject.tools;

import java.security.SecureRandom;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seproject.customComponents.DrawingArea;
import seproject.EventGenerator;
import seproject.TestConstants;

public class RectangleToolTest {

    private Pane paper;
    private Rectangle testShape;
    private RectangleTool t;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private MouseEvent clickOnBlankPaper;
    private SecureRandom random;
    private DrawingArea dw;
    public RectangleToolTest() {
    }

    /**
     * This method creates the Test environment, it creates a test rectangle with
     * red stroke and black fill, and istances a rectangle tool.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        testShape = new Rectangle(0, 0, 10, 20);
        testShape.setStroke(Color.RED);
        testShape.setFill(Color.BLACK);
        
        dw = new DrawingArea(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        paper = dw.getPaper();

        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        t = new RectangleTool(dw, borderColorProperty, fillColorProperty);
        clickOnBlankPaper = EventGenerator.PrimaryButtonMousePressed(paper, paper, testShape.getX(), testShape.getY());
    }

    /**
     * Simulate a mouse click and check if the rectangle added to the paper is
     * in the same position of a test rectangle and has the same stroke and fill
     * color attributes.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("mousePressed");
        t.onMousePressed(clickOnBlankPaper);
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Rectangle);
        Rectangle casted = (Rectangle) elem;
        //Checking for Position
        Assert.assertEquals(casted.getX(), testShape.getX(), 0);
        Assert.assertEquals(casted.getY(), testShape.getY(), 0);
        //Checking for Color.
        Assert.assertEquals(testShape.getStroke(), casted.getStroke());
        Assert.assertEquals(testShape.getFill(), casted.getFill());


    }

    /**
     * Simulate a mouse press and the drag. Checks if the rectangle added has
     * the same width and height of the testShape.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("mouseDragged");
        t.onMousePressed(clickOnBlankPaper);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDragged(paper,paper,testShape.getWidth(),testShape.getHeight()));
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Rectangle);
        Rectangle casted = (Rectangle) elem;
        Assert.assertEquals(testShape.getWidth(), casted.getWidth(), 0);
        Assert.assertEquals(testShape.getHeight(), casted.getHeight(), 0);
    }

    /**
     * Simulate a mouse release. Checks if the rectangle added has the same
     * width and height of the testShape.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("mouseReleased");
        t.onMousePressed(clickOnBlankPaper);
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(paper, paper, testShape.getWidth(),testShape.getHeight()));
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Rectangle);
        Rectangle casted = (Rectangle) elem;
        Assert.assertEquals(testShape.getWidth(), casted.getWidth(), 0);
        Assert.assertEquals(testShape.getHeight(), casted.getHeight(), 0);
    }

}
