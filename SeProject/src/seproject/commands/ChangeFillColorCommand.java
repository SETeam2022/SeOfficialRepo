package seproject.commands;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/**
 * An object of this class represents the action of changing the fill color of a
 * shape, the object also stores all the information neded for the undo of its
 * operation.
 */
public class ChangeFillColorCommand implements Command {

    private final Color newColor;
    private final Paint oldColor;
    private final Shape shape;

    /**
     * Creates a ChangeFillColorCommand.
     *
     * @param newColor the new color that will be used to fill the shape
     * @param shape the shape on witch the color change will be done
     */
    public ChangeFillColorCommand(Color newColor, Shape shape) {
        this.newColor = newColor;
        this.oldColor = shape.getFill();
        this.shape = shape;
    }

    /**
     * Changes the color of the shape.
     */
    @Override
    public void execute() {
        shape.setFill(newColor);
    }

    /**
     * Restores the color which the shape had before the change.
     */
    @Override
    public void undo() {
        shape.setFill(oldColor);
    }

}
