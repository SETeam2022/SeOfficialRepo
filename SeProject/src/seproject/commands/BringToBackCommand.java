package seproject.commands;

import javafx.scene.shape.Shape;
import seproject.customComponents.DrawingArea;

public class BringToBackCommand implements Command {

    private final Shape shape;
    private final DrawingArea paper;
    private int index;

    /**
     * Creates a BringToBackCommand.
     *
     * @param shape the shape which will be brought to back
     * @param paper the paper which the shape belongs to
     */
    public BringToBackCommand(Shape shape, DrawingArea paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * This method brings the shape to back.
     */
    @Override
    public void execute() {
        index = paper.getPaper().getChildren().indexOf(this.shape);
        this.shape.toBack();
    }

    /**
     * This method brings the shape to its original position among the children
     * of the pane.
     */
    @Override
    public void undo() {
        paper.getPaper().getChildren().remove(shape);
        paper.getPaper().getChildren().add(index, shape);
    }

}
