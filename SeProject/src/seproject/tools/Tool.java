package seproject.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * This class is the rappresentation of a unspecialized abstract tool. In order
 * to allow concrete shape creations this class must be concretized.
 */
public abstract class Tool {

    Pane paper;

    /**
     * @param paper is the pane on witch the new Shape nodes will be added
     */
    public Tool(Pane paper) {
        this.paper = paper;
    }

    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provides a conrete implementation by overriding the method
     *
     * @param event the JavaFx event associated with the mouse click
     */
    public abstract void onMousePressed(MouseEvent event);

    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provides a conrete implementation by overriding the method
     *
     * @param event the JavaFx event associated with the mouse click
     */
    public abstract void onMouseDragged(MouseEvent event);
    
    /**
     * Manca la dovumentazione
     * @param event 
     */
    public abstract void onMouseReleased(MouseEvent event);
    
    /**
     * This method provide an empty implementation and should be overreaded by
     * all the concrete tools that want to give a custom behavior when the tool
     * is deselected
     */
    public void deselect(){
    }

    /**
     * @return the pane that works as a paper witch the tool is working
     */
    public Pane getPaper() {
        return paper;
    }

    /**
     * @param paper the pane on witch the tool will work
     */
    public void setPaper(Pane paper) {
        this.paper = paper;
    }

}
