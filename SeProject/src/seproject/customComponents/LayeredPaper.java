package seproject.customComponents;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

/**
 *
 * @author alewi
 */
public interface LayeredPaper {
    public void addInPaper(Shape shape);
    public boolean removeFromPaper(Shape shape);
    public boolean paperContains(Shape shape);
    public void addInTopLayer(Node node);
    public boolean removeFromTopLayer(Node node);
    
}
