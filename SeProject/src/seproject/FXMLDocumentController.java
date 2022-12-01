package seproject;

import seproject.tools.SelectedShapeManager;
import seproject.tools.Tool;
import seproject.tools.LineTool;
import seproject.tools.RectangleTool;
import seproject.tools.EllipseTool;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import seproject.commands.Invoker;
import javafx.util.converter.NumberStringConverter;
import seproject.tools.SelectionTool;

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
    private Pane drawingPane;

    private Tool selectedTool;
    private FileManager fm;

    @FXML
    private ToolBar toolBar;
    @FXML
    private ToggleGroup g1;
    @FXML
    private ColorPicker fillColorPicker;
    @FXML
    private ColorPicker strokeColorPicker;
    @FXML
    private ToolBar sideBar;
    @FXML
    private Button undoButton;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (Node child : toolBar.getItems()) {
            if (child instanceof RadioButton) {
                child.getStyleClass().remove("radio-button");
                child.getStyleClass().add("toggle-button");
            }
        }
        
        fm = new FileManager(drawingPane);
        SelectedShapeManager.setSelectedShapeManagerPaper(drawingPane);
        /*Default color picker values*/
        fillColorPicker.setValue(Color.BLACK);
        strokeColorPicker.setValue(Color.BLACK);
        ereaseButton.disableProperty().bind(SelectedShapeManager.getSelectedShapeManager().getShapeIsSelectedProperty().not());
        undoButton.disableProperty().bind(Invoker.getInvoker().getUndoIsEnabledProperty().not());
        // selecting an initial tool
        selectedTool = new SelectionTool(drawingPane);
        selectButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue o, Boolean oldVal, Boolean newVal) {
                if (!Objects.equals(newVal, oldVal) && newVal == false) {
                    SelectedShapeManager.getSelectedShapeManager().unsetSelectedShape();
                }
            }
        });
        
        sideBar.managedProperty().bind(SelectedShapeManager.getSelectedShapeManager().getShapeIsSelectedProperty());
        sideBar.visibleProperty().bind(SelectedShapeManager.getSelectedShapeManager().getShapeIsSelectedProperty());
        
        Bindings.bindBidirectional(widthTextField.textProperty(), SelectedShapeManager.getSelectedShapeManager().getWidthProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(heightTextField.textProperty(), SelectedShapeManager.getSelectedShapeManager().getHeightProperty(), new NumberStringConverter());
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
        selectedTool = new SelectionTool(drawingPane);
    }

    @FXML
    private void ereaseShape(ActionEvent event) {
        SelectedShapeManager.getSelectedShapeManager().deleteSelectedShape();
    }

    @FXML
    private void addLine(ActionEvent event) {
        selectedTool = new LineTool(drawingPane, strokeColorPicker.valueProperty(), fillColorPicker.valueProperty());
    }

    @FXML
    private void addRectangle(ActionEvent event) {
        selectedTool = new RectangleTool(drawingPane, strokeColorPicker.valueProperty(), fillColorPicker.valueProperty());
    }

    @FXML
    private void addEllipses(ActionEvent event) {
        selectedTool = new EllipseTool(drawingPane, strokeColorPicker.valueProperty(), fillColorPicker.valueProperty());
    }

    @FXML
    private void clickOnDrawingPane(MouseEvent event) {
        if (event.isPrimaryButtonDown()){
            selectedTool.onMousePressed(event);
        }
    }

    @FXML
    private void onMouseDraggedOnDrawingPane(MouseEvent event) {
        if (event.isPrimaryButtonDown()){
            selectedTool.onMouseDragged(event);
        }
    }
    
    @FXML
    private void onMouseReleasedOnDrawingPane(MouseEvent event) {
        selectedTool.onMouseReleased(event);
    }

    @FXML
    private void changeFillColor(ActionEvent event) {
        SelectedShapeManager.getSelectedShapeManager().changeSelectedShapeFillColor(fillColorPicker.getValue());  
    }
    
    @FXML
    private void changeStrokeColor(ActionEvent event) {
        SelectedShapeManager.getSelectedShapeManager().changeSelectedShapeStrokeColor(strokeColorPicker.getValue());
    }

    @FXML
    private void undo(ActionEvent event) {
        Invoker.getInvoker().undoLastCommand();
    }

    @FXML
    private void setNewWidth(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER){
            Shape s = SelectedShapeManager.getSelectedShapeManager().getSelectedShape();
            Double scaleFactor = Double.parseDouble(widthTextField.getText())/s.getLayoutBounds().getWidth();
            s.setScaleX(scaleFactor);
        }
    }

    @FXML
    private void setNewHeight(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER){
            Shape s = SelectedShapeManager.getSelectedShapeManager().getSelectedShape();
            Double scaleFactor = Double.parseDouble(heightTextField.getText())/s.getLayoutBounds().getHeight();
            s.setScaleY(scaleFactor);
        }
    }

}
