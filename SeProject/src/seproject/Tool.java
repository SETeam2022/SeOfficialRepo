/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.event.Event;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author teodo
 */
public abstract class Tool {
    
    private Pane paper;
    
    /**
     * This method provides an empty implementation, the class that extends
     * Tool can provides a conrete implementation by overriding the method
     * @param event the JavaFx event associated with the mouse click
     */
    public  void onMousePressed(MouseEvent event){
    };
    
    public  void onMouseDragged(MouseEvent event){
    };
    
    public Pane getPaper(){
        return paper;
    }
    
    public void setPaper(Pane paper){
        this.paper = paper;
    }
    
   public Tool(Pane paper){
       this.paper = paper;
   }
    
}
