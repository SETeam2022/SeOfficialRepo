package seproject.commands;

import javafx.scene.shape.Shape;

/**
 * An object of this class represents the action of horizontally mirroring a shape. 
 * The object also stores all the information needed for the undo of its operation.
 * 
 */
public class MirrorHorizontalCommand implements Command{
    
    private final Shape shape;
    private final double prevScale;

    /**
     * Creates a MirrorHorizontalCommand.
     * 
     * @param shape the shape which will be horizontally mirrored
     */
    public MirrorHorizontalCommand(Shape shape) {
        this.shape = shape;
        this.prevScale = this.shape.getScaleY();
    }
    
    /**
     * This method executes the shape's horizontal mirroring.
     */
    @Override
    public void execute() {
        shape.setScaleY(prevScale*-1);
    }
    
    /**
     * This method allows to perform "undo" the shape's horizontal mirroring.
     */
    @Override
    public void undo() {
        shape.setScaleY(prevScale);        
    }
    
}
