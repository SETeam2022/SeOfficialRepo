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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setHeight(Shape shape, double height) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setX(Shape shape, double newX) {
        ((Ellipse) shape).setCenterX(newX);
    }

    @Override
    public void setY(Shape shape, double newY) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
