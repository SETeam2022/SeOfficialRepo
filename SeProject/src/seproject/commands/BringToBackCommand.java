package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class BringToBackCommand implements Command {

    private Shape shape;
    private Pane paper;
    private int index;

    /**
     * Creates a BringToBackCommand. 
     * @param shape the shape which will be brought to back
     * @param paper the paper which the shape belongs to
     */
    public BringToBackCommand(Shape shape, Pane paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * This method brings the shape to back.
     */
    @Override
    public void execute() {
        index = this.paper.getChildren().indexOf(this.shape);
        this.shape.toBack();
    }

    /**
     * This method brings the shape to its original position 
     * among the children of the pane.
     */
    @Override
    public void undo() {
        paper.getChildren().remove(shape);
        paper.getChildren().add(index, shape);
    }

}
