package seproject.tools;

import javafx.scene.control.TextArea;
import static java.lang.Double.min;
import static java.lang.Math.max;
import java.security.SecureRandom;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.customComponents.DrawingArea;
import seproject.EventGenerator;
import seproject.Constants;
import seproject.customComponents.LayeredPaper;

public class TextToolTest {

    private DrawingArea paper;
    private TextTool t;
    private ObjectProperty<Color> strokeColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private ReadOnlyObjectProperty<Integer> textSpinnerValueProperty;
    private SecureRandom random;
    private MouseEvent pressEvent;
    private static final double TOLLERANCE = 0.00002;
    private static final String TEST_TEXT = "HEY THERE, I'M USING DEFINITELY NOT PAINT!";

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
        new JFXPanel();
        paper = new DrawingArea(Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
        paper.setMinSize(Constants.MIN_WIDTH, Constants.MIN_HEIGHT);
        this.random = new SecureRandom();

        // SET UP TEXT TOOL PROPERTIES.
        strokeColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        strokeColorProperty.set(Color.DARKORCHID);
        fillColorProperty.set(Color.AQUA);
        textSpinnerValueProperty = new SimpleObjectProperty<>(Constants.DEFAULT_TEXT_SIZE);
        //
        random = new SecureRandom();
        t = new TextTool(paper, strokeColorProperty, fillColorProperty, textSpinnerValueProperty);
        pressEvent = EventGenerator.PrimaryButtonMouseDragged(paper, paper, random.nextInt(Constants.MAX_WIDTH / 2), random.nextInt(Constants.MAX_HEIGHT / 2));
    }

    @After
    public void tearDown() {
    }

    /**
     * In that case, if there is a blank paper and I click on it at the end of
     * this operation, the drawing area must have only two child: the group
     * (that contains the paper and the grid) and the rectangle.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        t.onMousePressed(pressEvent);
        int expectedValue = 1;
        int actualValue = paper.getPaperSize();
        assertEquals(expectedValue, actualValue);
        Class<?> expectedClass = Rectangle.class;
        Class<?> actualClass = paper.getPaper().getChildren().get(0).getClass();
        assertEquals(expectedClass, actualClass);
    }

    /**
     * Now, a mouse press has been performed on a blank paper (similar situation
     * to before) and then an mouse release has been performed.
     *
     * Following these two operations, there should be only one child on the
     * drawing area.
     */
    @Test
    public void testOnMousePressed2() {
        System.out.println("onMousePressed2");
        t.onMousePressed(pressEvent);
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(pressEvent.getSource(), pressEvent.getTarget(), pressEvent.getX(), pressEvent.getY()));
        int expectedValue = 0;
        int actualValue = paper.getPaperSize();
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
        Rectangle expectedRectangle = createRectangleFromVertex(vertexA);
        t.onMousePressed(pressEvent);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDragged(pressEvent.getSource(), pressEvent.getTarget(), vertexA.getX() + expectedRectangle.getWidth(), vertexA.getY() + expectedRectangle.getHeight()));

        Rectangle actualRectangle = (Rectangle) paper.getPaper().getChildren().get(0);

        assertEquals(expectedRectangle.getX(), actualRectangle.getX(), TOLLERANCE);
        assertEquals(expectedRectangle.getY(), actualRectangle.getY(), TOLLERANCE);
        assertEquals(expectedRectangle.getWidth(), actualRectangle.getWidth(), TOLLERANCE);
        assertEquals(expectedRectangle.getHeight(), actualRectangle.getHeight(), TOLLERANCE);
    }

    /**
     * This text checks whether after the mouse release event there is a
     * TextArea of ​​the desired size after the mouse dragged. For the
     * dimensions and position of the TextArea to be considered valid, they must
     * coincide with those of the Rectangle.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("onMouseReleased");

        Rectangle testRectangle = generateTempTextArea();

        int expectedSize = 3;
        int actualSize = paper.getChildren().size();

        assertEquals(expectedSize, actualSize);

        Node actualNode = paper.getChildren().get(actualSize - 1);
        Class<?> expectedClass = TextArea.class;
        assertEquals(expectedClass, actualNode.getClass());

        TextArea actualTextArea = (TextArea) actualNode;
        assertEquals(testRectangle.getX(), actualTextArea.getLayoutX(), TOLLERANCE);
        assertEquals(testRectangle.getY(), actualTextArea.getLayoutY(), TOLLERANCE);
        actualTextArea.setText(TEST_TEXT);
        int outX = (int) (testRectangle.getX() + testRectangle.getWidth() + 1);
        int outY = (int) (testRectangle.getY() + testRectangle.getHeight() + 1);
        int randOutX = outX + random.nextInt(Constants.MAX_WIDTH - outX);
        int randOutY = outY + random.nextInt(Constants.MAX_HEIGHT - outY);

        /**
         * At this point in the code, a mouse click is simulated outside the
         * TextArea, to then verify that this is no longer present on the grid
         * and sheet group and that the sheet itself contains the equivalent of
         * the Text class. It will later check that its content matches the test
         * string.
         */
        t.onMousePressed(EventGenerator.PrimaryButtonMousePressed(pressEvent.getSource(), pressEvent.getTarget(), randOutX, randOutY));
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(pressEvent.getSource(), pressEvent.getTarget(), randOutX, randOutY));

        expectedSize = 2;
        actualSize = paper.getChildren().size();
        assertEquals(expectedSize, actualSize);

        expectedSize = 1;
        actualSize = paper.getPaper().getChildren().size();
        assertEquals(expectedSize, actualSize);

        actualNode = paper.getPaper().getChildren().get(actualSize - 1);
        expectedClass = Text.class;
        assertEquals(expectedClass, actualNode.getClass());
        Text actualText = (Text) actualNode;
        assertEquals(TEST_TEXT, actualText.getText());

    }

    /**
     *
     * In this test we will verify that, in a situation where we deselect, the
     * paper remains with only one child.
     */
    @Test
    public void testDeselect() {
        System.out.println("deselect");
        Rectangle testRectangle = generateTempTextArea();
        t.deselect();
        int expectedValue = 2;
        int actualValue = paper.getChildren().size();
        assertEquals(expectedValue, actualValue);

    }

    private Rectangle createRectangleFromVertex(Point2D a) {
        return new Rectangle(a.getX(), a.getY(), random.nextInt(Constants.MAX_WIDTH / 2 - Constants.MIN_WIDTH-1)+Constants.MIN_WIDTH+1, random.nextInt(Constants.MAX_HEIGHT/2 -100)+100);
    }

    private Rectangle generateTempTextArea() {
        Point2D vertexA = new Point2D(pressEvent.getX(), pressEvent.getY());
        Rectangle rect = createRectangleFromVertex(vertexA);
        t.onMousePressed(pressEvent);
        t.onMouseDragged(EventGenerator.PrimaryButtonMouseDragged(pressEvent.getSource(), pressEvent.getTarget(), vertexA.getX() + rect.getWidth(), vertexA.getY() + rect.getHeight()));
        t.onMouseReleased(EventGenerator.PrimaryButtonMouseReleased(pressEvent.getSource(), pressEvent.getTarget(), vertexA.getX() + rect.getWidth(), vertexA.getY() + rect.getHeight()));
        return rect;

    }

}
