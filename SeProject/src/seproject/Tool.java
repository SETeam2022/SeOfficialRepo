/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 *
 * @author teodo
 */
public abstract class Tool {
    
    private Pane paper;
    
    private Paint strokeColor;
    
    private Paint fillColor;
    
    /**
     * This method provides an empty implementation, the class that extends
     * Tool can provides a conrete implementation by overriding the method
     * @param event the JavaFx event associated with the mouse click
     */
    public  void onMousePressed(MouseEvent event){
    };
    
    /**
     * This method provides an empty implementation, the class that extends
     * Tool can provides a conrete implementation by overriding the method
     * @param event the JavaFx event associated with the mouse click
     */
    public  void onMouseDragged(MouseEvent event){
    };
    
    /**
     * @return the pane that works as a paper witch the tool is working
     */
    public Pane getPaper(){
        return paper;
    }
    
    /**
     * @param paper the pane on witch the tool will work
     */
    public void setPaper(Pane paper){
        this.paper = paper;
    }
    
    /**
     * @param paint the color that will be used by the concrete tool for working on the shapes stroke
     */
    public void setStrokeColor(Paint paint){
        this.strokeColor = paint;
    }
    
    /**
     * @return strokeColor the strokeColor setted for the tool
     */
    public Paint getStrokeColor(){
        return this.strokeColor;
    }
    
    /**
     * @param paint the color that will be used by the concrete tool for working on the shapes fill
     */
    
    public void setFillColor(Paint paint){
        this.fillColor = paint;
    }
    
    /**
     * @return fillColor the fillColor setted for the tool
     */
    public Paint getFillColor(){
        return this.fillColor;
    }
    
    /**
     *  @param paper the pane on witch the tool will work
     */
   public Tool(Pane paper){
       this.paper = paper;
   }
    
}
