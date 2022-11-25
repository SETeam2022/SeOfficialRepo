package seproject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * This class allows to select a shape on the screen.
 */
public class SelectedShapeManager extends Tool {

    private Shape selectedShape = null;

    private final SimpleBooleanProperty shapeIsSelected;

    public SelectedShapeManager(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
        shapeIsSelected = new SimpleBooleanProperty(false);
    }

    /**
     * This function will be called after a click with the mouse on the paper it
     * will select a shape on the screen.
     *
     * @param event is the event that generated the call to this method
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        this.unsetSelectedShape();
        Object eventNode = event.getTarget();
        if (eventNode instanceof Shape) {
            Shape tmp = (Shape) eventNode;
            if (tmp.getBoundsInParent().contains(event.getX(), event.getY())) {
                this.setSelectedShape(tmp);
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
        if (this.getSelectedShape() != null) {
            this.selectedShape.relocate(event.getX(), event.getY());

        }
    }

    ;
    /**
    * 
    * @return selected shape
    */
    public Shape getSelectedShape() {
        return selectedShape;
    }

    /**
     *
     * @param selectedShape set the selected shape
     */
    private void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
        this.bindShapePaintProperty();
        setNodeShadow(this.selectedShape);
        this.shapeIsSelected.setValue(true);
    }

    public void unsetSelectedShape() {
        if (this.selectedShape == null) {
            return;
        }
        unbindShapePaintProperty(this.selectedShape);
        this.selectedShape.setEffect(null);
        this.shapeIsSelected.setValue(false);
        this.selectedShape = null;
    }

    /**
     * @return shapeIsSelected an observable property that is true if there is
     * something selected else is fase
     */
    public SimpleBooleanProperty getShapeIsSelectedProperty() {
        return shapeIsSelected;
    }

    /**
     * When this method is called if a shape has been selected it will be
     * deleted from the paper
     */
    public void deleteSelectedShape() {

        if (this.selectedShape == null) {
            return;
        }

        this.getPaper().getChildren().remove(this.selectedShape);
        this.selectedShape = null;
        this.shapeIsSelected.setValue(false);
    }

    private static void unbindShapePaintProperty(Shape shape) {
        if (shape == null) {
            return;
        }
        shape.strokeProperty().unbind();
        shape.fillProperty().unbind();
    }

    private void bindShapePaintProperty() {
        if (this.selectedShape == null) {
            return;
        }
        this.getFillColorProperty().setValue(Color.valueOf(this.selectedShape.getFill().toString()));
        this.getStrokeColorProperty().setValue(Color.valueOf(this.selectedShape.getStroke().toString()));
        this.selectedShape.strokeProperty().bind(this.getStrokeColorProperty());
        this.selectedShape.fillProperty().bind(this.getFillColorProperty());
    }

    private static void setNodeShadow(Node node) {
        if (node == null) {
            return;
        }
        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(4.0f);
        ds1.setOffsetX(2.0f);
        node.setEffect(ds1);
    }
}
