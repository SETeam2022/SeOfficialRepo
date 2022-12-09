/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject.customComponents;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * This class create an abstraction of the application's area where the user can
 * draw, one of the principal aim of the drawing area is the management of the
 * grid that is in overlay with the drawing.
 */
public class DrawingArea extends Pane {
    
    
    private static final double CONV_FACTOR =  37.7952755906; // 1cm =  37.7952755906 pixels
    
    private  Pane paper;
    
    private Group grid;

    private Group containerOfPaperAndGrid;

    /**
     * Create an isstance of the Drawing Area
     *
     * @param height
     * @param width
     */
    public DrawingArea(double width, double height) {
        super.setPrefSize(width, height);
        paper = new Pane();
        paper.setId("paper");
        paper.setPrefSize(width, height);
        grid = makeGrid(1);
        grid.setVisible(false);
        containerOfPaperAndGrid = new Group(paper,grid);
        containerOfPaperAndGrid.setClip(new Rectangle (0,0, width,height));
        super.getChildren().add(containerOfPaperAndGrid);
        
    }

    /**
     * Redraw the grid, with a new size for the side's grids's square
     *
     * @param newDistance the size in cm of the grid's square
     */
    public void redrawGrid(int newDistance) {
        this.containerOfPaperAndGrid.getChildren().remove(grid);
        this.grid = makeGrid(newDistance);
        this.containerOfPaperAndGrid.getChildren().add(grid);
    }
    /**
     * Makes the overlaying grid visible
     * @param val true if the grid must be visible, flase if the grid must be invisible
     */
    public void showGrid(boolean val) {
        grid.setVisible(val);
    }
    /**
     * The paper and grid group contains the two main elements of the DrawingArea:
     * <ul>
     *  <li>The grid: a group containing all the lines that builds the grid</li>
     *  <li>The paper: a pane on witch all the shapes are added</li>
     * </ul>
     * @return the group containing the two elements.
     */
    public Group getContainerOfPaperAndGrid() {
        return this.containerOfPaperAndGrid;
    }
    
    /**
     * Getter for the paper
     * @return  the Pane that works as a papaer
     */
    public Pane getPaper() {
        return this.paper;
    }
    
    /**
     * Gettet fo the grid
     * @return the group containing the lines that makes the grid
     */
    public Group getGrid(){
        return this.grid;
    }
    /**
     * Adds a shape into the the paper
     * @param shape the shape that will be added.
     */
    public void addShape(Shape shape) {
        paper.getChildren().add(shape);
    }
    
    /**
     * Remove a shape from the paper
     * @param shape the shape that will be removed
     * @return true if the shape has been removed, false otherwise
     */
    public boolean removeShape(Shape shape){
        return paper.getChildren().remove(shape);
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
