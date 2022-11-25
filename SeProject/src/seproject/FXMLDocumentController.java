package seproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable {

    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem loadMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private RadioButton selectButton;
    @FXML
    private Button ereaseButton;
    @FXML
    private RadioButton addLineButton;
    @FXML
    private RadioButton addRectangleButton;
    @FXML
    private RadioButton addEllipsesButton;
    @FXML
    private ColorPicker interiorColorPicker;
    @FXML
    private ColorPicker borderColorPicker;
    @FXML
    private Pane drawingPane;

    private Tool selectedTool;
    private FileManager fm;
    private SelectedShapeManager ssm;

    @FXML
    private ToolBar toolBar;
    @FXML
    private ToggleGroup g1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (Node child : toolBar.getItems()) {
            if (child instanceof RadioButton) {
                child.getStyleClass().remove("radio-button");
                child.getStyleClass().add("toggle-button");
            }
        }

        fm = new FileManager(drawingPane);
        /*Default color picker values*/
        interiorColorPicker.setValue(Color.BLACK);
        borderColorPicker.setValue(Color.BLACK);
        ssm = new SelectedShapeManager(drawingPane, borderColorPicker.valueProperty(), interiorColorPicker.valueProperty());
        ereaseButton.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());
        // selecting an initial tool
        selectedTool = ssm;
        selectButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue o, Boolean oldVal, Boolean newVal) {
                if (newVal != oldVal && newVal == false) {
                    ssm.unsetSelectedShape();
                }
            }
        });
    }

    @FXML
    private void saveDrawing(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File f = fc.showSaveDialog(drawingPane.getScene().getWindow());
        try {
            fm.save(f);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadDrawing(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Load");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File f = fc.showOpenDialog(drawingPane.getScene().getWindow());
        try {
            fm.load(f);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void selectShape(ActionEvent event) {
        selectedTool = ssm;
    }

    @FXML
    private void ereaseShape(ActionEvent event) {
        ssm.deleteSelectedShape();
    }

    @FXML
    private void addLine(ActionEvent event) {
        selectedTool = new LineTool(drawingPane, borderColorPicker.valueProperty(), interiorColorPicker.valueProperty());
    }

    @FXML
    private void addRectangle(ActionEvent event) {
        selectedTool = new RectangleTool(drawingPane, borderColorPicker.valueProperty(), interiorColorPicker.valueProperty());
    }

    @FXML
    private void addEllipses(ActionEvent event) {
        selectedTool = new EllipseTool(drawingPane, borderColorPicker.valueProperty(), interiorColorPicker.valueProperty());
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
