/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject.tools;

import static java.lang.Double.min;
import static java.lang.Math.max;
import java.security.SecureRandom;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import seproject.EventGenerator;
import seproject.TestConstants;

/**
 *
 * @author alewi
 */
public class TextToolTest {

    private Pane paper;
    private Text testShape;
    private TextTool t;
    private ObjectProperty<Color> strokeColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private SecureRandom random;
    private MouseEvent pressEvent;
    private static final int TOLLERANCE = 2;

    // PROPERTIES
    private StringProperty textAreaStringProperty;
    private DoubleProperty textAreaPrefWidthProperty;
    private DoubleProperty textAreaPrefHeightProperty;
    private BooleanProperty textAreaVisibleProperty;
    private ReadOnlyBooleanProperty textAreaFocusedProperty;
    private DoubleProperty textAreaLayoutXProperty;
    private DoubleProperty textAreaLayoutYProperty;

    public TextToolTest() {
    }

    /**
     * This method sets up the configuration which is required to test the
     * TextTool class' methods. We configure the pane, the testShape that in
     * this case is the Text, and its characteristics in terms of text color,
     * for example.
     */
    @Before
    public void setUp() {
        paper = new Pane();

        paper.setMinSize(TestConstants.MIN_WIDTH, TestConstants.MIN_HEIGHT);
        this.random = new SecureRandom();
        testShape = new Text();
        strokeColorProperty = new SimpleObjectProperty<>();
        // SET UP TEXT TOOL PROPERTIES.
        fillColorProperty = new SimpleObjectProperty<>();
        strokeColorProperty.set(Color.DARKORCHID);
        fillColorProperty.set(Color.AQUA);
        textAreaStringProperty = new SimpleStringProperty();
        textAreaPrefWidthProperty = new SimpleDoubleProperty();
        textAreaPrefHeightProperty = new SimpleDoubleProperty();
        textAreaVisibleProperty = new SimpleBooleanProperty();
        textAreaFocusedProperty = new SimpleBooleanProperty();
        textAreaLayoutXProperty = new SimpleDoubleProperty();
        textAreaLayoutYProperty = new SimpleDoubleProperty();
        //
        random = new SecureRandom();

        t = new TextTool(paper, strokeColorProperty, fillColorProperty, textAreaStringProperty,
                textAreaPrefWidthProperty, textAreaPrefHeightProperty, textAreaVisibleProperty,
                textAreaFocusedProperty, textAreaLayoutXProperty, textAreaLayoutYProperty);

        pressEvent = EventGenerator.PrimaryButtonMouseDrag(paper, paper, random.nextInt(TestConstants.MAX_WIDTH / 2), random.nextInt(TestConstants.MAX_HEIGHT / 2));
    }

    @After
    public void tearDown() {
    }

    /**
     * In that case, if there is a blank paper and I click on it at the end of
     * this operation, the paper must have only one child: the rectangle.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        t.onMousePressed(pressEvent);
        int expectedValue = 1;
        int actualValue = paper.getChildren().size();
        assertEquals(expectedValue, actualValue);
        Class<?> expectedClass = Rectangle.class;
        Class<?> actualClass = paper.getChildren().get(0).getClass();
        assertEquals(expectedClass, actualClass);
    }

    /**
     * Now, a mouse press has been performed on a blank paper (similar situation
     * to before) and then an mouse release has been performed.
     *
     * Following these two operations, there should be nothing on the sheet.
     */
    @Test
    public void testOnMousePressed2() {
        System.out.println("onMousePressed2");
        t.onMousePressed(pressEvent);
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(pressEvent.getSource(), pressEvent.getTarget(), pressEvent.getX(), pressEvent.getY()));
        int expectedValue = 0;
        int actualValue = paper.getChildren().size();
        assertEquals(expectedValue, actualValue);
    }

    /**
     *
     * Now in this situation, a mouse press and drag events is performed in a
     * random point of the paper. In the test the size of the rectangle will be
     * checked.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("onMouseDragged");
        Point2D vertexA = new Point2D(pressEvent.getX(), pressEvent.getY());
        Point2D vertexB = new Point2D(random.nextInt(TestConstants.MAX_WIDTH / 2) + pressEvent.getX(), random.nextInt(TestConstants.MAX_HEIGHT / 2) + pressEvent.getY());

        t.onMousePressed(pressEvent);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDrag(pressEvent.getSource(), pressEvent.getTarget(), vertexB.getX(), vertexB.getY()));

        Rectangle expectedRectangle = createRectangleFrom2Vertexes(vertexA, vertexB);
        Rectangle actualRectangle = (Rectangle) paper.getChildren().get(0);
        System.out.println(actualRectangle);
        assertEquals(expectedRectangle.getX(), actualRectangle.getX(), TOLLERANCE);
        assertEquals(expectedRectangle.getY(), actualRectangle.getY(), TOLLERANCE);
        assertEquals(expectedRectangle.getWidth(), actualRectangle.getWidth(), TOLLERANCE);
        assertEquals(expectedRectangle.getHeight(), actualRectangle.getHeight(), TOLLERANCE);
    }

    /**
     * Need to be implemented.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("onMouseReleased");
        Point2D vertexA = new Point2D(pressEvent.getX(), pressEvent.getY());
        Point2D vertexB = new Point2D(random.nextInt(TestConstants.MAX_WIDTH / 2) + pressEvent.getX(), random.nextInt(TestConstants.MAX_HEIGHT / 2) + pressEvent.getY());

        t.onMousePressed(pressEvent);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDrag(pressEvent.getSource(), pressEvent.getTarget(), vertexB.getX(), vertexB.getY()));
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(pressEvent.getSource(), pressEvent.getTarget(), vertexB.getX(), vertexB.getY()));
        Rectangle testRectangle = createRectangleFrom2Vertexes(vertexA, vertexB);
    }

    /**
     *
     * In this test we will verify that, in a situation where we deselect, the
     * paper remains empty.
     */
    @Test
    public void testDeselect() {
        System.out.println("deselect");
        t.deselect();
        int expectedValue = 0;
        int actualValue = paper.getChildren().size();
        assertEquals(expectedValue, actualValue);

    }

    private Rectangle createRectangleFrom2Vertexes(Point2D a, Point2D b) {
        double minX = min(a.getX(), b.getX());
        double minY = min(a.getY(), b.getY());
        double maxX = max(a.getX(), b.getX());
        double maxY = max(a.getY(), b.getY());
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

}
