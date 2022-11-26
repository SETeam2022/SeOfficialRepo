 package seproject;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This class is used to model a FileManager able to save the drawn shapes to an
 * xml file and get the saved shapes from an xml file.
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
     * Saves the drawn shapes into an xml file.
     *
     * @throws IOException
     */
    public void save(File f) throws IOException {
        
        if(f == null){ return; }
        
        try ( XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(Files.newOutputStream(f.toPath())))) {
            encoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.writeObject(paper.getChildren().toArray(new Node[0]));
        }
    }

    /**
     * Loads a drawing from an xml file.
     *
     * @throws IOException
     */
    public void load(File f) throws IOException {
        
        if (f == null || f.length() <= 0) {
            return;
        }

        try ( XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(Files.newInputStream(f.toPath())))) {
            decoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });

            paper.getChildren().setAll((Node[]) decoder.readObject());
        }
    }

}
