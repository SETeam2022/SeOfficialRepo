/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.tools;

import static java.lang.Math.abs;
import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * Rectangle on the screen.
 */
public class RectangleTool extends Tool {

    private Rectangle rectangle;
    private double startX, startY;

    /**
     * Create a new RectangleTool
     *
     * @param paper is the pane on witch the new ellipses nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public RectangleTool(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    /**
     * This function will be called after a click with the mouse on the paper it
     * will draw on the screen a rectangle by adding a new node as a child for
     * the Pane that works as a Paper
     *
     * @param event is the event that generated the call to this method its X
     * and Y coordinates will be used for the top left cornet of the shape
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        rectangle = new Rectangle(startX, startY, 0, 0);
        rectangle.setStroke(this.getStrokeColor());
        rectangle.setFill(this.getFillColor());
        this.getPaper().getChildren().add(rectangle);

    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        rectangle.setWidth(abs(startX - event.getX()));
        rectangle.setHeight(abs(startY - event.getY()));
    }
}
