package seproject.tools;

import editor.ShapeEditorFactory;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import seproject.DrawingArea;
import seproject.commands.ChangeFillColorCommand;
import seproject.commands.ChangeStrokeColorCommand;
import seproject.commands.DeleteShapeCommand;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;
import seproject.commands.ResizeCommand;
import seproject.commands.*;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * SelectedShapeManager on the screen.
 */
public class SelectedShapeManager {

    private Shape selectedShape = null;

    private final DoubleProperty widthProperty, heightProperty,rotationProperty;

    private final SimpleBooleanProperty shapeIsSelectedProperty;

    private final SimpleBooleanProperty shapeIsCopiedProperty;

    private static DrawingArea paper;

    private static SelectedShapeManager ssm = null;

    private Shape copiedShape = null;

    private Overlay overlay;

    private int incrementCopy = 0;

    private SelectedShapeManager() {

        this.widthProperty = new SimpleDoubleProperty();
        this.heightProperty = new SimpleDoubleProperty();
        this.rotationProperty = new SimpleDoubleProperty();
        this.shapeIsSelectedProperty = new SimpleBooleanProperty(false);
        this.shapeIsCopiedProperty = new SimpleBooleanProperty(false);

    }

    /**
     * This methods returns the only instance of the selectedShapeManager
     *
     * @return ssm
     */
    public static SelectedShapeManager getSelectedShapeManager() {
        if (ssm == null) {
            ssm = new SelectedShapeManager();
        }
        return ssm;
    }

    /**
     * This method must be called before the use of the SelectedShapeManager
     *
     * @param paper the pane on witch the shape will be selected
     */
    public static void setSelectedShapeManagerPaper(DrawingArea paper) {
        SelectedShapeManager.paper = paper;
    }

    /**
     *
     * @return selected shape
     */
    public Shape getSelectedShape() {
        return selectedShape;
    }

    /**
     *
     * @param selectedShape set the selected shape an adds the selection effect
     */
    public void setSelectedShape(Shape selectedShape) {
        ssm.selectedShape = selectedShape;

        overlay = new Overlay();
        paper.getChildren().add(overlay);

        ssm.widthProperty.setValue(ssm.getSelectedShape().getLayoutBounds().getWidth());
        ssm.heightProperty.setValue(ssm.getSelectedShape().getLayoutBounds().getHeight());
        ssm.rotationProperty.setValue(ssm.getSelectedShape().getRotate());
        ssm.shapeIsSelectedProperty.setValue(true);
        ssm.incrementCopy = 0;
    }

    /**
     * Unselect the current selected shape in SSM class if it isn't NULL.
     */
    public void unsetSelectedShape() {
        if (ssm.selectedShape == null) {
            return;
        }
        paper.getChildren().remove(overlay);
        ssm.shapeIsSelectedProperty.setValue(false);
        ssm.selectedShape = null;
    }

    /**
     * @return shapeIsSelectedProperty an observable property that is true if
     * there is something selected else is false
     */
    public SimpleBooleanProperty getShapeIsSelectedProperty() {
        return ssm.shapeIsSelectedProperty;
    }

    /**
     *
     * @return widthProperty an observable property which contains the width of
     * the current shape's bounds
     */
    public DoubleProperty getWidthProperty() {
        return ssm.widthProperty;
    }

    /**
     *
     * @return widthProperty an observable property which contains the height of
     * the current shape's bounds
     */
    public DoubleProperty getHeightProperty() {
        return ssm.heightProperty;
    }
    
    public DoubleProperty getRotationProperty(){
        return ssm.rotationProperty;
    }

    /**
     * When this method is called if a shape has been selected it will be
     * deleted from the paper
     */
    public void deleteSelectedShape() throws RuntimeException {

        if (this.selectedShape == null) {
            return;
        }

        if (SelectedShapeManager.paper == null) {
            throw new RuntimeException("You have to call the configuration method first, no working Pane is setted");
        }

        Invoker.getInvoker().executeCommand(new DeleteShapeCommand(this.selectedShape, paper));

        /* TODO: I have to delete the shape from the map too */
        paper.getChildren().remove(overlay);
        ssm.selectedShape = null;
        ssm.shapeIsSelectedProperty.setValue(false);

    }

    /**
     * Change the fill color of the selected shape to a given color
     *
     * @param color the new color for filling the shape
     */
    public void changeSelectedShapeFillColor(Color color) {
        if (ssm.selectedShape == null) {
            return;
        }
        Invoker.getInvoker().executeCommand(new ChangeFillColorCommand(color, ssm.selectedShape));
    }

    /**
     * Change the stroke color of the selected shape to a given color
     *
     * @param color the new color for the stroke of the shape
     */
    public void changeSelectedShapeStrokeColor(Color color) {
        if (ssm.selectedShape == null) {
            return;
        }
        Invoker.getInvoker().executeCommand(new ChangeStrokeColorCommand(color, ssm.selectedShape));
    }

    /*-------------------------------------------BRING TO FRONT AND BRING TO BACK ---------------------------------------------------------------*/
    /**
     * Bring the selected shape on top layer
     */
    public void bringToFrontShape() {
        if (ssm.selectedShape == null) {
            return;
        }
        Invoker.getInvoker().executeCommand(new BringToFrontCommand(ssm.selectedShape, paper));
    }

    /**
     * Bring the selected shape on down layer
     */
    public void bringToBackShape() {
        if (ssm.selectedShape == null) {
            return;
        }
        Invoker.getInvoker().executeCommand(new BringToBackCommand(ssm.selectedShape, paper));
    }


    /*-------------------------------------------CUT COPY AND PASTE ---------------------------------------------------------------*/
    /**
     * This method performs the copy of the selected shape.
     */
    public void copySelectedShape() {
        if (selectedShape == null) {
            return;
        }
        this.copiedShape = selectedShape;
        this.shapeIsCopiedProperty.setValue(true);

    }

    /**
     * This method performs the paste of the selected shape.
     */
    public void pasteShape() {
        if (copiedShape == null) {
            return;
        }
        incrementCopy += 10;
        Bounds paperBounds = paper.getLayoutBounds();
        Shape clone = ShapeEditorFactory.getInstance(copiedShape.getClass()).clone(copiedShape);
        clone.relocate(copiedShape.getBoundsInParent().getMinX() + incrementCopy, copiedShape.getBoundsInParent().getMinY() + incrementCopy);
        Invoker.getInvoker().executeCommand(new DrawShapeCommand(clone, paper));
    }

    /**
     * This method performs the cut of the selected shape.
     */
    public void cutShape() {
        this.copySelectedShape();
        ssm.deleteSelectedShape();
    }

    /**
     *
     * @return shapeIsCopiedProperty which allows to know if the selected shape
     * has been copied
     */
    public SimpleBooleanProperty getShapeIsCopiedProperty() {
        return this.shapeIsCopiedProperty;
    }

    /*--------------------------------------------------------------------RESIZE-----------------------------------------------------*/
    /**
     * This method performs the resize of the selected shape.
     *
     * @param width
     * @param height
     */
    public void resizeSelectedShape(double width, double height) {
        if (selectedShape == null) {
            return;
        }
        Invoker.getInvoker().executeCommand(new ResizeCommand(selectedShape, width, height));
    }


    /*--------------------------------------------------------------------OVERLAY-----------------------------------------------------*/
    /**
     * This class represents the overlay shown when a shape is selected, that is
     * a selection box.
     *
     */
    private class Overlay extends Rectangle {

        private Overlay() {
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

        private void updateOverlay(Bounds t1) {
            setX(t1.getMinX());
            setY(t1.getMinY());
            setWidth(t1.getWidth());
            setHeight(t1.getHeight());
        }
    }
    
    /* -------------------------------------------------------------- ROTATION --------------------------------------------------------------*/
    /**
     * This method allow to rotate the shape due to param
     * @param value
     */
    public void rotationShape(Double value){
        if (selectedShape == null){
            return;
        }
        Invoker.getInvoker().executeCommand(new RotationCommand(value, selectedShape));
    }
}
