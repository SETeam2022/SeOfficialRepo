/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject.shapes;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author teodoroadinolfi
 */
public class DrawableEllipse extends Ellipse implements DrawableShape {
    

    public DrawableEllipse(double x, double y, double radiusX, double radiusY ) {
        super(x,y,radiusX,radiusY);
    }

    @Override
    public void setShapeWidth(double width) {
        super.setRadiusX(width / 2);
    }

    @Override
    public void setShapeHeight(double height) {
       super.setRadiusY(height/2);
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
         return super.getRadiusX()/2;
    }

    @Override
    public double getShapeHeight() {
       return super.getRadiusY()/2;
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