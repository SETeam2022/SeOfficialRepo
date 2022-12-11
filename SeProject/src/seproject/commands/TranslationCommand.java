package seproject.commands;

import javafx.scene.shape.Shape;

/**
 * An object of this class represents the action of translating a shape, the
 * object also stores all the information needed for the undo of its operation.
 * 
 */
public class TranslationCommand implements Command {

    private final Shape shape;
    private final double posX, posY, startX, startY, scaleX, scaleY,eventX,eventY;

    /**
     * Creates a TranslationCommand.
     * 
     * @param shape the shape which will be translated
     * @param posX x position
     * @param posY y position
     * @param startX x start value
     * @param startY y start value
     * @param scaleX x scale value
     * @param scaleY y scale value
     * @param eventX x coordinate of the event
     * @param eventY y coordinate of the event
     */
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

    /**
     * Translates a shape.
     */
    @Override
    public void execute() {
        shape.setTranslateX(eventX/scaleX - posX);
        shape.setTranslateY(eventY/scaleY - posY);
    }

    /**
     * Makes the shape go back to its previous position.
     */
    @Override
    public void undo() {
        shape.setTranslateX(startX);
        shape.setTranslateY(startY);
    }
}
