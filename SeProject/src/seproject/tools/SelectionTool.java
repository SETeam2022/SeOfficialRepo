/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author teodo
 */
public class SelectionTool extends Tool {

    private Rectangle selectionRectangle;
    private final SelectedShapeManager manager;

    public SelectionTool(Pane paper) {
        super(paper);
        selectionRectangle = new Rectangle();
        this.selectionRectangle.setStroke(Color.CORNFLOWERBLUE);
        this.selectionRectangle.setStrokeWidth(3);
        this.selectionRectangle.setFill(Color.TRANSPARENT);
        this.selectionRectangle.getStrokeDashArray().addAll(3.0, 5.0);
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
                manager.setSelectedShape(tmp, this.selectionRectangle);
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
            selectedShape.setLayoutX(event.getX() - ((selectedShape.getLayoutBounds().getMaxX() + selectedShape.getLayoutBounds().getMinX()) / 2));
            selectedShape.setLayoutY(event.getY() - ((selectedShape.getLayoutBounds().getMaxY() + selectedShape.getLayoutBounds().getMinY()) / 2));
            this.selectionRectangle.setX(selectedShape.getBoundsInParent().getMinX());
            this.selectionRectangle.setY(selectedShape.getBoundsInParent().getMinY());
        }
    }

}
