/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.commands;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.shape.Shape;
import seproject.tools.SelectedShapeManager;

/**
 *
 * @author bvs
 */
public class CopyShapeCommand implements Command{
    
    private Shape shape;

    public CopyShapeCommand(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void execute() {
        SelectedShapeManager.getSelectedShapeManager().setCopiedShape(shape);
        SelectedShapeManager.getSelectedShapeManager().setShapeIsCopiedProperty(true);
    }

    @Override
    public void undo() {
        SelectedShapeManager.getSelectedShapeManager().setCopiedShape(null);
        SelectedShapeManager.getSelectedShapeManager().setShapeIsCopiedProperty(false);
    }
    
}
