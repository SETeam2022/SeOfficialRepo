package seproject.tools;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import seproject.commands.*;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * SelectedShapeManager on the screen.
 */
public class SelectedShapeManager {

    private Shape selectedShape = null;

    private Rectangle selectionRectangle = null;

    private final SimpleBooleanProperty shapeIsSelected;
    private final DoubleProperty widthProperty, heightProperty;

    private static Pane paper;

    private static SelectedShapeManager ssm = null;

    private SelectedShapeManager() {
        this.shapeIsSelected = new SimpleBooleanProperty(false);
        this.widthProperty = new SimpleDoubleProperty();
        this.heightProperty = new SimpleDoubleProperty();
    }

    /**
     * This methods returns the only instance of the selectedShapeManager
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
     * @param selectionRectangle
     */
    public void setSelectedShape(Shape selectedShape, Rectangle selectionRectangle) {
        ssm.selectedShape = selectedShape;
        ssm.selectionRectangle = selectionRectangle;
        showSelectionBox(this.selectedShape);
        ssm.shapeIsSelected.setValue(true);
        ssm.widthProperty.setValue(ssm.getSelectedShape().getLayoutBounds().getWidth());
        ssm.heightProperty.setValue(ssm.getSelectedShape().getLayoutBounds().getHeight());
    }

    /**
     * Unselect the current selected shape in SSM class if it isn't NULL.
     */
    public void unsetSelectedShape() {
        if (ssm.selectedShape == null) {
            return;
        }
        ssm.shapeIsSelected.setValue(false);
        SelectedShapeManager.paper.getChildren().remove(ssm.selectionRectangle);
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
     * 
     * @return widthProperty an observable property which contains the width of
     * the current shape's bounds
     */
    public DoubleProperty getWidthProperty(){
        return ssm.widthProperty;
    }
    
    /**
     * 
     * @return widthProperty an observable property which contains the height of
     * the current shape's bounds
     */
    public DoubleProperty getHeightProperty(){
        return ssm.heightProperty;
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

        SelectedShapeManager.paper.getChildren().remove(ssm.selectionRectangle);
        Invoker.getInvoker().executeCommand(new DeleteShapeCommand(selectedShape,paper));
        ssm.selectedShape = null;
        ssm.shapeIsSelected.setValue(false);

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
        Invoker.getInvoker().executeCommand(new ChangeFillColorCommand(color,ssm.selectedShape.getFill(),ssm.selectedShape));
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
        Invoker.getInvoker().executeCommand(new ChangeStrokeColorCommand(color,ssm.selectedShape.getStroke(),ssm.selectedShape));
    }

    /**
     * 
     * @param node 
     */
    private void showSelectionBox(Node node) {
        if (node == null) {
            return;
        }
        this.selectionRectangle.setX(this.selectedShape.getBoundsInParent().getMinX());
        this.selectionRectangle.setY(this.selectedShape.getBoundsInParent().getMinY());
        this.selectionRectangle.setWidth(this.selectedShape.getBoundsInParent().getWidth());
        this.selectionRectangle.setHeight(this.selectedShape.getBoundsInParent().getHeight());
        this.selectionRectangle.setMouseTransparent(true);
        SelectedShapeManager.paper.getChildren().add(selectionRectangle);
        this.selectionRectangle.toBack();
    }
    
    /**
     * 
     * @param width
     * @param height 
     */
    public void resizeSelectedShape(double width, double height){
        // read shape as string
        String s = "";
        try (XMLEncoder encoder = new XMLEncoder(System.out.append(s))) {
            encoder.writeObject(ssm.selectedShape);
        }
        System.out.println(s);

    }
}
