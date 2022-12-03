package seproject.commands;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seproject.tools.EllipseTool;
import seproject.tools.SelectedShapeManager;
import seproject.tools.SelectionTool;

public class TranslationCommandTest {

    private Pane paper;
    private EllipseTool ell;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private SelectionTool st;
    private Ellipse instancedEllipse;
    private TranslationCommand cmd;
    double offsetX, offsetY, startX, startY;

    @Before
    public void setUp() {
        paper = new Pane();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        ell = new EllipseTool(paper, borderColorProperty, fillColorProperty);

        st = new SelectionTool(paper);
        SelectedShapeManager.setSelectedShapeManagerPaper(paper);

        ell.onMousePressed(new MouseEvent(paper, paper, MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));

        ell.onMouseDragged(new MouseEvent(paper, paper, MouseEvent.MOUSE_DRAGGED, 200, 300,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));

        for (Node node : paper.getChildren()) {
            if (node instanceof Ellipse) {
                instancedEllipse = (Ellipse) node;
                break;
            }
        }
    }

    /**
     * Test of execute method, of class TranslationCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        // select event
        MouseEvent e1 = new MouseEvent(paper, instancedEllipse, MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null);
        st.onMousePressed(e1);

        Ellipse selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();

        assertTrue(selectedEllipse != null);
        // drag event
        MouseEvent event = new MouseEvent(paper, instancedEllipse, MouseEvent.MOUSE_DRAGGED, 40, 40,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null);

        offsetX = e1.getSceneX() - selectedEllipse.getTranslateX();
        offsetY = e1.getSceneY() - selectedEllipse.getTranslateY();
        startX = selectedEllipse.getTranslateX();
        startY = selectedEllipse.getTranslateY();

        cmd = new TranslationCommand(selectedEllipse, offsetX, offsetY, startX, startY, paper.getScaleX(),paper.getScaleY(),event);
        cmd.execute();
        st.onMousePressed(e1);
        Ellipse newSelectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();

        assertTrue(newSelectedEllipse == null);

    }

    /**
     * Test of undo method, of class TranslationCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");

        // select event
        MouseEvent e1 = new MouseEvent(paper, instancedEllipse, MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null);
        st.onMousePressed(e1);

        Ellipse selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();

        assertTrue(selectedEllipse != null);
        // drag event
        MouseEvent event = new MouseEvent(paper, instancedEllipse, MouseEvent.MOUSE_DRAGGED, 40, 40,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null);

        offsetX = e1.getSceneX() - selectedEllipse.getTranslateX();
        offsetY = e1.getSceneY() - selectedEllipse.getTranslateY();
        startX = selectedEllipse.getTranslateX();
        startY = selectedEllipse.getTranslateY();

        cmd = new TranslationCommand(selectedEllipse, offsetX, offsetY, startX, startY,paper.getScaleX(),paper.getScaleY(),event);
        cmd.execute();
        st.onMousePressed(event);

        cmd.undo();
        st.onMousePressed(e1);
        Ellipse newSelectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();
        assertTrue(newSelectedEllipse != null);
    }

}
