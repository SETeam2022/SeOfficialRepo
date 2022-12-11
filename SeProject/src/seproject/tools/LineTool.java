package seproject.tools;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import seproject.customComponents.DrawingArea;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;
import seproject.customComponents.LayeredPaper;

/**
 * This class is the representation of a specialized tool that can draw Lines
 * on the screen.
 */
public class LineTool extends DrawingTool {

    private Line line;

    /**
     * Create a new LineTool.
     *
     * @param paper is the pane on which the new line nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public LineTool(LayeredPaper paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }
    
    /**
     * This function will be called after a click with the mouse on the paper. It
     * will draw on the screen a line by adding a new node as a child of
     * the Pane working as a Paper.
     *
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for the start vertex of the line
     */
    @Override
    public void onMousePressed(MouseEvent event) {

        double startX = event.getX();
        double startY = event.getY();
        line = new Line(startX, startY, startX, startY);
        line.setStroke(this.getStrokeColorProperty().getValue());
        line.setFill(this.getFillColorProperty().getValue());
        line.setStrokeWidth(DrawingTool.widthStroke);
        Invoker.getInvoker().executeCommand(new DrawShapeCommand(line, getPaper()));

    }

    /**
     * This function will be called when I click the mouse on the paper and move
     * it and it will draw on the screen an updated line.
     *
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for Line's end vertex managing.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        line.setEndY(event.getY());
        line.setEndX(event.getX());
    }

    /**
     * This function will be called when the mouse is released after a drag. The
     * drawn line will be shown on the paper.
     * 
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for the Line's end vertex managing.
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        line.setEndY(event.getY());
        line.setEndX(event.getX());
    }
}
