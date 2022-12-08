package seproject.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import seproject.DrawingArea;

/**
 * This class is the rappresentation of a unspecialized abstract tool. In order
 * to allow concrete shape creations this class must be concretized.
 */
public abstract class Tool {

    DrawingArea paper;

    /**
     * @param paper is the pane on witch the new Shape nodes will be added
     */
    public Tool(DrawingArea paper) {
        this.paper = paper;
    }

    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provides a conrete implementation by overriding the method
     *
     * @param event the JavaFx event associated with the mouse click
     */
    public void onMousePressed(MouseEvent event) {
    }

    /**
     * This method provides an empty implementation, the class that extends Tool
     * can provides a conrete implementation by overriding the method
     *
     * @param event the JavaFx event associated with the mouse click
     */
    public void onMouseDragged(MouseEvent event) {
    }

    public void onMouseReleased(MouseEvent event) {
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
    public void setPaper(DrawingArea paper) {
        this.paper = paper;
    }

}
