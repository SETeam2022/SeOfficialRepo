package seproject.tools;

import static java.lang.Math.abs;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import seproject.DrawingArea;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;

public class TextTool extends DrawingTool {

    private final static double MIN_RECTANLGE_AREA = 150; // The minimum area of rectangle that will be transformed in TextArea.
    private final static double MIN_RECTANLGE_WIDTH = 50; // The minimum width of rectangle that will be transformed in TextArea.
    private Rectangle tempRectangle;
    private double rStartX, rStartY;
    private final StringProperty textAreaStringProperty;
    private final DoubleProperty textAreaPrefWidthProperty;
    private final DoubleProperty textAreaPrefHeightProperty;
    private final BooleanProperty textAreaVisibleProperty;
    private final ReadOnlyBooleanProperty textAreaFocusedProperty;
    private final DoubleProperty textAreaLayoutXProperty;
    private final DoubleProperty textAreaLayoutYProperty;
    

    private Text text;

    public TextTool(DrawingArea paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
        
        this.textAreaStringProperty = paper.getTextArea().textProperty();
        this.textAreaPrefHeightProperty = paper.getTextArea().prefHeightProperty();
        this.textAreaPrefWidthProperty = paper.getTextArea().prefWidthProperty();
        this.textAreaVisibleProperty = paper.getTextArea().visibleProperty();
        this.textAreaFocusedProperty = paper.getTextArea().focusedProperty();
        this.textAreaLayoutXProperty = paper.getTextArea().layoutXProperty();
        this.textAreaLayoutYProperty = paper.getTextArea().layoutYProperty();
        this.textAreaFocusedProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                return;
            }
            deselect();
        });
    }

    @Override
    public void onMousePressed(MouseEvent event) {
        deselect();
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
        if (tempRectangle.getWidth() <= MIN_RECTANLGE_WIDTH || (tempRectangle.getWidth() * tempRectangle.getHeight()) <= MIN_RECTANLGE_AREA) {
            paper.getChildren().remove(tempRectangle);
            return;
        }
        updateTextAreaFromRectangle();
    }

    /**
     * Remove the temporary textArea and rectangle created for inserting the
     * text
     */
    @Override
    public void deselect() {
        if (text != null) {
            text.strokeProperty().unbind();
            text = null;
        }
        if (tempRectangle != null) {
            paper.getChildren().remove(tempRectangle);
            tempRectangle = null;
        }

        if (!textAreaStringProperty.get().trim().equals("")) {
            Invoker.getInvoker().executeCommand(new DrawShapeCommand(popTextFromTextArea(), paper));
        }
        textAreaVisibleProperty.set(false);
        textAreaStringProperty.set("");

    }

    private Rectangle createTempRectangle(double x, double y) {
        Rectangle rect = new Rectangle(x, y, 0, 0);
        rect.getStrokeDashArray().addAll(2d, 3d);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.GREY);
        return rect;
    }

    private Text popTextFromTextArea() {
        text = new Text(rStartX, rStartY, textAreaStringProperty.get().trim());
        text.wrappingWidthProperty().set(textAreaPrefWidthProperty.get());
        textAreaVisibleProperty.set(false);
        textAreaStringProperty.set("");
        return text;
    }

    // WARNING: YOU CANNOT CALL THIS METHOD IN A DIFFERENT METHOD FROM: "onMouseReleased(MouseEvent event)". IT MAY CAUSE BUGS IN DURING RECTANGLE ADDING.
    private void updateTextAreaFromRectangle() {
        if (tempRectangle == null) {
            return;
        }
        rStartX = tempRectangle.getX();
        rStartY = tempRectangle.getY();
        textAreaLayoutXProperty.set(rStartX);
        textAreaLayoutYProperty.set(rStartY);
        textAreaPrefWidthProperty.set(tempRectangle.getWidth());
        textAreaPrefHeightProperty.set(tempRectangle.getHeight());
        textAreaVisibleProperty.set(true);
        paper.getChildren().remove(tempRectangle);
        tempRectangle = null;
    }

}
