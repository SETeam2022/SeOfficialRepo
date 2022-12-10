package seproject.tools;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import seproject.customComponents.DrawingArea;
import seproject.commands.*;

/**
 * This class represents the SelectionTool.
 * 
 */
public class SelectionTool extends Tool {

    private final SelectedShapeManager manager;
    private double startX, startY, offsetX, offsetY;
    private boolean shapeHasBeenDragged;
    private Shape selectedShape = null;

    private final SimpleDoubleProperty scaleX;
    private final SimpleDoubleProperty scaleY;

    /**
     * Creates a new SelectionTool.
     * 
     * @param paper is the pane whose shape nodes will be selected
     */
    public SelectionTool(DrawingArea paper) {
        super(paper);
        this.manager = SelectedShapeManager.getSelectedShapeManager();
        this.shapeHasBeenDragged = false;
        this.scaleX = new SimpleDoubleProperty();
        this.scaleY = new SimpleDoubleProperty();
        this.scaleX.bind(paper.getContainerOfPaperAndGrid().scaleXProperty());
        this.scaleY.bind(paper.getContainerOfPaperAndGrid().scaleYProperty());
    }

    /**
     * This function will be called after a click with the mouse on the paper it
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
     * and, while the mouse is pressed, the users performs a drag. This
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
