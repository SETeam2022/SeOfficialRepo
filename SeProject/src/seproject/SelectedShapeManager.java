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
        if (this.selectedShape != null){
        
            this.selectedShape.strokeProperty().unbind();
            this.selectedShape.fillProperty().unbind();
            this.selectedShape.setEffect(null);
            this.selectedShape = null;
            
        }
        this.shapeIsSelected.setValue(false);
        for (Node node : this.getPaper().getChildren()) {
            Shape tmp = (Shape) node;
            if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                DropShadow ds1 = new DropShadow();
                ds1.setOffsetY(4.0f);
                ds1.setOffsetX(2.0f);
                node.setEffect(ds1);
                this.setSelectedShape(tmp);
                this.shapeIsSelected.setValue(true);
            } 
        }
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        if (this.getSelectedShape() != null) {
            this.selectedShape.setLayoutX((event.getX() - selectedShape.getLayoutBounds().getMinX()));
            this.selectedShape.setLayoutY((event.getY() - selectedShape.getLayoutBounds().getMinY()));
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
        if (this.selectedShape != null) {
            this.selectedShape.strokeProperty().unbind();
            this.selectedShape.fillProperty().unbind();
        }
        this.selectedShape = selectedShape;
        this.getFillColorProperty().setValue(Color.valueOf(this.selectedShape.getFill().toString()));
        this.getStrokeColorProperty().setValue(Color.valueOf(this.selectedShape.getStroke().toString()));
        this.selectedShape.strokeProperty().bind(this.getStrokeColorProperty());
        this.selectedShape.fillProperty().bind(this.getFillColorProperty());
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

    public void deselect(){
        if (this.selectedShape == null) return;
        this.selectedShape.strokeProperty().unbind();
        this.selectedShape.fillProperty().unbind();
        this.selectedShape.setEffect(null);
        this.shapeIsSelected.setValue(false);
    }
}
