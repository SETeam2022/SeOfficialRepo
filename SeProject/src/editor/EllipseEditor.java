/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editor;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 *
 * @author alewi
 */
public class EllipseEditor implements ShapeEditor {

    public EllipseEditor() {
    }

    @Override
    public void setWidth(Shape shape, double width) {
       ((Ellipse) shape).setRadiusX(width/2);
    }

    @Override
    public void setHeight(Shape shape, double height) {
       ((Ellipse) shape).setRadiusY(height/2); 
    }

    @Override
    public void setX(Shape shape, double newX) {
        ((Ellipse) shape).setCenterX(newX);
    }

    @Override
    public void setY(Shape shape, double newY) {
        ((Ellipse) shape).setCenterY(newY);
    }

    @Override
    public double getWidth(Shape shape) {
        return ((Ellipse) shape).layoutBoundsProperty().get().getWidth();
    }

    @Override
    public double getHeight(Shape shape) {
        return ((Ellipse) shape).layoutBoundsProperty().get().getHeight();
    }
    
}
