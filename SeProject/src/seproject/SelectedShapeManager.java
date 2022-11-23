/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject;

import javafx.animation.PathTransition;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author bvs
 */
public class SelectedShapeManager extends Tool{
    
    private boolean flag;
    
    public SelectedShapeManager(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
        this.flag = false;
    }
    
    @Override
    public void onMousePressed(MouseEvent event){
        for(Node node : this.getPaper().getChildren()){
            node.setEffect(null);
        }
        for(Node node : this.getPaper().getChildren()){
            if (node.contains(event.getX(),event.getY())){
                DropShadow ds1 = new DropShadow();
                ds1.setOffsetY(4.0f);
                ds1.setOffsetX(2.0f);
                node.setEffect(ds1);
            } 
        }
    }
        
    
    @Override
    public void onMouseDragged(MouseEvent event){
        
    };
    
}
