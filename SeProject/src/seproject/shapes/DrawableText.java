package seproject.shapes;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author alewi
 */
public class DrawableText extends Text implements DrawableShape {

    public DrawableText(double d, double d1, String string) {
        super(d, d1, string);
    }

    @Override
    public void setShapeWidth(double width) {
        super.setWrappingWidth(width);
    }

    @Override
    public void setShapeHeight(double height) {

    }

    @Override
    public void setShapeRotation(double rotation) {
        super.setRotate(rotation);
    }

    @Override
    public double getShapeWidth() {
        return super.getWrappingWidth();
    }

    @Override
    public double getShapeHeight() {
        return super.getLayoutBounds().getHeight();
    }

    @Override
    public double getShapeRotation() {
        return  super.getRotate();
    }

    @Override
    public void setShapeFillColor(Color color) {
        super.setFill(color);
    }

    @Override
    public void setShapeStrokeColor(Color color) {
        super.setStroke(color);
    }

    @Override
    public Bounds getShapeLayoutBounds() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Bounds getShapeBoundsInParent() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getShapeTranslateX() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getShapeTranslateY() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setShapeTranslateX(double x) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setShapeTranslateY(double y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
