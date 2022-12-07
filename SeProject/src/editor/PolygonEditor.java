package editor;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

public class PolygonEditor implements ShapeEditor {

    /**
     * 
     * @param shape
     * @param width 
     */
    @Override
    public void setWidth(Shape shape, double width) {
        double offset = width - getWidth(shape), centerX = getCenter(shape).getX();
        System.out.println(getCenter(shape));
        System.out.println("offset: " + offset);
        System.out.println("----------------");
        Polyline polygon = (Polyline) shape;
        if (offset < 0){
            /* decrease the width of offset */
            for (int i=0; i < polygon.getPoints().size(); i+=2){
                double currentX = polygon.getPoints().get(i);
                System.out.println("old value of x: " + currentX);
                currentX = currentX < centerX ? currentX + offset : currentX - offset;
                System.out.println("new value of x: " + currentX);
                System.out.println("--------------");
                polygon.getPoints().set(i, currentX);
            }
        }else{
            /* increase the width of offset */
            for (int i=1; i < polygon.getPoints().size(); i+=2){
                double currentX = polygon.getPoints().get(i);
                currentX = currentX < centerX ? currentX - offset : currentX + offset;
                polygon.getPoints().set(i, currentX);
            }
        }
    }

    /**
     * 
     * @param shape
     * @param height 
     */
    @Override
    public void setHeight(Shape shape, double height) {
        double offset = height - getHeight(shape), centerY = getCenter(shape).getY();
        
        Polyline polygon = (Polyline) shape;
        if (offset < 0){
            /* decrease the height of offset */
            for (int i=0; i < polygon.getPoints().size(); i+=2){
                double currentY = polygon.getPoints().get(i);
                
            }
        }else{
            /* increase the height of offset */
            for (int i=1; i < polygon.getPoints().size(); i+=2){
                double currentY = polygon.getPoints().get(i);
                
            }
        }
    }

    /**
     * 
     * @param shape
     * @return 
     */
    @Override
    public double getWidth(Shape shape) {
        return shape.getBoundsInParent().getWidth();
    }

    /**
     * 
     * @param shape
     * @return 
     */
    @Override
    public double getHeight(Shape shape) {
        return shape.getBoundsInParent().getHeight();
    }

    /**
     * 
     * @param shape
     * @return 
     */
    @Override
    public Shape clone(Shape shape) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * 
     * @param shape
     * @return 
     */
    private Point2D getCenter(Shape shape){
        return new Point2D(shape.getBoundsInParent().getMinX()+shape.getBoundsInParent().getWidth()/2, shape.getBoundsInParent().getMinY()+shape.getBoundsInParent().getHeight()/2);
    }
    
}
