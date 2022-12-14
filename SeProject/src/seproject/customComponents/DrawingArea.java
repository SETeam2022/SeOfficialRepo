package seproject.customComponents;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * This class creates an abstraction of the application's area where the user
 * can draw. One of the main goals of the drawing area is the management of the
 * grid that is in overlay with the drawing. 
 * The DrawingArea pane is made by two principal elements:
 * <ul>
    <li>The grid: a group containing all the lines that builds the grid</li>
 *  <li>The paper: a pane on which all the shapes are added</li>
 * </ul>
 * This object also implements the behaviour of a LayeredPaper on witch the tools can work
 *
 */
public class DrawingArea extends Pane implements LayeredPaper {

    private static final double CONV_FACTOR = 37.7952755906; // 1cm =  37.7952755906 pixels

    private final Pane paper;

    private Group grid;

    /**
     * Creates an instance of the Drawing Area.
     *
     * @param height the height of the drawing area
     * @param width the width of the drawing area
     */
    public DrawingArea(double width, double height){
        super.setPrefSize(width, height);
        paper = new Pane();
        paper.setId("paper");
        paper.setPrefSize(width, height);
        grid = makeGrid(1);
        grid.setVisible(false);
        super.setClip(new Rectangle(0, 0, width, height));
        super.getChildren().addAll(paper,grid);
    }

    /**
     * Redraw the grid, with a new size for the side's grids's square.
     *
     * @param newDistance the size in cm of the grid's square
     */
    public void redrawGrid(int newDistance) {
        boolean oldValue = grid.visibleProperty().getValue();
        super.getChildren().remove(grid);
        this.grid = makeGrid(newDistance);
        this.grid.setVisible(oldValue);
        super.getChildren().add(grid);
    }

    /**
     * Makes the overlaying grid visible.
     *
     * @param val true if the grid must be visible, flase if the grid must be
     * invisible
     */
    public void showGrid(boolean val) {
        grid.setVisible(val);
    }

    /**
     * Getter for the paper.
     *
     * @return the Pane that works as a papaer
     */
    public Pane getPaper() {
        return this.paper;
    }

    /**
     * Getter for the grid.
     *
     * @return the group containing the lines that makes the grid
     */
    public Group getGrid() {
        return this.grid;
    }

    /**
     * Adds a shape into the the paper.
     *
     * @param shape the shape that will be added.
     */
    @Override
    public void addInPaper(Shape shape) {
        paper.getChildren().add(shape);
    }
    
    /**
     * Adds a shape at the given index in the paper
     * @param index the index where the paper will be added
     * @param shape the shape that will be added
     */
    @Override
    public void addInPaper(int index, Shape shape) {
        paper.getChildren().add(index,shape);
    }

    /**
     * Removes a shape from the paper.
     *
     * @param shape the shape that will be removed
     * @return true if the shape has been removed, false otherwise
     */
    @Override
    public boolean removeFromPaper(Shape shape) {
        return paper.getChildren().remove(shape);
    }
    
    /**
     * Verify that the paper contains a given Shape
     * @param shape that whe whant to test
     * @return true if the paper contains the shape false otherwhise
     */  
    @Override
    public boolean paperContains(Shape shape) {
        return this.paper.getChildren().contains(shape);
    }

    /**
     * Adds a node on the top of the  paper
     * @param node remove the node from the highest level of the paper.
     */
    @Override
    public void addInTopLayer(Node node) {
        super.getChildren().add(node);
    }
    
    /**
     * Return the index of the shape in the paper
     * @param shape the shape that we are searching
     * @return the index of the given shape or -1 if is not present
     */
    @Override
    public int indexInPaper(Shape shape) {
        return paper.getChildren().indexOf(shape);
    }
    

    /**
     * Remove a node from the layers that are on the paper
     * @param node remove the node from the highest level of the paper.
     * @return true if the node has been eliminated, flase otherwhise
     */
    @Override
    public boolean removeFromTopLayer(Node node) {
        return super.getChildren().remove(node);
    }
    
    /**
     * Getter for the paper size
     * @return the number of shapes that are in the paper
     */
    @Override
    public int getPaperSize() {
        return paper.getChildren().size();
    }

    private Group makeGrid(int newDistance) {
        double distanceInPixel = newDistance * CONV_FACTOR;
        Group g = new Group();
        for (int x = 1; x * distanceInPixel < paper.getPrefWidth(); x++) {
            g.getChildren().add(lineCreatorX(x * distanceInPixel));
        }
        for (int y = 1; y * distanceInPixel < paper.getPrefHeight(); y++) {
            g.getChildren().add(lineCreatorY(y * distanceInPixel));
        }
        g.setMouseTransparent(true);
        g.setManaged(false);
        return g;
    }

    private Line lineCreatorX(double x) {
        Line l = new Line(x, 0, x, paper.getPrefHeight());
        l.setStroke(new Color(0, 0, 0, 0.5));
        return l;
    }

    private Line lineCreatorY(double y) {
        Line l = new Line(0, y, paper.getPrefWidth(), y);
        l.setStroke(new Color(0, 0, 0, 0.5));
        return l;
    }



}
