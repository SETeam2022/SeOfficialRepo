package seproject;

import editor.ShapeEditor;
import editor.ShapeEditorFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 * This class is used to model a FileManager able to save the drawn shapes to a
 * binary file and get the saved shapes from an binary file.
 *
 */
public class FileManager {

    private final Pane paper;

    /**
     * Creates a FileManager with the specified Pane.
     *
     * @param paper The current paper of the application, containing drawn
     * shapes.
     */
    public FileManager(Pane paper) {
        this.paper = paper;
    }

    /**
     * Saves the drawn shapes into a binary file.
     *
     * @param f
     * @throws IOException
     */
    public void save(File f) throws IOException {
        
        if (f == null) {
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))){
            out.writeInt(paper.getChildren().size());
            for (Node n : paper.getChildren()){
                if (n instanceof Shape) {
                    Shape shape = (Shape) n;
                    ShapeEditor editor = ShapeEditorFactory.getInstance(shape.getClass());
                    editor.saveShape(shape, out);
                }
                
            }
        }
        
    }

    /**
     * Loads a drawing from a binary file.
     *
     * @param f
     * @throws IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public void load(File f) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        if (f == null || f.length() <= 0) {
            return;
        }
        
        paper.getChildren().clear();
        
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)))){
            int size = in.readInt();
            for(int i=0; i<size; i++){
                Class c = (Class) in.readObject();
                ShapeEditor editor = ShapeEditorFactory.getInstance(c);
                Shape s = editor.loadShape(c, in);
                paper.getChildren().add(s);
            }
        }

    }

}
