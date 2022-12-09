package seproject.commands;

import javafx.scene.shape.Shape;
import seproject.shapes.DrawableShape;

public class TranslationCommand implements Command {

    private final DrawableShape shape;
    private final double posX, posY, startX, startY, scaleX, scaleY,eventX,eventY;

    public TranslationCommand(DrawableShape shape ,double posX, double posY, double startX, double startY,double scaleX,double scaleY,double eventX , double eventY) {
        this.shape = shape;
        this.posX = posX;
        this.posY = posY;
        this.startX = startX;
        this.startY = startY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.eventX = eventX;
        this.eventY = eventY;
    }

    @Override
    public void execute() {
        shape.setShapeTranslateX(eventX/scaleX - posX);
        shape.setShapeTranslateY(eventY/scaleY - posY);
    }

    @Override
    public void undo() {
        shape.setShapeTranslateX(startX);
        shape.setShapeTranslateY(startY);
    }
}
