package seproject.commands;

import javafx.scene.shape.Shape;
import seproject.customComponents.LayeredPaper;

/**
 * An object of this class represents the action of bringing a shape to front, 
 * the object also stores all the information needed for the undo of its
 * operation.
 * 
 */
public class BringToFrontCommand implements Command {

    private final Shape shape;
    private final LayeredPaper paper;
    private int index;

    /**
     * Creates a BringToFrontCommand.
     *
     * @param shape the shape which will be brought to front
     * @param paper the paper which the shape belongs to
     */
    public BringToFrontCommand(Shape shape, LayeredPaper paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * This method brings the shape to front.
     */
    @Override
    public void execute() {
        index = this.paper.indexInPaper(shape);
        this.shape.toFront();
    }

    /**
     * This method brings the shape to its original position among the children
     * of the pane.
     */
    @Override
    public void undo() {
        paper.removeFromPaper(shape);
        paper.addInPaper(index, shape);
    }

}
