package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import seproject.DrawingArea;

public class BringToFrontCommand implements Command {

    private Shape shape;
    private DrawingArea paper;
    private int index;

    /**
     * Creates a BringToFrontCommand.
     *
     * @param shape the shape which will be brought to front
     * @param paper the paper which the shape belongs to
     */
    public BringToFrontCommand(Shape shape, DrawingArea paper) {
        this.shape = shape;
        this.paper = paper;
    }

    /**
     * This method brings the shape to front.
     */
    @Override
    public void execute() {
        index = this.paper.getPaper().getChildren().indexOf(this.shape);
        this.shape.toFront();
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
