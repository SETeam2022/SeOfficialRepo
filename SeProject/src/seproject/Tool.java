/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.event.Event;
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
     * This method provide an empty implementation, the class that extend
     * Tool can provide a conrete implementation by overriding the method
     * @param event the JavaFx event associated with the mouse click
     */
    public  void mouseClick(MouseEvent event){
    };
    
    public  void drag(MouseEvent event){
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
