package seproject.commands;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class TranslationCommand implements Command {

    private final Shape shape;
    private final double posX, posY, startX, startY;
    private final MouseEvent event;

    public TranslationCommand(Shape shape, double posX, double posY, double startX, double startY, MouseEvent event) {
        this.shape = shape;
        this.posX = posX;
        this.posY = posY;
        this.startX = startX;
        this.startY = startY;
        this.event = event;
    }

    @Override
    public void execute() {
        shape.setTranslateX(event.getSceneX() - posX);
        shape.setTranslateY(event.getSceneY() - posY);
    }

    @Override
    public void undo() {
        shape.setTranslateX(startX);
        shape.setTranslateY(startY);
    }

}
