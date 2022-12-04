package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 * An object of this class represent the action of deleteing a shape from the
 * pane that works as a paper the object also stores all the information neded
 * for the undo of its operation
 */
public class DeleteShapeCommand implements Command {

    private final Shape shape;
    private final Pane paper;

    /**
     * Create a DeleteShapeCommand
     *
     * @param shape the shape that will be deleted
     * @param paper the pane on witch the shape is rendered
     */
    public DeleteShapeCommand(Shape shape, Pane paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * Effectively delete the shape from the paper
     */
    @Override
    public void execute() {
        paper.getChildren().remove(shape);

    }

    /**
     * Redraw the shape previously deleted
     */
    @Override
    public void undo() {
        paper.getChildren().add(shape);
    }

}
