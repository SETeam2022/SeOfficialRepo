package seproject.tools;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import seproject.commands.ChangeFillColorCommand;
import seproject.commands.ChangeStrokeColorCommand;
import seproject.commands.DeleteShapeCommand;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * SelectedShapeManager on the screen.
 */
public class SelectedShapeManager {

    private Shape selectedShape = null;

    private Rectangle selectionRectangle = null;

    private final SimpleBooleanProperty shapeIsSelectedProperty;
    
    private final SimpleBooleanProperty shapeIsCopiedProperty;

    private static Pane paper;

    private static SelectedShapeManager ssm = null;

    private Shape copiedShape = null;

    private SelectedShapeManager() {
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
        ssm.shapeIsSelectedProperty.setValue(true);
    }

    /**
     * Unselect the current selected shape in SSM class if it isn't NULL.
     */
    public void unsetSelectedShape() {
        if (ssm.selectedShape == null) {
            return;
        }
        ssm.shapeIsSelectedProperty.setValue(false);
        SelectedShapeManager.paper.getChildren().remove(ssm.selectionRectangle);
        ssm.selectedShape = null;
    }

    /**
     * @return shapeIsSelectedProperty an observable property that is true if there is
 something selected else is false
     */
    public SimpleBooleanProperty getShapeIsSelectedProperty() {
        return ssm.shapeIsSelectedProperty;
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
        Invoker.getInvoker().executeCommand(new DeleteShapeCommand(this.selectedShape, paper));
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
    
    
    /*-------------------------------------------CUT COPY AND PASTE ---------------------------------------------------------------*/
    
    public void copySelectedShape(){
        if (ssm.selectedShape == null) return;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (XMLEncoder encoder = new XMLEncoder(baos)) {
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.writeObject(ssm.selectedShape);
        }
        String codedShape = new String(baos.toByteArray());
        try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(codedShape.getBytes()))) {
            this.copiedShape  = (Shape) decoder.readObject();
            this.shapeIsCopiedProperty.setValue(true);
        }
        
    }
    
    public void pasteShape(){
        if (copiedShape != null){
            Bounds paperBounds = paper.getLayoutBounds();
            copiedShape.relocate((paperBounds.getWidth() - copiedShape.getLayoutBounds().getWidth()) / 2, (paperBounds.getHeight() - copiedShape.getLayoutBounds().getHeight()) / 2);
            Invoker.getInvoker().executeCommand(new DrawShapeCommand(copiedShape,paper));
        }
    }
    
    public void cutShape(){
        this.copySelectedShape();
        ssm.deleteSelectedShape();
    }
    
    public SimpleBooleanProperty getShapeIsCopiedProperty(){
        return this.shapeIsCopiedProperty;
    }
    
    /*-------------------------------------------------------------------------------------------------------------------------------*/

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


}
