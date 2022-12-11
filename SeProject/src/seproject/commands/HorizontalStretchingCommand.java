package seproject.commands;

import javafx.scene.shape.Shape;

/**
 * An object of this class represents the action of horizontally stratching a shape. 
 * The object also stores all the information needed for the undo of its operation.
 * 
 */
public class HorizontalStretchingCommand implements Command{
    
    private final double prevStrechingValue;
    private final double newStretchingValue;
    private final Shape shape;

    /**
     * Creates a HorizontalStretchingCommand.
     * 
     * @param shape the shape which will be horizontally stretched
     * @param newStretchingValue the value of which the shape will be stretched
     */
    public HorizontalStretchingCommand(Shape shape, double newStretchingValue) {
        this.shape = shape;
        this.newStretchingValue = newStretchingValue;
        this.prevStrechingValue = this.shape.getScaleX();
    }

    /**
     * Mirrors the shape horizontally.
     */
    @Override
    public void execute() {
        shape.setScaleX(newStretchingValue);
    }

    /**
     * Discards the previously done horizontal mirroring.
     */
    @Override
    public void undo() {
        shape.setScaleX(prevStrechingValue);
    }
    
}
