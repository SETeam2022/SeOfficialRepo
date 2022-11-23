/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * Ellipses on the screen.
 */
public class EllipseTool extends Tool {

    /**
     * Create a new EllipseTool
     *
     * @param paper is the pane on witch the new ellipses nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public EllipseTool(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    /**
     * This function will be called after a click with the mouse on the paper it
     * will draw on the screen an ellipse by adding a new node as a child for
     * the Pane that works as a Paper
     *
     * @param event is the event that generated the call to this method its X
     * and Y coordinates will be used for the center of the ellipse
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        Ellipse ell = new Ellipse(event.getX(), event.getY(), 150, 300);
        ell.setStroke(this.getStrokeColor());
        ell.setFill(this.getFillColor());
        this.getPaper().getChildren().add(ell);
    }
;

}
