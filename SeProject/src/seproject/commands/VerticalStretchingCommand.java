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
public class VerticalStretchingCommand implements Command{
    
    private double prevStrechingValue, newStretchingValue;
    private Shape shape;

    public VerticalStretchingCommand(Shape shape, double newStretchingValue) {
        this.shape = shape;
        this.newStretchingValue = newStretchingValue;
        this.prevStrechingValue = this.shape.getScaleY();
    }

    @Override
    public void execute() {
        shape.setScaleY(newStretchingValue);
    }

    @Override
    public void undo() {
        shape.setScaleY(prevStrechingValue);
    }
    
}
