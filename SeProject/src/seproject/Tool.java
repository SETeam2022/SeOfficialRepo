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

/**
 *
 * @author teodo
 */
public abstract class Tool {

     Pane paper;

    private final ObjectProperty<Color> strokeColor;
    private final ObjectProperty<Color> fillColor;

    /**
     * @param paper is the pane on witch the new Shape nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public Tool(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        this.paper = paper;
        this.strokeColor = strokeColorProperty;
        this.fillColor = fillColorProperty;
    }

    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provides a conrete implementation by overriding the method
     *
     * @param event the JavaFx event associated with the mouse click
     */
    public void onMousePressed(MouseEvent event) {
    }

    ;
    
    /**
     * This method provides an empty implementation, the class that extends
     * Tool can provides a conrete implementation by overriding the method
     * @param event the JavaFx event associated with the mouse click
     */
    public void onMouseDragged(MouseEvent event) {
    }

    ;
    
    /**
     * @return the pane that works as a paper witch the tool is working
     */
    public Pane getPaper() {
        return paper;
    }

    /**
     * @param paper the pane on witch the tool will work
     */
    public void setPaper(Pane paper) {
        this.paper = paper;
    }

    /**
     * @param paint the color that will be used by the concrete tool for working
     * on the shapes stroke
     */
    /**
     * @return strokeColor the strokeColor setted for the tool
     */
    public Paint getStrokeColor() {
        return this.strokeColor.getValue();
    }

    /**
     * @param paint the color that will be used by the concrete tool for working
     * on the shapes fill
     */
    /**
     * @return fillColor the fillColor setted for the tool
     */
    public Paint getFillColor() {
        return this.fillColor.getValue();
    }

}
