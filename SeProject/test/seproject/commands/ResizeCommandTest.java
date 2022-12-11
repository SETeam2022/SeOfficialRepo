package seproject.commands;

import java.security.SecureRandom;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.Constants;

public class ResizeCommandTest {

    private SecureRandom random;
    private ResizeCommand resize;
    private Rectangle testShape;
    private double previousWidth;
    private double previousHeight;
    private double newWidth;
    private double newHeight;

    public ResizeCommandTest() {
    }

    /**
     * This method instances a shape on which perform the test, randomly intializes it
     * and a ResizeCommand.
     */
    @Before
    public void setUp() {
        testShape = new Rectangle();
        this.random = new SecureRandom();
        previousWidth = random.nextInt(Constants.MAX_WIDTH);
        previousHeight = random.nextInt(Constants.MAX_HEIGHT);
        newWidth = random.nextInt(Constants.MAX_WIDTH);
        newHeight = random.nextInt(Constants.MAX_HEIGHT);
        testShape.setWidth(previousWidth);
        testShape.setHeight(previousHeight);
        resize = new ResizeCommand(testShape, newWidth, newHeight);
    }

    /**
     * Test of execute method, of class ResizeCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("Execute");
        resize.execute();
        assertEquals(testShape.getWidth(), newWidth, 0);
        assertEquals(testShape.getHeight(), newHeight, 0);
    }

    /**
     * Test of undo method, of class ResizeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        resize.execute();
        resize.undo();
        assertEquals(testShape.getWidth(), previousWidth, 0);
        assertEquals(testShape.getHeight(), previousHeight, 0);
    }

}
