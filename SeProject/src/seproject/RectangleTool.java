/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
  * This class is the rappresentation of a specialized tool that can draw Rectangle 
  * on the screen.
  */
public class RectangleTool extends Tool{
 
    /**
     *  Create a new RectangleTool
     *  @param paper is the pane on witch the new ellipses nodes will be added
     */
    public RectangleTool(Pane paper){
        super(paper);
    }
    
    /**
    *   This function will be called after a click with the mouse on the paper
    *   it will draw on the screen a rectangle by adding a new node as a 
    *   child for the Pane that works as a Paper
    *   @param  event is the event that generated the call to this method its
    *   X and Y coordinates will be used for the top left cornet of the shape
    */
    @Override
    public void mousePressed(MouseEvent event){
        Rectangle ret = new Rectangle(event.getX(),event.getY(),100,50);
        this.getPaper().getChildren().add(ret);
        
    }
    
    
    
    
    
    
}
