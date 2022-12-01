package seproject.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import seproject.commands.*;

public class SelectionTool extends Tool {

    private final SelectedShapeManager manager;
    private double startX,startY,offsetX,offsetY;

    public SelectionTool(Pane paper) {
        super(paper);
        this.manager = SelectedShapeManager.getSelectedShapeManager();
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
                // nuovo codice
                startX = tmp.getTranslateX();
                startY = tmp.getTranslateY();
                offsetX = event.getSceneX()- tmp.getTranslateX();
                offsetY = event.getSceneY()-tmp.getTranslateY();
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
            selectedShape.setTranslateX(event.getSceneX()-offsetX);
            selectedShape.setTranslateY(event.getSceneY()-offsetY);
        }
    }
    
    @Override
    public void onMouseReleased(MouseEvent event){
        Shape selectedShape = manager.getSelectedShape();
        if (selectedShape != null) {
            Invoker.getInvoker().executeCommand(new TraslationCommand(selectedShape,offsetX,offsetY,startX,startY,event));
        }
    }
}
