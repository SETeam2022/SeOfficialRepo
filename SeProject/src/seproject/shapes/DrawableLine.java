package seproject.shapes;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DrawableLine extends Line implements DrawableShape{

    public DrawableLine(double startX , double startY , double endX ,  double endY) {
        super(startX,startY,endX, endY);
    }

    @Override
     public void setShapeWidth(double width){
        int old = (int) Math.round(this.getShapeWidth());
        if (old == width) return;
        setEndX(getStartX() + width);
     }
    
    @Override
    public void setShapeHeight(double height){
        int old = (int) Math.round(getShapeHeight());
        if (old == height)  return;
        if (getEndY() < getStartY()) setEndY(getStartY() - height);
        else setEndY(getStartY() + height);
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
        return Math.abs(getEndX() - getStartX());
    }

    @Override
    public double getShapeHeight() {
        return Math.abs(getEndY() - getStartY());
    }

    @Override
    public void setShapeRotation(double rotation) {
        super.setRotate(rotation);
    }

    @Override
    public double getShapeRotation() {
        return super.getRotate();
    }
}
