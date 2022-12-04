package seproject.commands;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/**
 * An object of this class represent the action of changeing the fill color of a
 * shape, the onject also stores all the information neded for the undo of its
 * operation
 */
public class ChangeFillColorCommand implements Command {

    private final Color newColor;
    private final Paint oldColor;
    private final Shape shape;

    /**
     * Create a ChangeFillColorCommand
     *
     * @param newColor the new color that will be used for filling the shape
     * @param shape the shape on witch the color change will be done
     */
    public ChangeFillColorCommand(Color newColor, Shape shape) {
        this.newColor = newColor;
        this.oldColor = shape.getFill();
        this.shape = shape;
    }

    /**
     * Change effectivly the color of the shape
     */
    @Override
    public void execute() {
        shape.setFill(newColor);
    }

    /**
     * Restore the color of the shape, before the change
     */
    @Override
    public void undo() {
        shape.setFill(oldColor);
    }

}
