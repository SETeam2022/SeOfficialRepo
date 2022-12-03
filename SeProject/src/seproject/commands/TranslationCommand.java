package seproject.commands;

import javafx.scene.shape.Shape;

public class TranslationCommand implements Command {

    private final Shape shape;
    private final double posX, posY, startX, startY, scaleX, scaleY,eventX,eventY;

    public TranslationCommand(Shape shape ,double posX, double posY, double startX, double startY,double scaleX,double scaleY,double eventX , double eventY) {
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
        shape.setTranslateX(eventX/scaleX - posX);
        shape.setTranslateY(eventY/scaleY - posY);
    }

    @Override
    public void undo() {
        shape.setTranslateX(startX);
        shape.setTranslateY(startY);
    }
}
