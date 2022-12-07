/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;

/**
 *
 * @author teodoroadinolfi
 */
public class DrawingArea extends Group {
    
    Pane paper;
    Group grid;
    
    public DrawingArea(Pane paper){
        this.paper = paper;
        this.grid = makeGrid();
        super.getChildren().add(new Group(paper,grid));
        paper.setClip(new Rectangle (0,0, paper.getPrefWidth(),paper.getPrefHeight()));
    }
    
    
    public void redrawGrid(int newDistance){
        
    }
    
    private Group makeGrid(){
        Group g = new Group();
        for (int x=38 ; x < paper.getPrefWidth() ; x+=38){
            g.getChildren().add(lineCreatorX(x));
        }
        for (int y=38 ; y < paper.getPrefHeight() ; y+=38){
            g.getChildren().add(lineCreatorY(y));
        }
        g.setManaged(false);
        return g;
    }
    
    private Line lineCreatorX(int x){
        Line l = new Line(x,0,x,paper.getPrefHeight());
        l.setStroke(new Color(255,255,255,0.5));
        return l;
    }
    
    private Line lineCreatorY(int y){
       Line l = new Line(0,y,paper.getPrefWidth(),y);
       l.setStroke(new Color(255,255,255,0.5));
       return l;
    }
    
    
}
