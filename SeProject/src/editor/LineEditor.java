package editor;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineEditor extends ShapeEditor {

    /**
     *
     * @param shape the line you want to edit Width
     * @param width the new line width
     */
    @Override
    public void setWidth(Shape shape, double width) {
        int old = (int) Math.round(this.getWidth(shape));
        if (old == width) {
            return;
        }

        ((Line) shape).setEndX(((Line) shape).getStartX() + width);
    }

    /**
     *
     * @param shape the line you want to edit Height
     * @param height
     */
    @Override
    public void setHeight(Shape shape, double height) {
        int old = (int) Math.round(this.getHeight(shape));
        if (old == height) {
            return;
        }

        if (((Line) shape).getEndY() < ((Line) shape).getStartY()) {
            ((Line) shape).setEndY(((Line) shape).getStartY() - height);
        } else {
            ((Line) shape).setEndY(((Line) shape).getStartY() + height);
        }
    }

    /**
     *
     * @param shape the line you want to get Width
     * @return
     */
    @Override
    public double getWidth(Shape shape) {
        return Math.abs(((Line) shape).getEndX() - ((Line) shape).getStartX());
    }

    /**
     *
     * @param shape the line you want to get Height
     * @return
     */
    @Override
    public double getHeight(Shape shape) {
        return Math.abs(((Line) shape).getEndY() - ((Line) shape).getStartY());
    }
    
    /**
     *
     * @param shape the line you want to clone.
     * @return the cloned line.
     */
    @Override
    public Shape clone(Shape shape) {
        Line original = (Line) shape;
        Line clone = (Line) super.clone(shape);

        clone.setStartX(original.getStartX());
        clone.setStartY(original.getStartY());

        clone.setEndX(original.getEndX());
        clone.setEndY(original.getEndY());
        return clone;
    }

}
