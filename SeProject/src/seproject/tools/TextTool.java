package seproject.tools;

import static java.lang.Math.abs;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import seproject.customComponents.DrawingArea;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;

/**
 * This class is the representation of a specialized tool that can draw a
 * Text on the screen.
 * 
 */
public class TextTool extends DrawingTool {

    private final static double MIN_RECTANLGE_AREA = 150; // The minimum area of rectangle that will be transformed in TextArea.
    private final static double MIN_RECTANLGE_WIDTH = 50; // The minimum width of rectangle that will be transformed in TextArea.

    private TextArea tempTextArea;
    private Rectangle tempRectangle;
    private Text shape;
    private final ReadOnlyObjectProperty<Integer> textSpinnerValueProperty;
    private final SimpleObjectProperty<Font> fontProperty;

    private double rStartX, rStartY;

    /**
     * This tool allows you to add text in the form of a Shape to your drawing
     * area.The text will be entered by the user in the appropriate TextArea
     * created after having drawn a rectangle by clicking on the drawing area
     * and dragging with the mouse.
     *
     * @param paper the drawing area
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * ColorPicker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * ColorPicker's value.
     * @param textSpinnerValueProperty is the spinner's IntegerValueProperty
     * used to manage the text size.
     *
     */
    public TextTool(DrawingArea paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty, ReadOnlyObjectProperty<Integer> textSpinnerValueProperty) {
        super(paper, strokeColorProperty, fillColorProperty);

        this.textSpinnerValueProperty = textSpinnerValueProperty;
        this.fontProperty = new SimpleObjectProperty<>(Font.font(11));
        textSpinnerValueProperty.addListener((cl, oldVal, newVal) -> {
            fontProperty.set(Font.font(Font.getDefault().getFamily(), newVal));
        });

    }

    /**
     * This function will be called after a click with the mouse on the
     * DrawingArea, a dotted rectangle will be instantiated which will guide the
     * user in setting the dimensions of the TextArea which will be
     * transformed into Text.
     *
     * @param event is the event that generated the call to this method its X
     * and Y coordinates will be used for setting up the top left corner of the
     * rectangle.
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        deselect();
        rStartX = event.getX();
        rStartY = event.getY();
        createTempRectangle(rStartX, rStartY);
    }

    /**
     * This event will record the mouse drag after pressing on the DrawingArea.
     *
     * @param event based on the mouse coordinates during this event, the size
     * of the rectangle at the end of that event, will be the TextArea's size.
     */
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

    /**
     * If the guide Rectangle is null or does not reach the minimum size
     * requirements for instantiating a TextArea, the Tool is reset. Otherwise,
     * a TextArea will be instantiated.
     *
     * @param event
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        if (tempRectangle == null || tempRectangle.getWidth() <= MIN_RECTANLGE_WIDTH || (tempRectangle.getWidth() * tempRectangle.getHeight()) <= MIN_RECTANLGE_AREA) {
            resetTool();
            return;
        }
        createTextAreaFromRectangle();
    }

    /**
     * Executing this method will unbind the border of the figure. It will then
     * attempt to draw any text in the last TextArea.
     */
    @Override
    public void deselect() {
        if (shape != null) {
            shape.fillProperty().unbind();
            shape.strokeProperty().unbind();
        }
        drawTextFromTextArea();
    }

    private void createTempRectangle(double x, double y) {
        this.tempRectangle = new Rectangle(x, y, 0, 0);
        this.tempRectangle.getStrokeDashArray().addAll(2d, 3d);
        this.tempRectangle.setFill(Color.TRANSPARENT);
        this.tempRectangle.setStroke(Color.GREY);
        paper.addShape(this.tempRectangle);
    }

    private void drawTextFromTextArea() {
        if (tempTextArea != null) {
            String text = tempTextArea.textProperty().get().trim();
            if (!(text == null || "".equals(text))) {
                shape = new Text(rStartX, rStartY, text);
                shape.fillProperty().set(getFillColorProperty().get());
                shape.strokeProperty().set(getStrokeColorProperty().get());
                shape.setStyle("-fx-font-size: " + textSpinnerValueProperty.get() + "px;");
                //shape.setBoundsType(TextBoundsType.VISUAL);
                shape.wrappingWidthProperty().set(tempTextArea.widthProperty().get());
                Invoker.getInvoker().executeCommand(new DrawShapeCommand(shape, paper));
            }
        }
        resetTool();
    }

    /**
     * This method is called when the guide rectangle drawn by the user is ready
     * to be transformed into the TextArea in which the user can write.<br>
     * <b>WARNING</b>: YOU CANNOT CALL THIS METHOD IN A DIFFERENT METHOD FROM:
     * "onMouseReleased(MouseEvent event)". IT MAY CAUSE BUGS IN DURING
     * RECTANGLE ADDING.
     */
    private void createTextAreaFromRectangle() {
        if (tempRectangle == null) {
            return;
        }
        this.tempTextArea = new TextArea();
        rStartX = tempRectangle.getX();
        rStartY = tempRectangle.getY();
        this.tempTextArea.relocate(rStartX, rStartY);
        this.tempTextArea.setWrapText(true);
        this.tempTextArea.fontProperty().bind(fontProperty);
        this.tempTextArea.prefWidthProperty().set(tempRectangle.getWidth());
        this.tempTextArea.prefHeightProperty().set(tempRectangle.getHeight());
        unSetRectangle();

        paper.getContainerOfPaperAndGrid().getChildren().add(this.tempTextArea);
    }

    private void unSetRectangle() {
        if (this.tempRectangle == null) {
            return;
        }
        paper.removeShape(tempRectangle);
        tempRectangle = null;
    }

    private void unSetTempTextArea() {
        if (this.tempTextArea == null) {
            return;
        }
        paper.getContainerOfPaperAndGrid().getChildren().remove(this.tempTextArea);
        tempTextArea.fontProperty().unbind();
        tempTextArea = null;
    }

    private void resetTool() {
        unSetRectangle();
        unSetTempTextArea();
    }
}
