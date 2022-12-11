package seproject.commands;

import javafx.scene.shape.Shape;
import seproject.customComponents.LayeredPaper;

/**
 * An object of this class represents the action of deleting a shape from the
 * pane (representing the paper). The object also stores all the information
 * needed for the undo of its operation.
 */
public class DeleteShapeCommand implements Command {

    private final Shape shape;
    private final LayeredPaper paper;

    /**
     * Creates a DeleteShapeCommand.
     *
     * @param shape the shape that will be deleted
     * @param paper the pane on witch the shape is rendered
     */
    public DeleteShapeCommand(Shape shape, LayeredPaper paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * Deletes the shape from the paper.
     */
    @Override
    public void execute() {
        paper.removeFromPaper(shape);

    }

    /**
     * Redraws the previously deleted shape.
     */
    @Override
    public void undo() {
        paper.addInPaper(shape);
    }

}
