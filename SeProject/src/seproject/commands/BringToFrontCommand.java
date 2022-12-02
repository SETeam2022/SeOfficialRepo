/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author bvs
 */
public class BringToFrontCommand implements Command{
    
    private Shape shape;
    private Pane paper;
    private int index;

    public BringToFrontCommand(Shape shape, Pane paper) {
        this.shape = shape;
        this.paper = paper;
    }

    @Override
    public void execute() {
        index = this.paper.getChildren().indexOf(this.shape);
        this.shape.toFront();
    }

    @Override
    public void undo() {
        paper.getChildren().remove(shape);
        paper.getChildren().add(index, shape);
    }
    
}
