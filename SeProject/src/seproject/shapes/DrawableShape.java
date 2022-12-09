/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject.shapes;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

/**
 *
 * @author teodoroadinolfi
 */
public interface DrawableShape {
    
    public void setShapeWidth(double width);
    
    public void setShapeHeight(double height);
    
    public void setShapeRotation(double rotation);
    
    public double getShapeWidth();
    
    public double getShapeHeight();
    
    public double getShapeRotation();
    
    public void setShapeFillColor(Color color);
    
    public void setShapeStrokeColor(Color color);
    
    //public void stratchWidth(double stratch);
    
    //public void stratchHeight(double stratch);
    
    public Bounds getShapeLayoutBounds();
    
    public Bounds getShapeBoundsInParent();
    
    public double getShapeTranslateX();
    
    public double getShapeTranslateY();
    
    public void setShapeTranslateX(double x);
    
    public void setShapeTranslateY(double y);
    
    
}
