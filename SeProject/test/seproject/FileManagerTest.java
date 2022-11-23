package seproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileManagerTest {
    
    private FileManager fm;
    private Pane paper;
    private String expectedXML, actualXML;
    final private String out1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    "<java version=\"1.8.0_341\" class=\"java.beans.XMLDecoder\">" +
    " <array class=\"javafx.scene.Node\" length=\"0\"/>" +
    "</java>",
    out2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    "<java version=\"1.8.0_341\" class=\"java.beans.XMLDecoder\">" +
    " <array class=\"javafx.scene.Node\" length=\"1\">" +
    "  <void index=\"0\">" +
    "   <object class=\"javafx.scene.shape.Rectangle\">" +
    "    <void property=\"fill\">" +
    "     <object class=\"javafx.scene.paint.Color\">" +
    "      <double>1.0</double>" +
    "      <double>0.7529411911964417</double>" +
    "      <double>0.7960784435272217</double>" +
    "      <double>1.0</double>" +
    "     </object>" +
    "    </void>" +
    "    <void property=\"height\">" +
    "     <double>2.0</double>" +
    "    </void>" +
    "    <void property=\"stroke\">" +
    "     <object class=\"javafx.scene.paint.Color\">" +
    "      <double>1.0</double>" +
    "      <double>0.0</double>" +
    "      <double>0.0</double>" +
    "      <double>1.0</double>" +
    "     </object>" +
    "    </void>" +
    "    <void property=\"width\">" +
    "     <double>6.0</double>" +
    "    </void>" +
    "   </object>" +
    "  </void>" +
    " </array>" +
    "</java>",
    out3 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
          "<java version=\"1.8.0_341\" class=\"java.beans.XMLDecoder\">"+
          " <array class=\"javafx.scene.Node\" length=\"3\">"+
          "  <void index=\"0\">   <object class=\"javafx.scene.shape.Rectangle\">"+
          "    <void property=\"fill\">"+
          "     <object class=\"javafx.scene.paint.Color\">"+
          "      <double>1.0</double>"+
          "      <double>0.7529411911964417</double>"+
          "      <double>0.7960784435272217</double>"+
          "      <double>1.0</double>"+
          "     </object>"+
          "    </void>"+
          "    <void property=\"height\">"+
          "     <double>2.0</double>"+
          "    </void>"+
          "    <void property=\"stroke\">"+
          "     <object class=\"javafx.scene.paint.Color\">"+
          "      <double>1.0</double>"+
          "      <double>0.0</double>"+
          "      <double>0.0</double>"+
          "      <double>1.0</double>"+
          "     </object>"+
          "    </void>"+
          "    <void property=\"width\">"+
          "     <double>6.0</double>"+
          "    </void>"+
          "   </object>"+
          "  </void>"+
          "  <void index=\"1\">"+
          "   <object class=\"javafx.scene.shape.Line\">"+
          "    <void property=\"endX\">"+
          "     <double>4.0</double>"+
          "    </void>"+
          "    <void property=\"endY\">"+
          "     <double>4.0</double>"+
          "    </void>"+
          "   </object>"+
          "  </void>"+
          "  <void index=\"2\">"+
          "   <object class=\"javafx.scene.shape.Ellipse\">"+
          "    <void property=\"radiusX\">"+
          "     <double>4.0</double>"+
          "    </void>"+
          "    <void property=\"radiusY\">"+
          "     <double>2.0</double>"+
          "    </void>"+
          "   </object>"+
          "  </void>"+
          " </array></java>";
            
    private File file, file2, file3;
    
    public FileManagerTest() {
        
    }
    
    /**
     * The following folder and its content will be deleted after tests are run,
     * even if failures of exceptions occur.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    /**
     * This will be executed before each test, temporary files are created
     */
    @Before
    public void setUp(){
        paper = new Pane();
        fm = new FileManager(paper);
        try {
            file = folder.newFile("testfile1.xml");
            file2 = folder.newFile("testfile2.xml");
            file3 = folder.newFile("testfile3.xml");
        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of save method, of class FileManager.
     */
    @Test
    public void testSave() {
        try {
            System.out.println("save");
            fm.save(file);
            
            // Test 1: the pane has no shapes on it
            expectedXML = out1;
            
            Scanner scan = new Scanner(file);
            actualXML = "";
            while (scan.hasNext()) { actualXML += scan.nextLine();}
            scan.close();
            assertEquals(expectedXML, actualXML);
            
            // Test 2: the pane has a shape on it
            Rectangle rect = new Rectangle(0,0,6,2);
            rect.setFill(Color.PINK);
            rect.setStroke(Color.RED);
            paper.getChildren().add(rect);
            fm.save(file);
            
            expectedXML = out2;
            
            scan = new Scanner(file);
            actualXML = "";
            while (scan.hasNext()) { actualXML += scan.nextLine();}
            scan.close();
            assertEquals(expectedXML, actualXML);
            
            // Test 3: the pane has multiple different shapes on it
            Line line = new Line(0,0,4,4);
            Ellipse ellipse = new Ellipse(0, 0,4, 2);
            paper.getChildren().addAll(line, ellipse);
            fm.save(file);
            
            expectedXML = out3;
            
            scan = new Scanner(file);
            actualXML = "";
            while (scan.hasNext()) { actualXML += scan.nextLine();}
            scan.close();
            assertEquals(expectedXML, actualXML);
            
        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * Test of load method, of class FileManager.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        try {
            // Test 1: the loaded drawing has a pane with no children
            PrintWriter pw = new PrintWriter(file);
            pw.println(out1);
            pw.close();
            fm.load(file);
            assertEquals(0, paper.getChildren().toArray().length);
            
            // Test 2: the loaded drawing has a pane with just one child
            pw = new PrintWriter(file2);
            pw.println(out2);
            pw.close();
            fm.load(file2);
            assertEquals(1, paper.getChildren().toArray().length);
            
            // Test 3: the loaded drawing has a pane with many children
            pw = new PrintWriter(file3);
            pw.println(out3);
            pw.close();
            fm.load(file3);
            assertEquals(3, paper.getChildren().toArray().length);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
