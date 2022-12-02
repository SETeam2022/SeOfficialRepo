/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject.commands;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import javafx.scene.shape.Shape;

/**
 *
 * @author teodoroadinolfi
 */
public class ResizeCommand implements Command {
    
    private final Shape shape;
    private final double width;
    private final double height;
    private double oldWidth;
    private double oldHeight;

    public ResizeCommand(Shape shape, double width, double height) {
        this.shape = shape;
        this.width = width;
        this.height = height;
    }

    @Override
    public void execute() {
        ShapeEditor editor = ShapeEditorFactory.getInstance(shape.getClass());
        oldWidth = editor.getWidth(shape);
        oldHeight = editor.getHeight(shape);
        editor.setWidth(shape, width);
        editor.setHeight(shape, height);
    }

    @Override
    public void undo() {
        ShapeEditor editor = ShapeEditorFactory.getInstance(shape.getClass());
        editor.setWidth(shape, oldWidth);
        editor.setHeight(shape, oldHeight);
    }
    
}
