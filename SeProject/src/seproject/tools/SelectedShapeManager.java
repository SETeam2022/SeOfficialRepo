package seproject.tools;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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

    private final DoubleProperty widthProperty, heightProperty;

    private final SimpleBooleanProperty shapeIsSelectedProperty;

    private final SimpleBooleanProperty shapeIsCopiedProperty;

    private static Pane paper;

    private static SelectedShapeManager ssm = null;

    private Shape copiedShape = null;

    private final Group group;

    private Overlay overlay;

    private SelectedShapeManager() {

        this.widthProperty = new SimpleDoubleProperty();
        this.heightProperty = new SimpleDoubleProperty();
        this.shapeIsSelectedProperty = new SimpleBooleanProperty(false);
        this.shapeIsCopiedProperty = new SimpleBooleanProperty(false);
        group = new Group();
        group.setMouseTransparent(true);
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
        paper.getChildren().add(SelectedShapeManager.getSelectedShapeManager().group);
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
        overlay = new Overlay(selectedShape);
        group.getChildren().add(overlay);
        group.toFront();
        ssm.widthProperty.setValue(ssm.getSelectedShape().getLayoutBounds().getWidth());
        ssm.heightProperty.setValue(ssm.getSelectedShape().getLayoutBounds().getHeight());
        ssm.shapeIsSelectedProperty.setValue(true);
    }

    /**
     * Unselect the current selected shape in SSM class if it isn't NULL.
     */
    public void unsetSelectedShape() {
        if (ssm.selectedShape == null) {
            return;
        }
        group.getChildren().remove(overlay);
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
        group.getChildren().remove(overlay);
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
        if (ssm.selectedShape == null) {
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try ( XMLEncoder encoder = new XMLEncoder(baos)) {
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.writeObject(ssm.selectedShape);
        }
        String codedShape = new String(baos.toByteArray());
        try ( XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(codedShape.getBytes()))) {
            this.copiedShape = (Shape) decoder.readObject();
            this.shapeIsCopiedProperty.setValue(true);
        }

    }

    /**
     * This method performs the paste of the selected shape.
     */
    public void pasteShape() {
        if (copiedShape != null) {
            Bounds paperBounds = paper.getLayoutBounds();
            copiedShape.relocate((paperBounds.getWidth() - copiedShape.getLayoutBounds().getWidth()) / 2, (paperBounds.getHeight() - copiedShape.getLayoutBounds().getHeight()) / 2);
            Invoker.getInvoker().executeCommand(new DrawShapeCommand(copiedShape, paper));
        }
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
}

/*--------------------------------------------------------------------OVERLAY-----------------------------------------------------*/
/**
 * This class represents the overlay shown when a shape is selected, that is a
 * selection box.
 *
 */
class Overlay extends Rectangle {

    final Shape selectedShape;
    private ChangeListener<Bounds> overlayChangeListener;

    Overlay(Shape selectedShape) {
        setX(selectedShape.getLayoutBounds().getMinX());
        setY(selectedShape.getLayoutBounds().getMinY());
        setWidth(selectedShape.getLayoutBounds().getWidth());
        setHeight(selectedShape.getLayoutBounds().getHeight());
        setFill(Color.TRANSPARENT);
        setStroke(Color.CORNFLOWERBLUE);
        setStrokeWidth(3);
        getStrokeDashArray().addAll(3.0, 5.0);
        this.selectedShape = selectedShape;
        monitorOverlay();
    }

    /**
     * This method adds an observer on the boundsInParent of the selected shape,
     * in order to detect a change and consequently resize tha overlay according
     * to it.
     */
    void monitorOverlay() {
        final ReadOnlyObjectProperty<Bounds> bounds;
        bounds = selectedShape.boundsInParentProperty();
        updateOverlay(bounds.get());
        overlayChangeListener = new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                updateOverlay(newValue);
            }
        };
        bounds.addListener(overlayChangeListener);
    }

    /**
     * This method, takes in input the newBounds on the selected shape and
     * resizes the selection overlay according to them.
     *
     * @param newBounds
     */
    private void updateOverlay(Bounds newBounds) {
        setX(newBounds.getMinX());
        setY(newBounds.getMinY());
        setWidth(newBounds.getWidth());
        setHeight(newBounds.getHeight());
    }
}
