package editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

public class PolygonEditor extends ShapeEditor {

    /**
     * This method is used to set the width of a polygon to a given number.
     * Starting from the new desired width, calculates the minimum and maximum x
     * coordinate of a shape, normalizes all the other points, rescaling them
     * in the new range.
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

    /**
     * This method is used to set the height of a polygon to a given number.
     * Starting from the new desired height, calculates the minimum and maximum y
     * coordinate of a shape, normalizes all the other points, rescaling them
     * in the new range.
     * 
     * @param shape
     * @param height 
     */
    @Override
    public void setHeight(Shape shape, double height) {
        
        Polyline polygon = (Polyline) shape;
        double minY = getMin(polygon.getPoints(),1),
               maxY = getMax(polygon.getPoints(), 1);
        
        for (int i=1; i < polygon.getPoints().size(); i+=2){
            double currentY = polygon.getPoints().get(i);
            if(currentY != minY){
                currentY = height*((currentY-minY)/(maxY-minY))+minY;
                polygon.getPoints().set(i, currentY);
            }
        }
        
    }

    /**
     * This method is used to get the width of a polygon. It is calculated as the
     * difference between the minimum and maximum x values of the polygon.
     * 
     * @param shape
     * @return double, the width of the shape
     */
    @Override
    public double getWidth(Shape shape) {
        Polyline polygon = (Polyline) shape;
        double minX = getMin(polygon.getPoints(), 0), maxX = getMax(polygon.getPoints(), 0);
        return Math.abs(Math.abs(maxX) - Math.abs(minX));
    }

    /**
     * This method is used to get the height of a polygon. It is calculated as the
     * difference between the minimum and maximum y values of the polygon.
     * 
     * @param shape
     * @return double, the height of the shape
     */
    @Override
    public double getHeight(Shape shape) {
        Polyline polygon = (Polyline) shape;
        double minY = getMin(polygon.getPoints(), 1), maxY = getMax(polygon.getPoints(), 1);
        return Math.abs(Math.abs(maxY) - Math.abs(minY));
    }

    /**
     * This method allows to clone the polygon and its duplicate.
     * @param shape
     * @return Shape, the cloned shape
     */
    @Override
    public Shape clone(Shape shape) {
        Polyline original = (Polyline) shape;
        Polyline clone = (Polyline) super.clone(shape);
        clone.getPoints().setAll(original.getPoints());
        return clone;   
    }

    /**
     * This method is used to get the minimum coordinate of a polygon. All the 
     * x or y coordinates (according to the specified parameter, 0 for the x and 
     * 1 for the y) are compared in order to get the minimum one.
     * 
     * @param points
     * @param start
     * @return min, the minimum value
     */
    private double getMin(ObservableList<Double> points, int start) {
        double min = points.get(start);
        for(int i=start; i<points.size(); i+=2){
            if (points.get(i) < min) { min = points.get(i); }
        }
        return min;
    }
    
    /**
     * This method is used to get the maximum coordinate of a shape. All the 
     * x or y coordinates (according to the specified parameter, 0 for the x and 
     * 1 for the y) are compared in order to get the maximum one.
     * 
     * @param points
     * @param start
     * @return max, the maximum value
     */
    private double getMax(ObservableList<Double> points, int start) {
        double max = points.get(start);
        for(int i=start; i<points.size(); i+=2){
            if (points.get(i) > max) { max = points.get(i); }
        }
        return max;
    }

    @Override
    public void saveShape(Shape shape, ObjectOutputStream stream) throws IOException {
        super.saveShape(shape, stream);
        Polyline polygon = (Polyline) shape;
        stream.writeObject(new ArrayList (polygon.getPoints()));
    }

    @Override
    public Shape loadShape(Class c, ObjectInputStream stream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Polyline polygon = (Polyline) super.loadShape(c, stream);
        polygon.getPoints().setAll((ArrayList) stream.readObject());
        return polygon;
    }
}
