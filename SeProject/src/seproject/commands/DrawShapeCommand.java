package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import seproject.tools.SelectedShapeManager;

public class DrawShapeCommand implements Command{
    
    private final Shape shape;
    
    private final Pane paper;
    
    /**
     * This class is the rappresentation of the 
     * @param shape the shape that will be added to the paper
     * @param paper the paper on witch the shape will be added
     */
    public DrawShapeCommand(Shape shape, Pane paper){
        this.shape = shape;
        this.paper = paper;
    }
    
    /**
     * Draw the shape on the paper
     */
    @Override
    public void execute() {
        paper.getChildren().add(shape);
    }
    
    /**
     * Delete the drawed shape from the paper
     */
    @Override
    public void undo() {
        SelectedShapeManager.getSelectedShapeManager().unsetSelectedShape();
        paper.getChildren().remove(shape);
    }
    
}
