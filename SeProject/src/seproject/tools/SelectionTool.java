package seproject.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import seproject.commands.*;

public class SelectionTool extends Tool {

    private final SelectedShapeManager manager;
    private double startX, startY, offsetX, offsetY;
    private boolean shapeHasBeenDragged;

    public SelectionTool(Pane paper) {
        super(paper);
        this.manager = SelectedShapeManager.getSelectedShapeManager();
        this.shapeHasBeenDragged = false;
    }

    /**
     * This function will be called after a click with the mouse on the paper it
     * will select a shape on the screen.
     *
     * @param event is the event that generated the call to this method
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        SelectedShapeManager.getSelectedShapeManager().unsetSelectedShape();
        Object eventNode = event.getTarget();
        if (eventNode instanceof Shape) {
            Shape tmp = (Shape) eventNode;
            if (tmp.getBoundsInParent().contains(event.getX(), event.getY())) {
                manager.setSelectedShape(tmp);
                startX = tmp.getTranslateX(); 
                startY = tmp.getTranslateY(); 
                offsetX = event.getSceneX()/paper.getScaleX() - tmp.getTranslateX();
                offsetY = event.getSceneY()/paper.getScaleY() - tmp.getTranslateY();
            }
        }
    }

    /**
     * This function will be called after a click with the mouse on the paper
     * and, while the mouse is pressed, the users performs a dragging. This
     * fuction allows the user to drag a selected shape on the screen.
     *
     * @param event is the event that generated the call to this method
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        Shape selectedShape = manager.getSelectedShape();
        if (selectedShape != null) {
            selectedShape.setTranslateX(event.getSceneX()/paper.getScaleX() - offsetX);
            selectedShape.setTranslateY(event.getSceneY()/paper.getScaleY() - offsetY);
            shapeHasBeenDragged = true;
        }
    }

    /**
     * This function will be called after the release of the mouse primary button
     * if the shape has been dragged a new TraslationCommand will be created in order to
     * register its older position.
     * @param event is the event that generated the call to this method
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        Shape selectedShape = manager.getSelectedShape();
        if (selectedShape != null && shapeHasBeenDragged) {
            Invoker.getInvoker().executeCommand(new TranslationCommand(selectedShape, offsetX, offsetY, startX, startY,paper.getScaleX(), paper.getScaleY(),event));
        }
        shapeHasBeenDragged = false;
    }
}
