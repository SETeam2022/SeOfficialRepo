/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package seproject.commands;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seproject.tools.EllipseTool;
import seproject.tools.SelectedShapeManager;
import seproject.tools.SelectionTool;

/**
 *
 * @author bvs
 */
public class TraslationCommandTest {
    
    private Pane paper;
    private EllipseTool ell;
    private ObjectProperty<Color> borderColorProperty;
    private ObjectProperty<Color> fillColorProperty;
    private SelectionTool st;
    private Ellipse instancedEllipse;
    private TraslationCommand cmd;
    
    @Before
    public void setUp() {
        paper = new Pane();
        borderColorProperty = new SimpleObjectProperty<>();
        fillColorProperty = new SimpleObjectProperty<>();
        borderColorProperty.set(Color.RED);
        fillColorProperty.set(Color.BLACK);
        ell = new EllipseTool(paper, borderColorProperty, fillColorProperty);
        
        st = new SelectionTool(paper);
        SelectedShapeManager.setSelectedShapeManagerPaper(paper);
        
        ell.onMousePressed(new MouseEvent(paper,paper,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        
        ell.onMouseDragged(new MouseEvent(paper,paper,MouseEvent.MOUSE_DRAGGED, 200, 300,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null));
        Node instancedNode = paper.getChildren().get(0);
        Assert.assertTrue("Instanced Node is not an Ellipse", instancedNode instanceof Ellipse);
        instancedEllipse = (Ellipse) instancedNode;
        
    }
    

    /**
     * Test of execute method, of class TraslationCommand.
     */
    @Test
    public void testExecute() {
        MouseEvent e1 = new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_CLICKED, 100,
                200, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null);
        st.onMousePressed(e1);
        
        Ellipse selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();
        
        MouseEvent event = new MouseEvent(paper,instancedEllipse,MouseEvent.MOUSE_DRAGGED, 40, 40,
                0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true,
                true, true, true, true, null);
        
        double offsetX,offsetY,startX,startY; 
        offsetX = e1.getSceneX()- selectedEllipse.getTranslateX();
        offsetY = e1.getSceneY()- selectedEllipse.getTranslateY();
        startX = selectedEllipse.getTranslateX();
        startY = selectedEllipse.getTranslateY();
        
        System.out.println(selectedEllipse.getTranslateX()); 
        
        cmd = new TraslationCommand(selectedEllipse,offsetX,offsetY,startX,startY,event,new Rectangle());
        
        selectedEllipse = (Ellipse) SelectedShapeManager.getSelectedShapeManager().getSelectedShape();
        
        System.out.println(selectedEllipse.getTranslateX());
        
    }

    /**
     * Test of undo method, of class TraslationCommand.
     */
    @Test
    public void testUndo() {
    }
    
}
