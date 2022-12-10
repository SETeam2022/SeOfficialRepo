package seproject.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import seproject.customComponents.DrawingArea;

/**
 * This class is the representation of a unspecialized abstract tool. In order
 * to allow concrete shapes creation this class must be concretized.
 */
public abstract class Tool {

    DrawingArea paper;

    /**
     * Creation of a Tool.
     * 
     * @param paper is the pane on which the new Shape nodes will be added
     */
    public Tool(DrawingArea paper) {
        this.paper = paper;
    }

    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provide a concrete implementation by overriding the method.
     *
     * @param event the JavaFx event associated to the mouse click
     */
    public abstract void onMousePressed(MouseEvent event);

    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provide a concrete implementation by overriding the method.
     *
     * @param event the JavaFx event associated to the mouse click
     */
    public abstract void onMouseDragged(MouseEvent event);
    
    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provide a concrete implementation by overriding the method.
     *
     * @param event the JavaFx event associated to the mouse click
     */
    public abstract void onMouseReleased(MouseEvent event);
    
    /**
     * This method provides an empty implementation and should be overreaded by
     * all the concrete tools that want to give a custom behavior when the tool
     * is deselected.
     */
    public void deselect(){
    }

    /**
     * @return the pane that works as a paper which the tool is working on
     */
    public Pane getPaper() {
        return paper;
    }

    /**
     * @param paper the pane on which the tool will work
     */
    public void setPaper(DrawingArea paper) {
        this.paper = paper;
    }

}
