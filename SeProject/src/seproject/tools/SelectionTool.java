package seproject.tools;

import seproject.Overlay;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import seproject.DrawingArea;
import seproject.commands.*;
import seproject.*;

public class SelectionTool extends Tool {

    private final SelectedShapeManager manager;
    private double startX, startY, offsetX, offsetY;
    private boolean shapeHasBeenDragged;
    
    private Overlay overlay;
    private Shape selectedShape = null;
    
    private SimpleDoubleProperty scaleX;
    private SimpleDoubleProperty scaleY;

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
        //SelectedShapeManager.getSelectedShapeManager().unsetSelectedShape();
        Object eventNode = event.getTarget();
        if (eventNode instanceof Shape) {
                Shape tmp = (Shape) eventNode;      
                if(selectedShape== null|| !selectedShape.equals(tmp)){
                    deselect();
                    selectedShape = tmp;
                    overlay = new Overlay(selectedShape);
                    paper.getContainerOfPaperAndGrid().getChildren().add(overlay);
                    manager.setSelectedShape(selectedShape);
                    overlay.toFront();
                    System.out.println("Sono un overlay");
                }
                startX = selectedShape.getTranslateX(); 
                startY = selectedShape.getTranslateY(); 
                offsetX = event.getSceneX()/scaleX.getValue() - selectedShape.getTranslateX();
                offsetY = event.getSceneY()/scaleY.getValue()- selectedShape.getTranslateY();
        } else {
            deselect();
            selectedShape = null;
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
        //Shape selectedShape = manager.getSelectedShape();
        if (selectedShape != null) {
            selectedShape.setTranslateX(event.getSceneX()/scaleX.getValue() - offsetX);
            selectedShape.setTranslateY(event.getSceneY()/scaleY.getValue() - offsetY);
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
        if (selectedShape != null && shapeHasBeenDragged) {
            Invoker.getInvoker().executeCommand(new TranslationCommand(selectedShape, offsetX, offsetY, startX, startY,scaleX.getValue(),scaleY.getValue(),event.getSceneX(),event.getSceneY()));
        }
        shapeHasBeenDragged = false;
    }

    @Override
    public void deselect() {
        if (selectedShape == null) return;
        manager.unsetSelectedShape();
        paper.getContainerOfPaperAndGrid().getChildren().remove(overlay);
    }
    
    
}
