/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import seproject.shapes.DrawableShape;

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
        grid = makeGrid(newDistance);
        this.containerOfPaperAndGrid.getChildren().add(grid);
    }

    public void showGrid(boolean val) {
        grid.setVisible(val);
    }

    public Group getContainerOfPaperAndGrid() {
        return this.containerOfPaperAndGrid;
    }

    public Pane getPaper() {
        return this.paper;
    }

    public void setPaperWidth(double width) {
        paper.setPrefWidth(width);
    }

    public void setPaperHeight(double height) {
        paper.setPrefHeight(height);
    }

    public void addShape(DrawableShape shape) {
        if (shape instanceof Node )
            paper.getChildren().add( (Node)shape);
    }
    
    public boolean removeShape(DrawableShape shape){
        if (shape instanceof Node )
            return paper.getChildren().remove( (Node)shape);
        return false;
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
