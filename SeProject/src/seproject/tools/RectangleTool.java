package seproject.tools;

import static java.lang.Math.abs;
import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import seproject.customComponents.DrawingArea;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * Rectangle on the screen.
 */
public class RectangleTool extends DrawingTool {

    private Rectangle rectangle;
    private double startX, startY;

    /**
     * Create a new RectangleTool
     *
     * @param paper is the pane on witch the new rectangle nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public RectangleTool(DrawingArea paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    /**
     * This function will be called after a click with the mouse on the paper it
     * will draw on the screen a rectangle by adding a new node as a child for
     * the Pane that works as a Paper
     *
     * @param event is the event that generated the call to this method its X
     * and Y coordinates will be used for setting up the top left corner of the
     * shape
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        rectangle = new Rectangle(startX, startY, 0, 0);
        rectangle.setStroke(this.getStrokeColorProperty().getValue());
        rectangle.setFill(this.getFillColorProperty().getValue());
        rectangle.setStrokeWidth(DrawingTool.widthStroke);
        Invoker.getInvoker().executeCommand(new DrawShapeCommand(rectangle, paper));

    }

    /**
     * This function will be called when I click the mouse on the paper and move
     * it on the paper and it will draw on the screen an update rectangle.
     *
     * @param event is the event that generated the call to this method its X
     * and Y coordinates will be used for rectangle's width and height managing.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {

        double newWidth = abs(startX - event.getX());
        double newHeight = abs(startY - event.getY());

        double newStartX = Math.min(startX, event.getX());
        double newStartY = Math.min(startY, event.getY());

        rectangle.setX(newStartX);
        rectangle.setY(newStartY);
        rectangle.setWidth(newWidth);
        rectangle.setHeight(newHeight);
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        double newWidth = abs(startX - event.getX());
        double newHeight = abs(startY - event.getY());

        double newStartX = Math.min(startX, event.getX());
        double newStartY = Math.min(startY, event.getY());


        rectangle.setX(newStartX);
        rectangle.setY(newStartY);
        rectangle.setWidth(newWidth);
        rectangle.setHeight(newHeight);
    }
}
