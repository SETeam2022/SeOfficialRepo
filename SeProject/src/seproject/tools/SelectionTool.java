package seproject.tools;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import seproject.commands.*;
import seproject.customComponents.LayeredPaper;

/**
 * This class represents the SelectionTool.
 * 
 */
public class SelectionTool extends Tool {

    private final SelectedShapeManager manager;
    private double startX, startY, offsetX, offsetY;
    private boolean shapeHasBeenDragged;
    private Shape selectedShape = null;

    private final DoubleProperty scaleX;
    private final DoubleProperty scaleY;

    /**
     * Creates a new SelectionTool.
     * 
     * @param paper is the pane whose shape nodes will be selected
     */
    public SelectionTool(LayeredPaper paper,DoubleProperty scaleX,DoubleProperty scaleY) {
        super(paper);
        this.manager = SelectedShapeManager.getSelectedShapeManager();
        this.shapeHasBeenDragged = false;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * This function will be called after a click with the mouse on the paper. It
     * will select a shape on the screen.
     *
     * @param event is the event that generated the call to this method
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        Object eventNode = event.getTarget();
        if (eventNode instanceof Shape) {
            Shape tmp = (Shape) eventNode;
            if (selectedShape == null || !selectedShape.equals(tmp)) {
                deselect();
                selectedShape = tmp;
                manager.setSelectedShape(selectedShape);
            }
            startX = selectedShape.getTranslateX();
            startY = selectedShape.getTranslateY();
            offsetX = event.getSceneX() / scaleX.getValue() - selectedShape.getTranslateX();
            offsetY = event.getSceneY() / scaleY.getValue() - selectedShape.getTranslateY();
        } else {
            deselect();
        }
    }

    /**
     * This function will be called after a click with the mouse on the paper
     * and, while the mouse is pressed, the user perform a drag. This
     * fuction allows the user to drag a selected shape on the screen.
     *
     * @param event is the event that generated the call to this method
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        if (selectedShape != null) {
            selectedShape.setTranslateX(event.getSceneX() / scaleX.getValue() - offsetX);
            selectedShape.setTranslateY(event.getSceneY() / scaleY.getValue() - offsetY);
            shapeHasBeenDragged = true;
        }
    }

    /**
     * This function will be called after the release of the mouse primary
     * button. If the shape has been dragged a new TraslationCommand will be
     * created in order to register its latest position.
     *
     * @param event is the event that generated the call to this method
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        if (selectedShape != null && shapeHasBeenDragged)
            Invoker.getInvoker().executeCommand(new TranslationCommand(selectedShape, offsetX, offsetY, startX, startY, scaleX.getValue(), scaleY.getValue(), event.getSceneX(), event.getSceneY()));
        shapeHasBeenDragged = false;
    }

    /**
     * The call to this method will unset the selected shape.
     */
    @Override
    public void deselect() {
        if (selectedShape == null) return;
        manager.unsetSelectedShape();
        selectedShape = null;
    }
    
}
