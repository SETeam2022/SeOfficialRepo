package seproject.tools;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This class is the rappresentation of a unspecialized abstract tool. In order
 * to allow concrete shape creations this class must be concretized.
 */
public abstract class Tool {

    Pane paper;

    private final ObjectProperty<Color> strokeColorProperty;

    public ObjectProperty<Color> getStrokeColorProperty() {
        return strokeColorProperty;
    }

    public ObjectProperty<Color> getFillColorProperty() {
        return fillColorProperty;
    }
    private final ObjectProperty<Color> fillColorProperty;

    /**
     * @param paper is the pane on witch the new Shape nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public Tool(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        this.paper = paper;
        this.strokeColorProperty = strokeColorProperty;
        this.fillColorProperty = fillColorProperty;
    }

    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provides a conrete implementation by overriding the method
     *
     * @param event the JavaFx event associated with the mouse click
     */
    public void onMousePressed(MouseEvent event) {}

    
    /**
     * This method provides an empty implementation, the class that extends
     * Tool can provides a conrete implementation by overriding the method
     * 
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
     * @return strokeColor the strokeColor setted for the tool
     */
    public Paint getStrokeColor() {
        return this.strokeColorProperty.getValue();
    }

    /**
     * @return fillColor the fillColor setted for the tool
     */
    public Paint getFillColor() {
        return this.fillColorProperty.getValue();
    }

}
