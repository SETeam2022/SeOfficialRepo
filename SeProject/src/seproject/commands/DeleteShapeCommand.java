package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class DeleteShapeCommand implements Command{
    
    private final Shape shape;
    private final Pane paper;
    
    public DeleteShapeCommand(Shape shape, Pane paper){
        this.shape = shape;
        this.paper = paper;
    }

    @Override
    public void execute() {
        paper.getChildren().remove(shape);
        
    }

    @Override
    public void undo() {
        paper.getChildren().add(shape);
    }
    
}
