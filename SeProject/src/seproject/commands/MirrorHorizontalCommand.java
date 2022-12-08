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
public class MirrorHorizontalCommand implements Command{
    
    private final Shape shape;
    private final double prevScale;

    public MirrorHorizontalCommand(Shape shape) {
        this.shape = shape;
        this.prevScale = this.shape.getScaleY();
    }
    
    @Override
    public void execute() {
        shape.setScaleY(prevScale*-1);
    }

    @Override
    public void undo() {
        shape.setScaleY(prevScale);        
    }
    
}
