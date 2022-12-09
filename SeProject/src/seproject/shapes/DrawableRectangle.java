/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject.shapes;

import java.io.Serializable;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author teodoroadinolfi
 */
public class DrawableRectangle extends Rectangle implements DrawableShape{
    
    public DrawableRectangle(double x, double y, double width ,double height){
        super(x,y,width,height);
    }

    @Override
     public void setShapeWidth(double width){
         super.setWidth(width);
     }
    
    @Override
    public void setShapeHeight(double height){
        super.setHeight(height);
    }
    
    @Override
    public void setShapeFillColor(Color color){
        super.setFill(color);
    }
    
    @Override
    public void setShapeStrokeColor(Color color){
        super.setStroke(color);
    } 
    
    @Override
    public Bounds getShapeLayoutBounds(){
        return super.getLayoutBounds();
    }
    
    @Override
    public Bounds getShapeBoundsInParent(){
        return super.getBoundsInParent();
    }

    @Override
    public double getShapeTranslateX() {
        return super.getTranslateX();
    }

    @Override
    public double getShapeTranslateY() {
        return super.getTranslateY();
    }

    @Override
    public void setShapeTranslateX(double x) {
        super.setTranslateX(x);
    }

    @Override
    public void setShapeTranslateY(double y) {
        super.setTranslateY(y);
    }

    @Override
    public double getShapeWidth() {
        return super.getWidth();
    }

    @Override
    public double getShapeHeight() {
        return super.getHeight();
    }

    @Override
    public void setShapeRotation(double rotation) {
        super.setRotate(rotation);
    }

    @Override
    public double getShapeRotation() {
        return  super.getRotate();
    }
    
    
    
    
    
    
    
    
    
}
