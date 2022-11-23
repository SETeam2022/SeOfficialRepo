package seproject;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * This class allows to select a shape on the screen.
 */
public class SelectedShapeManager extends Tool{
    
    private Shape selectedShape;
    
    public SelectedShapeManager(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }
    
    /**  
     * This function will be called after a click with the mouse on the paper it
     * will select a shape on the screen.
     *
     * @param event is the event that generated the call to this method
    */
    @Override
    public void onMousePressed(MouseEvent event){
        for(Node node : this.getPaper().getChildren()){
            node.setEffect(null);
        }
        for(Node node : this.getPaper().getChildren()){
            if (node.contains(event.getX(),event.getY())){
                DropShadow ds1 = new DropShadow();
                ds1.setOffsetY(4.0f);
                ds1.setOffsetX(2.0f);
                node.setEffect(ds1);
                this.setSelectedShape((Shape)node);
            } 
        }
    }
        
    @Override
    public void onMouseDragged(MouseEvent event){
        
    };
    /**
    * 
    * @return selected shape
    */
    public Shape getSelectedShape() {
        return selectedShape;
    }
    
    
    /**
     * 
     * @param selectedShape set the selected shape
     */
    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }
    
    
    
    
}
