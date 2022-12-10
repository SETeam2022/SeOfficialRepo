package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import seproject.customComponents.DrawingArea;
import seproject.tools.SelectedShapeManager;

/**
 * An object of this class represent the action of drawing a shape on the paper
 * the object also stores all the information neded for the undo of its
 * operation
 */
public class DrawShapeCommand implements Command {

    private final Shape shape;

    private final DrawingArea paper;

    /**
     * Create a DrawShapeCommand
     *
     * @param shape the shape that will be added to the paper
     * @param paper the paper on witch the shape will be added
     */
    public DrawShapeCommand(Shape shape, DrawingArea paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * Draw the shape on the paper
     */
    @Override
    public void execute() {
        paper.addShape(shape);
    }

    /**
     * Delete the drawed shape from the paper
     */
    @Override
    public void undo() {
        SelectedShapeManager.getSelectedShapeManager().unsetSelectedShape();
        paper.removeShape(shape);
    }

}
