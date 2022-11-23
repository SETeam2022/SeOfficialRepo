/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 *
 * @author alewi
 */
public class LineTool extends Tool {

    private Line line;

    /**
     * Create a LineTool
     *
     * @param paper is the pane on witch the new line nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public LineTool(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    /**
     * onMouseDragghed method. It refreshes the creation of the line before
     * dropping it on the paper.
     *
     * @param event is the dragging movement around the pane.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        line.setEndX(event.getX());
        line.setEndY(event.getY());
    }

    /**
     * onMouseClick method. It starts the creation of the line setting up an
     * initial position.
     *
     * @param event is the dragging movement around the pane.
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        double startX = event.getX();
        double startY = event.getY();
        line = new Line(startX, startY, startX, startY);
        line.setStroke(this.getStrokeColor());
        line.setFill(this.getFillColor());
        this.getPaper().getChildren().add(line);
    }

}