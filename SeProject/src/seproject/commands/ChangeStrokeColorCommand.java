package seproject.commands;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/**
 * An object of this class represents the action of changing the stroke color of
 * a shape, the object also stores all the information needed for the undo of its
 * operation.
 */
public class ChangeStrokeColorCommand implements Command {

    private final Color newColor;
    private final Paint oldColor;
    private final Shape shape;

    /**
     * Creates a ChangeStrokeColorCommand.
     *
     * @param newColor the new color that will be used for the shape's stroke
     * @param shape the shape on witch the color change will be done
     */
    public ChangeStrokeColorCommand(Color newColor, Shape shape) {
        this.newColor = newColor;
        this.oldColor = shape.getStroke();
        this.shape = shape;
    }

    /**
     * Changes the color of the shape.
     */
    @Override
    public void execute() {
        shape.setStroke(newColor);
    }

    /**
     * Restores the color which the shape had before the change.
     */
    @Override
    public void undo() {
        shape.setStroke(oldColor);
    }

}
