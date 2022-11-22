/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.event.Event;
import javafx.scene.shape.Shape;

/**
 *
 * @author teodo
 */
public abstract class Tool {
    
    private Shape workingShape;
    
    public abstract void mouseDown(Event event);
    
    public abstract void drag(Event event);
    
    public Shape getShape(){
        return workingShape;
    }
    
    public void setShape(Shape s){
        this.workingShape = s;
    }
    
   public Tool(){
       workingShape = null;
   }
    
}
