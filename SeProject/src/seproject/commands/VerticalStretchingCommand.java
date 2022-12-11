package seproject.commands;

import javafx.scene.shape.Shape;

/**
 * An object of this class represents the action of vertically stratching a shape. 
 * The object also stores all the information neded for the undo of its operation.
 * 
 */
public class VerticalStretchingCommand implements Command{
    
    private final double prevStrechingValue;
    private final double newStretchingValue;
    private final Shape shape;

    /**
     * Creates a VerticalStretchingCommand.
     * 
     * @param shape the shape which will be vertically stretched
     * @param newStretchingValue the value of which the shape will be stretched
     */
    public VerticalStretchingCommand(Shape shape, double newStretchingValue) {
        this.shape = shape;
        this.newStretchingValue = newStretchingValue;
        this.prevStrechingValue = this.shape.getScaleY();
    }

    /**
     * Mirrors the shape vertically.
     */
    @Override
    public void execute() {
        shape.setScaleY(newStretchingValue);
    }

    /**
     *  Discards the previously done vertical mirroring.
     */
    @Override
    public void undo() {
        shape.setScaleY(prevStrechingValue);
    }
    
}
