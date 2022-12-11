package seproject.customComponents;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

/**
 *
 * @author alewi
 */
public interface LayeredPaper {

    /**
     * Allows the addition of a Shape to the drawing paper.
     *
     * @param shape
     */
    public void addInPaper(Shape shape);

    /**
     *
     *
     *
     * @param index
     * @param shape
     */
    public void addInPaper(int index, Shape shape);

    /**
     *
     * @param shape
     * @return
     */
    public boolean removeFromPaper(Shape shape);

    /**
     *
     * @param shape
     * @return
     */
    public boolean paperContains(Shape shape);

    /**
     * @return the size of drawing paper's children list.
     */
    public int getPaperSize();

    /**
     *
     * @param shape
     * @return
     */
    public int indexInPaper(Shape shape);

    /**
     *
     * @param node
     */
    public void addInTopLayer(Node node);

    /**
     *
     * @param node
     * @return
     */
    public boolean removeFromTopLayer(Node node);

}
