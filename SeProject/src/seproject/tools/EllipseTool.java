package seproject.tools;

import static java.lang.Math.abs;
import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;
import seproject.customComponents.LayeredPaper;

/**
 * This class is the representation of a specialized tool which can draw
 * Ellipses on the screen.
 */
public class EllipseTool extends DrawingTool {

    private Ellipse ell;
    private double startX, startY;

    /**
     * Creates a new EllipseTool.
     *
     * @param paper is the pane on which the new ellipses nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public EllipseTool(LayeredPaper paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    /**
     * This function will be called after a click with the mouse on the paper. It
     * will draw on the screen an ellipse by adding a new node as a child of
     * the Pane working as a Paper.
     *
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for the center of the ellipse
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        ell = new Ellipse(event.getX(), event.getY(), 0, 0);
        ell.setStroke(this.getStrokeColorProperty().getValue());
        ell.setFill(this.getFillColorProperty().getValue());
        ell.setStrokeWidth(Constants.STROKE_WIDTH);
        Invoker.getInvoker().executeCommand(new DrawShapeCommand(ell, getPaper()));
    }

    /**
     * This function will be called when I click the mouse on the paper and move
     * it and it will draw on the screen an updated ellipse.
     *
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for ellipse's radius managing.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        ell.setRadiusX(abs(startX - event.getX()));
        ell.setRadiusY(abs(startY - event.getY()));
    }

    /**
     * This function will be called when the mouse is released after a drag. The
     * drawn ellipse will be shown on the paper.
     * 
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for ellipse's radius managing.
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        ell.setRadiusX(abs(startX - event.getX()));
        ell.setRadiusY(abs(startY - event.getY()));
    }

}
