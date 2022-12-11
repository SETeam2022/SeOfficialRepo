package seproject.commands;

import javafx.scene.shape.Shape;
import seproject.customComponents.DrawingArea;
import seproject.customComponents.LayeredPaper;

/**
 * An object of this class represents the action of bringing a shape to back, 
 * the object also stores all the information needed for the undo of its
 * operation.
 * 
 */
public class BringToBackCommand implements Command {

    private final Shape shape;
    private final LayeredPaper paper;
    private int index;

    /**
     * Creates a BringToBackCommand.
     *
     * @param shape the shape which will be brought to back
     * @param paper the paper which the shape belongs to
     */
    public BringToBackCommand(Shape shape, LayeredPaper paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * This method brings the shape to back.
     */
    @Override
    public void execute() {
        index = paper.indexInPaper(this.shape);
        this.shape.toBack();
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
