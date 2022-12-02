package editor;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineEditor implements ShapeEditor {

    @Override
    public void setWidth(Shape shape, double width) {
        int old = (int) Math.round(this.getWidth(shape));
        if(old == width) return;
        
        ((Line) shape).setEndX(((Line) shape).getStartX()+width);
    }

    @Override
    public void setHeight(Shape shape, double height) {
        int old = (int) Math.round(this.getHeight(shape));
        if(old == height) return; 
        
        if (((Line) shape).getEndY()<((Line) shape).getStartY()) ((Line) shape).setEndY(((Line) shape).getStartY()-height);
        else ((Line) shape).setEndY(((Line) shape).getStartY()+height);
    }

    @Override
    public double getWidth(Shape shape) {
        return ((Line) shape).getLayoutBounds().getWidth();
    }

    @Override
    public double getHeight(Shape shape) {
        return ((Line) shape).getLayoutBounds().getHeight();
    }

    @Override
    public void setX(Shape shape, double newX) {
        ((Line) shape).setStartX(((Line) shape).getStartX()+newX);
        ((Line) shape).setEndX(((Line) shape).getEndX()+newX);
    }

    @Override
    public void setY(Shape shape, double newY) {
        ((Line) shape).setStartY(((Line) shape).getStartX()+newY);
        ((Line) shape).setEndY(((Line) shape).getEndX()+newY);
    }
    
}
