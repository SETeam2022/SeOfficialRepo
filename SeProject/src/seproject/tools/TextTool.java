package seproject.tools;

import static java.lang.Math.abs;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;

public class TextTool extends DrawingTool {

    private final static double MIN_RECTANLGE_AREA = 150; // The minimum area of rectangle that will be transformed in TextArea.
    private final static double MIN_RECTANLGE_WIDTH = 50; // The minimum width of rectangle that will be transformed in TextArea.
    private TextArea tempTextArea;
    private Rectangle tempRectangle;
    private double rStartX, rStartY;

    public TextTool(Pane paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    @Override
    public void onMousePressed(MouseEvent event) {
        if (tempTextArea != null) {
            deselect();
        }
        rStartX = event.getX();
        rStartY = event.getY();
        tempRectangle = createTempRectangle(rStartX, rStartY);
        paper.getChildren().add(tempRectangle);

    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        if (tempRectangle == null) {
            return;
        }
        double newWidth = abs(rStartX - event.getX());
        double newHeight = abs(rStartY - event.getY());

        double newStartX = Math.min(rStartX, event.getX());
        double newStartY = Math.min(rStartY, event.getY());

        tempRectangle.setX(newStartX);
        tempRectangle.setY(newStartY);
        tempRectangle.setWidth(newWidth);
        tempRectangle.setHeight(newHeight);
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        if (tempRectangle == null) {
            return;
        }

        if (tempRectangle.getWidth() <= MIN_RECTANLGE_WIDTH || tempRectangle.computeAreaInScreen() <= MIN_RECTANLGE_AREA) {
            paper.getChildren().remove(tempRectangle);
            return;
        }

        tempTextArea = createTextAreaFromRectangle();
        if (tempTextArea == null) {
            return;
        }
        paper.getChildren().add(tempTextArea);

        tempTextArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                return;
            }
            deselect();

        });

    }

    /**
     * Remove the temporary textArea and rectangle created for inserting the
     * text
     */
    @Override
    public void deselect() {
        if (tempRectangle != null) {
            paper.getChildren().remove(tempRectangle);
            tempRectangle = null;
        }
        if (tempTextArea != null) {
            if (!tempTextArea.getText().trim().equals("")) {
                Invoker.getInvoker().executeCommand(new DrawShapeCommand(popTextFromTextArea(), paper));
            }
            paper.getChildren().remove(tempTextArea);
            tempTextArea = null;
        }

    }

    private Rectangle createTempRectangle(double x, double y) {
        Rectangle rect = new Rectangle(x, y, 0, 0);
        rect.getStrokeDashArray().addAll(2d, 3d);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.GREY);
        return rect;
    }

    private Text popTextFromTextArea() {
        if (tempTextArea == null) {
            return null;
        }
        Text text = new Text(rStartX, rStartY, tempTextArea.getText());
        text.wrappingWidthProperty().set(tempTextArea.getWidth());
        paper.getChildren().remove(tempTextArea);
        tempTextArea.deselect();
        tempTextArea = null;
        return text;
    }

    // WARNING: YOU CANNOT CALL THIS METHOD IN A DIFFERENT METHOD FROM: "onMouseReleased(MouseEvent event)". IT MAY CAUSE BUGS IN DURING RECTANGLE ADDING.
    private TextArea createTextAreaFromRectangle() {
        if (tempRectangle == null) {
            return null;
        }
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        rStartX = tempRectangle.getX();
        rStartY = tempRectangle.getY();
        textArea.relocate(rStartX, rStartY);

        textArea.setPrefWidth(tempRectangle.getWidth());
        textArea.setPrefHeight(tempRectangle.getHeight());

        paper.getChildren().remove(tempRectangle);
        tempRectangle = null;
        return textArea;
    }

}
