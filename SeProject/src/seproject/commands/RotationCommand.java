/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.commands;

import javafx.scene.shape.Shape;

/**
 *
 * @author bvs
 */
public class RotationCommand implements Command{
    
    private double newValue, oldValue;
    private Shape shape;

    public RotationCommand(double newValue, Shape shape) {
        this.newValue = newValue;
        this.shape = shape;
        this.oldValue = shape.getRotate();
    }

    
    
    @Override
    public void execute() {
        this.shape.rotateProperty().setValue(this.newValue);
    }

    @Override
    public void undo() {
        this.shape.rotateProperty().setValue(oldValue);
    }
    
}
