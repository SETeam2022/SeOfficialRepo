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
public class MirrorVerticalCommand implements Command{

    private final Shape shape;
    private final double prevScale;

    public MirrorVerticalCommand(Shape shape) {
        this.shape = shape;
        this.prevScale = this.shape.getScaleX();
    }
    
    /**
     * This method execute the shape's vertical mirroring.
     */
    @Override
    public void execute() {
        shape.setScaleX(prevScale*-1);
    }

    /**
     * This method allow to do "undo" the shape's horizontal mirroring.
     */
    @Override
    public void undo() {
        shape.setScaleX(prevScale);
    }
}