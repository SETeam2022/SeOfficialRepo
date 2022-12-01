package seproject.tools;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * Lines on the screen.
 */
public class LineTool extends DrawingTool {

    private Line line;

    /**
     * Create a new LineTool
     *
     * @param paper is the pane on witch the new line nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public LineTool(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    /**
     * This method refreshes the creation of the line before
     * dropping it on the paper.
     *
     * @param event is the dragging movement around the pane.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        line.setEndY(event.getY());
        line.setEndX(event.getX());
    }

    /**
     * This method starts the creation of the line setting up an
     * initial position.
     *
     * @param event is the dragging movement around the pane.
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        
        double startX = event.getX();
        double startY = event.getY();
        line = new Line(startX, startY, startX, startY);
        line.setStroke(this.getStrokeColorProperty().getValue());
        line.setFill(this.getFillColorProperty().getValue());
        line.setStrokeWidth(DrawingTool.widthStroke);
        Invoker.getInvoker().executeCommand(new DrawShapeCommand(line,paper));
        
    }
    
    @Override
    public void onMouseReleased(MouseEvent event){
        line.setEndY(event.getY());
        line.setEndX(event.getX());
    }
}
