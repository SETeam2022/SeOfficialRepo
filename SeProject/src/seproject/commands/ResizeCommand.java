package seproject.commands;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import javafx.scene.shape.Shape;
import seproject.tools.SelectedShapeManager;

/**
 * An object of this class represents the action of resaizing a shape, the
 * object also stores all the information needed for the undo of its operation.
 * 
 */
public class ResizeCommand implements Command {

    private final Shape shape;
    private final double width;
    private final double height;
    private double oldWidth;
    private double oldHeight;

    /**
     * Creates a ResizeCommand.
     *
     * @param shape the shape to be resizes
     * @param width the shape's new width
     * @param height the shape's new height
     */
    public ResizeCommand(Shape shape, double width, double height) {
        this.shape = shape;
        this.width = width;
        this.height = height;
    }

    /**
     * Resizes the shape.
     */
    @Override
    public void execute() {
        ShapeEditor editor = ShapeEditorFactory.getInstance(shape.getClass());
        oldWidth = editor.getWidth(shape);
        oldHeight = editor.getHeight(shape);
        editor.setWidth(shape, width);
        editor.setHeight(shape, height);
    }

    /**
     * Makes the shape go back to its original size.
     */
    @Override
    public void undo() {
        ShapeEditor editor = ShapeEditorFactory.getInstance(shape.getClass());
        editor.setWidth(shape, oldWidth);
        editor.setHeight(shape, oldHeight);
    }

}
