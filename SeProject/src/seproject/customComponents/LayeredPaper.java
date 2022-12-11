package seproject.customComponents;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

/**
 *
 * @author alewi
 */
public interface LayeredPaper {

    /**
     * Adds a shape into the the paper.
     *
     * @param shape the shape that will be added.
     */
    public void addInPaper(Shape shape);

    /**
     * Adds a shape at the given index in the paper
     * @param index the index where the paper will be added
     * @param shape the shape that will be added
     */
    public void addInPaper(int index, Shape shape);

    /**
     * Removes a shape from the paper.
     *
     * @param shape the shape that will be removed
     * @return true if the shape has been removed, false otherwise
     */
    public boolean removeFromPaper(Shape shape);

    /**
     * Verify that the paper contains a given Shape
     * @param shape that whe whant to test
     * @return true if the paper contains the shape false otherwhise
     */ 
    public boolean paperContains(Shape shape);

    /**
     * @return the size of drawing paper's children list.
     */
    public int getPaperSize();

    /**
     * Return the index of the shape in the paper
     * @param shape the shape that we are searching
     * @return the index of the given shape or -1 if is not present
     */
    public int indexInPaper(Shape shape);

    /**
     * Adds a node on the top of the  paper
     * @param node remove the node from the highest level of the paper.
     */
    public void addInTopLayer(Node node);

    /**
     * Remove a node from the layers that are on the paper
     * @param node remove the node from the highest level of the paper.
     * @return true if the node has been eliminated, flase otherwhise
     */
    public boolean removeFromTopLayer(Node node);

}
