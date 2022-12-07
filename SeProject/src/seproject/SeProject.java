package seproject;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SeProject extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Definitely not Paint");
        stage.getIcons().add(new Image("icons/paint.png"));

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static ArrayList<Node> getAllNotShapeNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        System.out.println("root: " + root);
        nodes.add(root);
        addAllDescendents(root, nodes, 0);
        System.out.println("Called a function for " + nodes.size() + " iteractions.");
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes, int level) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            System.out.println(repeatString("-", level) + node);
            nodes.add(node);
        }
        for (Node node : parent.getChildrenUnmodifiable()) {
            if ((node instanceof Pane || node instanceof ToolBar ||node instanceof RadioButton)) {
                addAllDescendents((Parent) node, nodes, level + 1);
            }
        }
    }

    private static String repeatString(String string, int n) {
        if (string == null) {
            return null;
        }
        for (int i = 0; i < n; i++) {
            string += string;
        }
        return string;
    }

}
