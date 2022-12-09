package seproject.tools;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import seproject.DrawingArea;

public abstract class DrawingTool extends Tool {

    static final double widthStroke = 2.5;

    private final ObjectProperty<Color> strokeColorProperty;
    private final ObjectProperty<Color> fillColorProperty;

    public DrawingTool(DrawingArea paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper);
        this.strokeColorProperty = strokeColorProperty;
        this.fillColorProperty = fillColorProperty;
    }

    public ObjectProperty<Color> getStrokeColorProperty() {
        return strokeColorProperty;
    }

    public ObjectProperty<Color> getFillColorProperty() {
        return fillColorProperty;
    }

}
