package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class BringToBackCommand implements Command {

    private Shape shape;
    private Pane paper;
    private int index;

    public BringToBackCommand(Shape shape, Pane paper) {
        this.shape = shape;
        this.paper = paper;
    }

    @Override
    public void execute() {
        index = this.paper.getChildren().indexOf(this.shape);
        this.shape.toBack();
    }

    @Override
    public void undo() {
        paper.getChildren().remove(shape);
        paper.getChildren().add(index, shape);
    }

}
