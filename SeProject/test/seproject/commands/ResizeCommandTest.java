package seproject.commands;

import editor.ShapeEditor;
import editor.ShapeEditorChooser;
import java.security.SecureRandom;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.Constants;

public class ResizeCommandTest {

    private SecureRandom random;
    private Rectangle testRectangle;
    private Ellipse testEllipse;
    private Line testLine;
    private Text testText;
    private Polyline testPolygon;
    private double previousWidth;
    private double previousHeight;
    private double newWidth;
    private double newHeight;
    private Invoker invoker;
    private ShapeEditor editor;
    private ResizeCommand res;

    public ResizeCommandTest() {
    }

    /**
     * This method instances a shape on which perform the test, randomly intializes it
     * and a ResizeCommand.
     */
    @Before
    public void setUp() {
        testRectangle = new Rectangle();
        testEllipse = new Ellipse();
        testLine = new Line();
        testText = new Text();
        this.random = new SecureRandom();
        testPolygon = new Polyline(random.nextInt(Constants.MIN_WIDTH),random.nextInt(Constants.MIN_HEIGHT), random.nextInt(Constants.MIN_WIDTH),random.nextInt(Constants.MIN_HEIGHT), random.nextInt(Constants.MIN_WIDTH),random.nextInt(Constants.MIN_HEIGHT));
        previousWidth = random.nextInt(Constants.MAX_WIDTH);
        previousHeight = random.nextInt(Constants.MAX_HEIGHT);
        newWidth = random.nextInt(Constants.MAX_WIDTH);
        newHeight = random.nextInt(Constants.MAX_HEIGHT);
        testRectangle.setWidth(previousWidth);
        testRectangle.setHeight(previousHeight);
        this.invoker = Invoker.getInvoker();
    }

    /**
     * Test of execute method, of class ResizeCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("Execute");
        invoker.executeCommand(new ResizeCommand(testRectangle, newWidth, newHeight));
        assertEquals(testRectangle.getWidth(), newWidth, 0);
        assertEquals(testRectangle.getHeight(), newHeight, 0);
        editor = ShapeEditorChooser.getInstance(Ellipse.class);
        invoker.executeCommand(new ResizeCommand(testEllipse, newWidth, newHeight));
        assertEquals(editor.getWidth(testEllipse), newWidth, 0);
        assertEquals(editor.getHeight(testEllipse), newHeight, 0);
        editor = ShapeEditorChooser.getInstance(Line.class);
        invoker.executeCommand(new ResizeCommand(testLine, newWidth, newHeight));
        assertEquals(editor.getWidth(testLine), newWidth, 0);
        assertEquals(editor.getHeight(testLine), newHeight, 0);
        editor = ShapeEditorChooser.getInstance(Polyline.class);
        invoker.executeCommand(new ResizeCommand(testPolygon, newWidth, newHeight));
        assertEquals(editor.getWidth(testPolygon), newWidth, 0);
        assertEquals(editor.getHeight(testPolygon), newHeight, 0);
        editor = ShapeEditorChooser.getInstance(Text.class);
        invoker.executeCommand(new ResizeCommand(testText, newWidth, newHeight));
        assertEquals(editor.getWidth(testText), newWidth, 0);
    }

    /**
     * Test of undo method, of class ResizeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        res = new ResizeCommand(testRectangle, newWidth, newHeight);
        invoker.executeCommand(res);
        invoker.undoLastCommand();
        assertEquals(testRectangle.getWidth(), previousWidth, 0);
        assertEquals(testRectangle.getHeight(), previousHeight, 0);
        
        editor = ShapeEditorChooser.getInstance(Ellipse.class);
        res = new ResizeCommand(testEllipse, newWidth, newHeight);
        previousWidth = editor.getWidth(testEllipse);
        previousHeight = editor.getHeight(testEllipse);
        invoker.executeCommand(res);
        invoker.undoLastCommand();
        assertEquals(editor.getWidth(testEllipse), previousWidth, 0);
        assertEquals(editor.getHeight(testEllipse), previousHeight, 0);
        
        editor = ShapeEditorChooser.getInstance(Line.class);
        res = new ResizeCommand(testLine, newWidth, newHeight);
        previousWidth = editor.getWidth(testLine);
        previousHeight = editor.getHeight(testLine);
        invoker.executeCommand(res);
        invoker.undoLastCommand();
        assertEquals(editor.getWidth(testLine), previousWidth, 0);
        assertEquals(editor.getHeight(testLine), previousHeight, 0);
        
        editor = ShapeEditorChooser.getInstance(Polyline.class);
        res = new ResizeCommand(testPolygon, newWidth, newHeight);
        previousWidth = editor.getWidth(testPolygon);
        previousHeight = editor.getHeight(testPolygon);
        invoker.executeCommand(res);
        invoker.undoLastCommand();
        assertEquals(editor.getWidth(testPolygon), previousWidth, 0);
        assertEquals(editor.getHeight(testPolygon), previousHeight, 0);
        
        editor = ShapeEditorChooser.getInstance(Text.class);
        res = new ResizeCommand(testText, newWidth, newHeight);
        previousWidth = editor.getWidth(testText);
        invoker.executeCommand(res);
        invoker.undoLastCommand();
        assertEquals(editor.getWidth(testText), previousWidth, 0);
    }

}
