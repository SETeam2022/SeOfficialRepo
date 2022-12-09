/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.commands;

import javafx.scene.shape.Shape;
import seproject.shapes.DrawableShape;

/**
 *
 * @author bvs
 */
public class RotationCommand implements Command{
    
    private double newValue, oldValue;
    private DrawableShape shape;

    public RotationCommand(double newValue, DrawableShape shape) {
        this.newValue = newValue;
        this.shape = shape;
        this.oldValue = shape.getShapeRotation();
    }

    
    
    @Override
    public void execute() {
        this.shape.setShapeRotation(newValue);
    }

    @Override
    public void undo() {
        this.shape.setShapeRotation(oldValue);
    }
    
}
