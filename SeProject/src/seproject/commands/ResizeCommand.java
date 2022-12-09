package seproject.commands;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import javafx.scene.shape.Shape;
import seproject.shapes.DrawableShape;

/**
 * An object of this class represent the action of resaizeing a shape, the
 * object also stores all the information neded for the undo of its operation
 */
public class ResizeCommand implements Command {

    private final DrawableShape shape;
    private final double width;
    private final double height;
    private double oldWidth;
    private double oldHeight;

    /**
     * Create a ResizeCommand
     *
     * @param shape the shape to resize
     * @param width the shape's new width
     * @param height the shape's new height
     */
    public ResizeCommand(DrawableShape shape, double width, double height) {
        this.shape = shape;
        this.width = width;
        this.height = height;
    }

    /**
     * Effectively resize the shape
     */
    @Override
    public void execute() {  
        oldWidth = shape.getShapeWidth();
        oldHeight = shape.getShapeHeight();
        shape.setShapeHeight(height);
        shape.setShapeWidth(width);
    }
    /**
     * Resize the shape to it's original size
     */
    @Override
    public void undo() {
        shape.setShapeHeight(oldHeight);
        shape.setShapeWidth(oldWidth);
    }

}
