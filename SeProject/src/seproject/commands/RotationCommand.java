package seproject.commands;

import javafx.scene.shape.Shape;

/**
 * An object of this class represents the action of rotating a shape, the
 * object also stores all the information needed for the undo of its operation.
 * 
 */
public class RotationCommand implements Command{
    
    private final double newValue;
    private final double oldValue;
    private final Shape shape;

    /**
     * Creates a RotationCommand.
     * 
     * @param newValue the new rotation value
     * @param shape the shape which will be rotated
     */
    public RotationCommand(double newValue, Shape shape) {
        this.newValue = newValue;
        this.shape = shape;
        this.oldValue = shape.getRotate();
    }

    /**
     * Rotates the shape.
     */
    @Override
    public void execute() {
        this.shape.rotateProperty().setValue(this.newValue);
    }

    /**
     * Makes the shape go back to its previous rotation value.
     */
    @Override
    public void undo() {
        this.shape.rotateProperty().setValue(oldValue);
    }
    
}
