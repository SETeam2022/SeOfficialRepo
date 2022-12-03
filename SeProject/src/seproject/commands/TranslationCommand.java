package seproject.commands;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class TranslationCommand implements Command {

    private final Shape shape;
    private final double posX, posY, startX, startY, scaleX, scaleY;
    private final MouseEvent event;

    public TranslationCommand(Shape shape ,double posX, double posY, double startX, double startY,double scaleX,double scaleY,MouseEvent event) {
        this.shape = shape;
        this.posX = posX;
        this.posY = posY;
        this.startX = startX;
        this.startY = startY;
        this.event = event;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public void execute() {
        shape.setTranslateX(event.getSceneX()/scaleX - posX);
        shape.setTranslateY(event.getSceneY()/scaleY - posY);
    }

    @Override
    public void undo() {
        shape.setTranslateX(startX);
        shape.setTranslateY(startY);
    }
}
