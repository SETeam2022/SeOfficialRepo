package seproject.tools;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import seproject.customComponents.DrawingArea;
import seproject.customComponents.LayeredPaper;

/**
 * This class wants to represent the generic drawing tool.
 * 
 */
public abstract class DrawingTool extends Tool {

    static final double widthStroke = 2.5;

    private final ObjectProperty<Color> strokeColorProperty;
    private final ObjectProperty<Color> fillColorProperty;

    /**
     * Drawing Tool creation.
     * 
     * @param paper is the pane on which the new shape nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public DrawingTool(LayeredPaper paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper);
        this.strokeColorProperty = strokeColorProperty;
        this.fillColorProperty = fillColorProperty;
    }

    /**
     * 
     * @return the strokeColorProperty
     */
    public ObjectProperty<Color> getStrokeColorProperty() {
        return strokeColorProperty;
    }

    /**
     * 
     * @return the fillColorProperty
     */
    public ObjectProperty<Color> getFillColorProperty() {
        return fillColorProperty;
    }

}
