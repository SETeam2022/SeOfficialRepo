/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author teodo
 */
public class RectangleTool extends Tool{
    
    public RectangleTool(Pane paper){
        super(paper);
    }
    
    /**
    *   This function will be called after a click with the mouse on the paper
    *   it will draw on the screen the actual working shape by adding the new node as a 
    *   child for the Pane that works as a Paper
    *   @param  event is the event that generated the call to this method
    */
    @Override
    public void mouseClick(MouseEvent event){
        
        Rectangle ret = new Rectangle(event.getX(),event.getY(),100,50);
        this.getPaper().getChildren().add(ret);
        
    }
    
    
    
    
    
    
}
