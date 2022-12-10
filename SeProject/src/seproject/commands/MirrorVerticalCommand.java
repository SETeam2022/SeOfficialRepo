package seproject.commands;

import javafx.scene.shape.Shape;

/**
 * An object of this class represents the action of vertically mirroring a shape. 
 * The object also stores all the information needed for the undo of its operation.
 * 
 */
public class MirrorVerticalCommand implements Command{

    private final Shape shape;
    private final double prevScale;

    /**
     * Creates a MirrorVerticalCommand.
     * 
     * @param shape the shape which will be vertically mirrored
     */
    public MirrorVerticalCommand(Shape shape) {
        this.shape = shape;
        this.prevScale = this.shape.getScaleX();
    }
    
    /**
     * This method executes the shape's vertical mirroring.
     */
    @Override
    public void execute() {
        shape.setScaleX(prevScale*-1);
    }

    /**
     * This method allows to perform "undo" the shape's vertical mirroring.
     */
    @Override
    public void undo() {
        shape.setScaleX(prevScale);
    }
}
