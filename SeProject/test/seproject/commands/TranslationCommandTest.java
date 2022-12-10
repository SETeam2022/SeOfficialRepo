package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import seproject.tools.SelectionTool;

public class TranslationCommandTest {

    private Pane paper;
    private SelectionTool st;
    private Ellipse instancedEllipse;
    Rectangle testShape;
    private TranslationCommand cmd;
    double offsetX, offsetY, startX, startY, scaleX, scaleY, eventX, eventY;

    @Before
    public void setUp() {
        paper = new Pane();
        testShape = new Rectangle(0,0,500,500);
        paper.getChildren().add(testShape);
        scaleX = paper.getScaleX();
        scaleY = paper.getScaleY();
        eventX = 600;
        eventY = 600;
        startX = testShape.getTranslateY();
        startY = testShape.getTranslateY();
        offsetX = 20;
        offsetY = 20;

    }

    /**
     * Test of execute method, of class TranslationCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        cmd = new TranslationCommand(testShape, offsetX, offsetY, startX, startY,scaleX ,scaleY,eventX,eventY);
        cmd.execute();
        double testX = testShape.getTranslateX();
        double testY = testShape.getTranslateY();
        assertEquals(testX,eventX/scaleX-offsetX,0);
        assertEquals(testY,eventY/scaleY-offsetY,0);

    }
    
    /**
     * Test of executeWithZoomedPane method, of class TranslationCommand.
     */
    @Test
    public void testExecuteWithZoomedPane(){
        System.out.println("executeWithZoomedPane");
        paper.setScaleX(50);
        paper.setScaleY(50);
        cmd = new TranslationCommand(testShape, offsetX, offsetY, startX, startY,paper.getScaleX(), paper.getScaleY(),eventX,eventY);
        cmd.execute();
        double testX = testShape.getTranslateX();
        double testY = testShape.getTranslateY();
        assertEquals(testX,eventX/paper.getScaleX()-offsetX,0);
        assertEquals(testY,eventY/paper.getScaleY()-offsetY,0);
    }

    /**
     * Test of undo method, of class TranslationCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        cmd = new TranslationCommand(testShape, offsetX, offsetY, startX, startY,scaleX ,scaleY,eventX,eventY);
        cmd.execute();
        cmd.undo();
        assertEquals(testShape.getTranslateX(), startX,0);
        assertEquals(testShape.getTranslateY(), startY,0);
    }

}
