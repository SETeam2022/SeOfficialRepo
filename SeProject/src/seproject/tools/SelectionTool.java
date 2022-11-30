/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author teodo
 */
public class SelectionTool extends Tool{
    
    private Shape selectedShape;
    
    public SelectionTool(Pane paper) {
        super(paper);
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
                this.selectedShape = tmp;
                SelectedShapeManager.getSelectedShapeManager().setSelectedShape(tmp);
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
        if (this.selectedShape != null) {
            this.selectedShape.setLayoutX(event.getX()-((selectedShape.getLayoutBounds().getMaxX()+selectedShape.getLayoutBounds().getMinX())/2));
            this.selectedShape.setLayoutY(event.getY()-((selectedShape.getLayoutBounds().getMaxY()+selectedShape.getLayoutBounds().getMinY())/2));
        }
    }
    
}
