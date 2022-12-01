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
public class ChangeStrokeColorCommand implements Command{
    
    private Color newColor;
    private Paint oldColor;
    private Shape shape;

    public ChangeStrokeColorCommand(Color newColor, Paint oldColor, Shape shape) {
        this.newColor = newColor;
        this.oldColor = oldColor;
        this.shape = shape;
    }

    @Override
    public void execute() {
        shape.setStroke(newColor);
    }

    @Override
    public void undo() {
        shape.setStroke(oldColor);
    }
    
    
    
     
    
}
