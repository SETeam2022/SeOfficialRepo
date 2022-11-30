package seproject.tools;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * SelectedShapeManager on the screen.
 */
public class SelectedShapeManager {

    private Shape selectedShape = null;

    private final SimpleBooleanProperty shapeIsSelected;

    private static Pane paper;

    private static SelectedShapeManager ssm = null;

    private SelectedShapeManager() {
        this.shapeIsSelected = new SimpleBooleanProperty(false);
    }

    /**
     * This methods returns the only istnace of the selectedShapeManager
     */
    public static SelectedShapeManager getSelectedShapeManager() {
        if (ssm == null) {
            ssm = new SelectedShapeManager();
        }
        return ssm;
    }

    /**
     * This method must be called before the use of the SelectedShapeManager
     * @param paper the pane on witch the shape will be selected
     */
    public static void setSelectedShapeManagerPaper(Pane paper) {
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
        setNodeShadow(this.selectedShape);
        ssm.shapeIsSelected.setValue(true);
    }

    /**
     * Unselect the current selected shape in SSM class if it isn't NULL.
     */
    public void unsetSelectedShape() {
        if (ssm.selectedShape == null) {
            return;
        }
        ssm.selectedShape.setEffect(null);
        ssm.shapeIsSelected.setValue(false);
        ssm.selectedShape = null;
    }

    /**
     * @return shapeIsSelected an observable property that is true if there is
     * something selected else is false
     */
    public SimpleBooleanProperty getShapeIsSelectedProperty() {
        return ssm.shapeIsSelected;
    }

    /**
     * When this method is called if a shape has been selected it will be
     * deleted from the paper
     */
    public void deleteSelectedShape() {

        if (this.selectedShape == null) {
            return;
        }

        ssm.paper.getChildren().remove(this.selectedShape);
        ssm.selectedShape = null;
        ssm.shapeIsSelected.setValue(false);

    }
    
    /**
     * Change the fill color of the selected shape to a given color
     * @param color the new color for the fill of the shape
     */
    public void changeSelectedShapeFillColor(Color color) {
        if (ssm.selectedShape == null) {
            return;
        }
        ssm.selectedShape.setFill(color);
    }
    
    /**
     * Change the stroke color of the selected shape to a given color
     * @param color 
     */
    public void changeSelectedShapeStrokeColor(Color color) {
        if (ssm.selectedShape == null) {
            return;
        }
        ssm.selectedShape.setStroke(color);
    }

    private void setNodeShadow(Node node) {
        if (node == null) {
            return;
        }
        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(4.0f);
        ds1.setOffsetX(2.0f);
        node.setEffect(ds1);
    }
}
