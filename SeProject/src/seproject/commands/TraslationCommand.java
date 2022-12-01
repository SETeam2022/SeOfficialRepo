/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.commands;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author bvs
 */
public class TraslationCommand implements Command{
    
    private final Shape shape;
    private final double posX;
    private final double posY;
    private final double startX;
    private final double startY;
    private final MouseEvent event;
    private final Rectangle selectionRectangle;

    public TraslationCommand(Shape shape, double posX,double posY,double startX,double startY, MouseEvent event, Rectangle selectionRectangle) {
        this.shape = shape;
        this.posX = posX;
        this.posY = posY;
        this.startX = startX;
        this.startY = startY;
        this.event = event;
        this.selectionRectangle = selectionRectangle;
    }

    @Override
    public void execute() {
        shape.setTranslateX(event.getSceneX()-posX);
        shape.setTranslateY(event.getSceneY()-posY);
        //System.out.println("1-> " + (event.getSceneX()-posX));
        this.selectionRectangle.setX(shape.getBoundsInParent().getMinX());
        this.selectionRectangle.setY(shape.getBoundsInParent().getMinY());
    }

    @Override
    public void undo() {
        shape.setTranslateX(startX);
        shape.setTranslateY(startY);
        this.selectionRectangle.setX(shape.getBoundsInParent().getMinX());
        this.selectionRectangle.setY(shape.getBoundsInParent().getMinY());
    }
    
}
