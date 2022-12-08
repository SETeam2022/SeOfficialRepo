package seproject.tools;

import java.security.SecureRandom;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seproject.DrawingArea;
import seproject.EventGenerator;
import seproject.TestConstants;

public class LineToolTest {

    private Pane paper;
    private Line testShape;
    private DrawingArea dw;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private LineTool t;
    private MouseEvent clickOnBlankPaper;
    private SecureRandom random;
    public LineToolTest() {
    }

    /**
     * This method create the Test environment, it creates a test line with red
     * stroke and black fill, and istances a line tool.
     */
    @Before
    public void setUp() {
        this.random = new SecureRandom();
        testShape = new Line(443, 308, 471, 308);
        testShape.setStroke(Color.RED);
        testShape.setFill(Color.BLACK);
        dw = new DrawingArea(random.nextInt(TestConstants.MAX_WIDTH), random.nextInt(TestConstants.MAX_HEIGHT));
        paper = dw.getPaper();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        t = new LineTool(dw, borderColorProperty, fillColorProperty);
        clickOnBlankPaper = EventGenerator.PrimaryButtonMouseClick(paper, paper, testShape.getStartX(), testShape.getStartY());
    }

    /**
     * Simulate a mouse click and check if the line added to the paper is in the
     * same start position of a test line and has the same stroke and fill color
     * attribute
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("mousePressed");
        t.onMousePressed(clickOnBlankPaper);
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Line);
        Line casted = (Line) elem;
        //Checking for positions
        Assert.assertEquals(casted.getStartX(), testShape.getStartX(), 0);
        Assert.assertEquals(casted.getStartY(), testShape.getStartY(), 0);
        // Checking for colors
        Assert.assertEquals(testShape.getStroke(), casted.getStroke());
        Assert.assertEquals(testShape.getFill(), casted.getFill());


    }

    /**
     * Simulate a mouse press and the drag. Checks if the line added to the
     * paper has the same length (due to the drag effect)
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("mouseDragged");
        t.onMousePressed(clickOnBlankPaper);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDrag(paper,paper,testShape.getEndX(),  testShape.getEndY()));
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Line);
        Line casted = (Line) elem;
        Assert.assertEquals(testShape.getEndX(), casted.getEndX(), 0);
        Assert.assertEquals(testShape.getEndY(), casted.getEndY(), 0);

    }

    /**
     * Simulate a mouse realease. Checks if the line added to the paper has the
     * same length.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("mouseReleased");
        t.onMousePressed(clickOnBlankPaper);
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(paper, paper, testShape.getEndX(),  testShape.getEndY()));
        Node elem = paper.getChildren().get(0);
        assertTrue("The shape isn't of the same class of the testShape",elem instanceof Line);
        Line casted = (Line) elem;
        Assert.assertEquals(testShape.getEndX(), casted.getEndX(), 0);
        Assert.assertEquals(testShape.getEndY(), casted.getEndY(), 0);
    }

}
