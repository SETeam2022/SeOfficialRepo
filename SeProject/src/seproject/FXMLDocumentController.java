package seproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem loadMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private Button selectButton;
    @FXML
    private Button ereaseButton;
    @FXML
    private Button addLineButton;
    @FXML
    private Button addRectangleButton;
    @FXML
    private Button addEllipsesButton;
    @FXML
    private ColorPicker interiorColorPicker;
    @FXML
    private ColorPicker borderColorPicker;
    @FXML
    private Pane drawingPane;
    
    private Tool selectedTool;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            //
    }    

    @FXML
    private void saveDrawing(ActionEvent event) {
    }

    @FXML
    private void loadDrawing(ActionEvent event) {
    }

    @FXML
    private void closeApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void selectShape(ActionEvent event) {
        
    }

    @FXML
    private void ereaseShape(ActionEvent event) {
    }

    @FXML
    private void addLine(ActionEvent event) {
        selectedTool = new LineTool(drawingPane);
    }

    @FXML
    private void addRectangle(ActionEvent event) {
        selectedTool = new RectangleTool(drawingPane);
    }

    @FXML
    private void addEllipses(ActionEvent event) {
        selectedTool = new EllipseTool(drawingPane);
    }

    @FXML
    private void pickInteriorColor(ActionEvent event) {
       selectedTool.setFillColor(interiorColorPicker.getValue());
    }

    @FXML
    private void pickBorderColor(ActionEvent event) {
        selectedTool.setStrokeColor(borderColorPicker.getValue());
    }

    @FXML
    private void clickOnDrawingPane(MouseEvent event) {
        selectedTool.onMousePressed(event);
    }

    @FXML
    private void onMouseDraggedOnDrawingPane(MouseEvent event) {
        selectedTool.onMouseDragged(event);
    }
    
}
