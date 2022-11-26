package seproject.tools;

import static java.lang.Math.abs;
import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * This class is the rappresentation of a specialized tool that can draw
 * Ellipses on the screen.
 */
public class EllipseTool extends Tool {
    private Ellipse ell;
    private double startX, startY;
    /**
     * Create a new EllipseTool
     *
     * @param paper is the pane on witch the new ellipses nodes will be added
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * Interior Picker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * Interior Picker's value.
     */
    public EllipseTool(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    /**
     * This function will be called after a click with the mouse on the paper it
     * will draw on the screen an ellipse by adding a new node as a child for
     * the Pane that works as a Paper
     *
     * @param event is the event that generated the call to this method its X
     * and Y coordinates will be used for the center of the ellipse
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        ell = new Ellipse(event.getX(), event.getY(), 0,0);
        ell.setStroke(this.getStrokeColor());
        ell.setFill(this.getFillColor());
        ell.setStrokeWidth(Tool.widthStroke);
        this.getPaper().getChildren().add(ell);
    }
    
    /**
    *   This function will be called when I click the mouse on the paper and 
    *   move it on the paper and it will draw on the screen an update ellipse.
    *   @param event is the event that generated the call to this method its X 
    *   and Y coordinates will be used for the center of the ellipse
    */
    @Override
    public void onMouseDragged(MouseEvent event){
        ell.setRadiusX(abs(startX-event.getX()));
        ell.setRadiusY(abs(startY-event.getY()));
    }

    
    

}
