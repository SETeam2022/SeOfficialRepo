/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.commands;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/**
 *
 * @author bvs
 */
public class ChangeFillColorCommand implements Command{
    
    private final Color newColor;
    private final Paint oldColor;
    private final Shape shape;

    public ChangeFillColorCommand(Color newColor, Paint oldColor, Shape shape) {
        this.newColor = newColor;
        this.oldColor = oldColor;
        this.shape = shape;
    }
    

    @Override
    public void execute() {
        shape.setFill(newColor);
    }

    @Override
    public void undo() {
        shape.setFill(oldColor);
    }
    
}
