package seproject.tools;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import seproject.Constants;
import seproject.commands.DrawShapeCommand;
import seproject.commands.Invoker;
import seproject.customComponents.LayeredPaper;

/**
 * This class is the representation of a specialized tool which can draw
 * Polygons on the screen.
 *
 */
public class PolygonTool extends DrawingTool {

    private Polyline polygon;
    private static final double DELTA = 50;

    /**
     * Create a new PolygonTool.
     *
     * @param paper is the pane on which the new polygons will be added.
     * @param strokeColorProperty is the associated ObjectProperty of Stroke
     * ColorPicker's value.
     * @param fillColorProperty is the associated ObjectProperty of Fill
     * ColorPicker's value.
     */
    public PolygonTool(LayeredPaper paper, ObjectProperty<Color> strokeColorProperty, ObjectProperty<Color> fillColorProperty) {
        super(paper, strokeColorProperty, fillColorProperty);
    }

    /**
     * This method will be called each time the mouse is pressed on the paper.
     * When the mouse is pressed and there's no partially created shape, the
     * shape is added to the pane's children and its default configuration is
     * set, while if there's already a partially created shape, the next presses
     * will turn into vertices added to the polygon.
     *
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for the points of the polygon
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        if (polygon == null || !this.getPaper().paperContains(polygon)){
            polygon = new Polyline();
            polygon.getPoints().addAll(event.getX(), event.getY());
            polygon.setStroke(this.getStrokeColorProperty().getValue());
            polygon.setFill(this.getFillColorProperty().getValue());
            polygon.setStrokeWidth(Constants.STROKE_WIDTH);
            Invoker.getInvoker().executeCommand(new DrawShapeCommand(polygon, getPaper()));
        }
        polygon.getPoints().addAll(event.getX(), event.getY());
    }

    /**
     * This method will be called each time the mouse is dragged on the paper.
     * When the mouse is dragged, in order to give a visual feedback to the user
     * of the polygon being created, at each call of the method, the last added
     * point is updated.
     *
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for polygon's current status' rendering.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        polygon.getPoints().set(polygon.getPoints().size() - 2, event.getX());
        polygon.getPoints().set(polygon.getPoints().size() - 1, event.getY());
    }

    /**
     * This method will be called each time the mouse is released. When the
     * mouse is released, first a method is called to check if the point in
     * which the release has happened is near the first point added by the user,
     * in other words we want to check if the user wanted to close the polygon
     * but has missed the precise coordinates due to an error of the mouse
     * movement. If this check is affermative, then the coordinates of the last
     * point are set equal to the coordinates of the first one, otherwise it
     * means that the polygon has not been finished yet and we simply repalace
     * the coordinates of the last point with the ones of the event.
     *
     * @param event is the event that generated the call to this method, its X
     * and Y coordinates will be used for polygon's final rendering.
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        if (isNearStart(polygon.getPoints().get(0), polygon.getPoints().get(1), event.getX(), event.getY())) {
            polygon.getPoints().set(polygon.getPoints().size() - 2, polygon.getPoints().get(0));
            polygon.getPoints().set(polygon.getPoints().size() - 1, polygon.getPoints().get(1));
            polygon = null;
        } else {
            polygon.getPoints().set(polygon.getPoints().size() - 2, event.getX());
            polygon.getPoints().set(polygon.getPoints().size() - 1, event.getY());
        }
    }

    /**
     * This method will be called when the user discards this tool in favor of
     * another one. In this case the shape (if not yet completed) has to be
     * closed.
     */
    @Override
    public void deselect() {
        if (polygon == null) return;
        double startX = polygon.getPoints().get(0), startY = polygon.getPoints().get(1),
                endX = polygon.getPoints().get(polygon.getPoints().size() - 2), endY = polygon.getPoints().get(polygon.getPoints().size() - 1);
        if (!(startX == endX && startY == endY))
            polygon.getPoints().addAll(startX, startY);
    }

    /**
     * This is a utility method which, given the start and end coordinates, and
     * a fixed DELTA, checks whether the two points can be considered the same
     * in relation to the tolerance bounds.
     *
     * @param xStart x coordinate of the first point of the polygon
     * @param yStart y coordinate of the first point of the polygon
     * @param x x coordinate of the last detected point
     * @param y y coordinate of the last detected point
     * @return boolean
     */
    private boolean isNearStart(double xStart, double yStart, double x, double y) {
        return Math.abs(Math.abs(x) - Math.abs(xStart)) < DELTA && Math.abs(Math.abs(y) - Math.abs(yStart)) < DELTA;
    }

}
