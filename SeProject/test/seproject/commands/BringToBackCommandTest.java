/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import seproject.tools.SelectedShapeManager;

/**
 *
 * @author bvs
 */
public class BringToBackCommandTest {
    
    private SelectedShapeManager ssm;
    private Pane paper;
    private Rectangle rect;
    int beforeBringToFront, afterBringToFront, afterUndo,afterBringToBack;
    private Command cmd;
    
    @Before
    public void setUp() {
        paper = new Pane();
        rect = new Rectangle(10,10,10,10);
        paper.getChildren().clear();
        paper.getChildren().add(rect);
        SelectedShapeManager.setSelectedShapeManagerPaper(paper);
        ssm = SelectedShapeManager.getSelectedShapeManager();
        ssm.setSelectedShape(rect);
        
    }

    /**
     * Test of execute method, of class BringToBackCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        beforeBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        cmd = new BringToFrontCommand(ssm.getSelectedShape(),paper);
        cmd.execute();
        afterBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToFront > beforeBringToFront);
        cmd = new BringToBackCommand(ssm.getSelectedShape(),paper);
        cmd.execute();
        afterBringToBack = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToBack < afterBringToFront);
    }

    /**
     * Test of undo method, of class BringToBackCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        beforeBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        cmd = new BringToFrontCommand(ssm.getSelectedShape(),paper);
        cmd.execute();
        afterBringToFront = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToFront > beforeBringToFront);
        cmd = new BringToBackCommand(ssm.getSelectedShape(),paper);
        cmd.execute();
        afterBringToBack = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterBringToBack < afterBringToFront);
        
        // undo
        
        cmd.undo();
        afterUndo = paper.getChildren().indexOf(ssm.getSelectedShape());
        assertTrue(afterUndo > afterBringToBack);
        
    }
    
}
