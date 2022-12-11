package seproject.commands;

import javafx.scene.shape.Shape;
import seproject.customComponents.DrawingArea;
import seproject.customComponents.LayeredPaper;
import seproject.tools.SelectedShapeManager;

/**
 * An object of this class represents the action of drawing a shape on the paper.
 * The object also stores all the information needed for the undo of its operation.
 * 
 */
public class DrawShapeCommand implements Command {

    private final Shape shape;

    private final LayeredPaper paper;

    /**
     * Creates a DrawShapeCommand.
     *
     * @param shape the shape that will be added to the paper
     * @param paper the paper on which the shape will be added
     */
    public DrawShapeCommand(Shape shape, LayeredPaper paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * Draws the shape on the paper.
     */
    @Override
    public void execute() {
        paper.addInPaper(shape);
    }

    /**
     * Deletes the drawn shape from the paper.
     */
    @Override
    public void undo() {
        SelectedShapeManager.getSelectedShapeManager().unsetSelectedShape();
        paper.removeFromPaper(shape);
    }

}
