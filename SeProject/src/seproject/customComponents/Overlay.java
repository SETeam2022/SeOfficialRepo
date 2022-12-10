package seproject.customComponents;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * This class represents the overlay shown when a shape is selected, that is
 * a selection box.
 *
 */
public class Overlay extends Rectangle {

    /**
     * Creates an a selection box in overlay to the selected shape.
     * 
     * @param selectedShape 
     */
    public Overlay(Shape selectedShape) {
        updateOverlay(selectedShape.getBoundsInParent());
        setMouseTransparent(true);
        setFill(Color.TRANSPARENT);
        setStroke(Color.CORNFLOWERBLUE);
        setStrokeWidth(3);
        getStrokeDashArray().addAll(3.0, 5.0);
        selectedShape.boundsInParentProperty().addListener((ObservableValue<? extends Bounds> ov, Bounds t, Bounds t1) -> {
            updateOverlay(t1);
        });
    }

    /**
     * Updates the overlay according to the given bounds.
     * 
     * @param bounds 
     */
    private void updateOverlay(Bounds bounds) {
        setX(bounds.getMinX());
        setY(bounds.getMinY());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
    }

}
    
