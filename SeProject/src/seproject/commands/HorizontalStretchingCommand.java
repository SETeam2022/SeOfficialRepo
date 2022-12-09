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
public class HorizontalStretchingCommand implements Command{
    
    private double prevStrechingValue, newStretchingValue;
    private Shape shape;

    public HorizontalStretchingCommand(Shape shape, double newStretchingValue) {
        this.shape = shape;
        this.newStretchingValue = newStretchingValue;
        this.prevStrechingValue = this.shape.getScaleX();
    }

    @Override
    public void execute() {
        shape.setScaleX(newStretchingValue);
    }

    @Override
    public void undo() {
        shape.setScaleX(prevStrechingValue);
    }
    
}
