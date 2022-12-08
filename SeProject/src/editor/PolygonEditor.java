package editor;

import javafx.collections.ObservableList;
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

        Polyline polygon = (Polyline) shape;
        double minX = getMin(polygon.getPoints(),0),
               maxX = getMax(polygon.getPoints(), 0);
        
        for (int i=0; i < polygon.getPoints().size(); i+=2){
            double currentX = polygon.getPoints().get(i);
            if(currentX != minX){
                currentX = width*((currentX-minX)/(maxX-minX))+minX;
                polygon.getPoints().set(i, currentX);
            }
        }
        
    }
    
    //System.out.println("---i: " + i + " minY: " + minY + " oldWidth " + oldWidth + " newWidth: " + width + " oldMax: " + max + " newMax: " + newMax + " offset: " + offset + " scale: " + scale + " oldValue: " + currentY + " newValue: " + currentY*scale);

    /**
     * 
     * @param shape
     * @param height 
     */
    @Override
    public void setHeight(Shape shape, double height) {
        
        /*Polyline polygon = (Polyline) shape;
        double minY = getMin(polygon.getPoints(),1),
               maxY = getMax(polygon.getPoints(), 1);
        
        for (int i=1; i < polygon.getPoints().size(); i+=2){
            double currentY = polygon.getPoints().get(i);
            if(currentY != minY){
                currentY = height*((currentY-minY)/(maxY-minY))+minY;
                polygon.getPoints().set(i, currentY);
            }
        }*/
        
    }

    /**
     * 
     * @param shape
     * @return 
     */
    @Override
    public double getWidth(Shape shape) {
        Polyline polygon = (Polyline) shape;
        double minX = getMin(polygon.getPoints(), 0), maxX = getMax(polygon.getPoints(), 0);
        return Math.abs(Math.abs(maxX) - Math.abs(minX));
        // return polygon.getLayoutBounds().getWidth();
    }

    /**
     * 
     * @param shape
     * @return 
     */
    @Override
    public double getHeight(Shape shape) {
        Polyline polygon = (Polyline) shape;
        double minY = getMin(polygon.getPoints(), 1), maxY = getMax(polygon.getPoints(), 1);
        return Math.abs(Math.abs(maxY) - Math.abs(minY));
        //return polygon.getLayoutBounds().getHeight();
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
        Polyline polygon = (Polyline) shape;
        double minX = getMin(polygon.getPoints(), 0), minY = getMin(polygon.getPoints(), 1), maxX = getMax(polygon.getPoints(), 0), maxY = getMax(polygon.getPoints(), 1);
        return new Point2D(maxX-minX, maxY-minY);
    }

    /**
     * 
     * @param points
     * @param start
     * @return 
     */
    private double getMin(ObservableList<Double> points, int start) {
        double min = points.get(0);
        for(int i=start; i<points.size(); i+=2){
            if (points.get(i) < min) { min = points.get(i); }
        }
        return min;
    }
    
    /**
     * 
     * @param points
     * @param start
     * @return 
     */
    private double getMax(ObservableList<Double> points, int start) {
        double max = points.get(0);
        for(int i=start; i<points.size(); i+=2){
            if (points.get(i) > max) { max = points.get(i); }
        }
        return max;
    }
}