package seproject;

import com.sun.glass.ui.Screen;
import seproject.tools.SelectedShapeManager;
import seproject.tools.Tool;
import seproject.tools.LineTool;
import seproject.tools.RectangleTool;
import seproject.tools.EllipseTool;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    @FXML
    private ToolBar toolBar;
    @FXML
    private ToggleGroup g1;
    @FXML
    private ColorPicker fillColorPicker;
    @FXML
    private ColorPicker strokeColorPicker;
    @FXML
    private Button undoButton;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private ToolBar sideBar;
    @FXML
    private Label errorLabelSize;
    @FXML
    private Slider zoomSlider;

    private final static double MAX_SIZE = 10000;
    
    private final static double MIN_ZOOM = 1;
    
    private final static double MAX_ZOOM = 9;

    private ContextMenu contextMenu;

    private MenuItem copy;

    private MenuItem paste;

    private MenuItem cut;

    private MenuItem bringToFront;

    private MenuItem bringToBack;

    private Tool selectedTool;

    private FileManager fm;
    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DecimalFormat df = new DecimalFormat("##,####,####");
        df.setGroupingUsed(true);
        df.setDecimalSeparatorAlwaysShown(false);
        
        drawingPane.setMaxWidth(Screen.getMainScreen().getWidth());
        drawingPane.setMaxHeight(Screen.getMainScreen().getHeight());
                
        contextMenuInit();
        
        for (Node child : toolBar.getItems()) {
            if (child instanceof RadioButton) {
                child.getStyleClass().remove("radio-button");
                child.getStyleClass().add("toggle-button");
            }
        }

        fm = new FileManager(drawingPane);
        SelectedShapeManager.setSelectedShapeManagerPaper(drawingPane);

        /* Default color picker values */
        fillColorPicker.setValue(Color.BLACK);
        strokeColorPicker.setValue(Color.BLACK);
        ereaseButton.disableProperty().bind(SelectedShapeManager.getSelectedShapeManager().getShapeIsSelectedProperty().not());
        undoButton.disableProperty().bind(Invoker.getInvoker().getUndoIsEnabledProperty().not());

        /* Selecting an initial tool */
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

        /* Text fields' size input validation */
        UnaryOperator<Change> doubleFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^[0-9]*(\\.[0-9]*)?$")) {
                errorLabelSize.setManaged(false);
                errorLabelSize.setVisible(false);
                return change;
            }
            errorLabelSize.setManaged(true);
            errorLabelSize.setVisible(true);
            return null;
        };

        TextFormatter tfWidth = new TextFormatter(doubleFilter), tfHeight = new TextFormatter(doubleFilter);
        widthTextField.setTextFormatter(tfWidth);
        heightTextField.setTextFormatter(tfHeight);
        errorLabelSize.setManaged(false);
        errorLabelSize.setVisible(false);
        Bindings.bindBidirectional(widthTextField.textProperty(), SelectedShapeManager.getSelectedShapeManager().getWidthProperty(), new NumberStringConverter(df));
        Bindings.bindBidirectional(heightTextField.textProperty(), SelectedShapeManager.getSelectedShapeManager().getHeightProperty(), new NumberStringConverter(df));

        /* Zoom slider's settings */
        zoomSlider.setMin(MIN_ZOOM);
        zoomSlider.setMax(MAX_ZOOM);
        
        drawingPane.scaleXProperty().bind(zoomSlider.valueProperty());
        drawingPane.scaleYProperty().bind(zoomSlider.valueProperty());
        
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
        if (event.isPrimaryButtonDown()) {
            contextMenu.hide();
            selectedTool.onMousePressed(event);
        } else if (event.isSecondaryButtonDown()) {
            contextMenu.show(drawingPane, event.getScreenX(), event.getScreenY());
        }
    }

    @FXML
    private void onMouseDraggedOnDrawingPane(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            selectedTool.onMouseDragged(event);
        }
    }

    @FXML
    private void onMouseReleasedOnDrawingPane(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            selectedTool.onMouseReleased(event);
        }
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

    private void contextMenuInit() {

        this.contextMenu = new ContextMenu();
        this.copy = new MenuItem("Copy");
        this.cut = new MenuItem("Cut");
        this.paste = new MenuItem("Paste");
        this.bringToFront = new MenuItem("Bring to Front");
        this.bringToBack = new MenuItem("Bring to Back");
        contextMenu.getItems().addAll(copy, cut, paste, bringToFront, bringToBack);

        SelectedShapeManager ssm = SelectedShapeManager.getSelectedShapeManager();

        /* If nothing is selected no options will be avilable */
        copy.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());
        cut.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());
        bringToFront.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());
        bringToBack.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());

        /* If something has been copied the paste button will be unlocked */
        paste.disableProperty().bind(ssm.getShapeIsCopiedProperty().not());

        copy.setOnAction(e -> {
            ssm.copySelectedShape();
        });

        cut.setOnAction(e -> {
            ssm.cutShape();
        });

        paste.setOnAction(e -> {
            ssm.pasteShape();
        });

        bringToFront.setOnAction(e -> {
            ssm.bringToFrontShape();
        });

        bringToBack.setOnAction(e -> {
            ssm.bringToBackShape();
        });

    }

    @FXML
    private void setNewWidth(KeyEvent event) {
        String width = widthTextField.getText(), height = heightTextField.getText();
        if (event.getCode() == KeyCode.ENTER && validateSize(width) && validateSize(height)) {
            SelectedShapeManager.getSelectedShapeManager().resizeSelectedShape((Double.parseDouble(width)), Double.parseDouble(height));
        } else if (event.getCode() == KeyCode.ENTER) {
            errorLabelSize.setManaged(true);
            errorLabelSize.setVisible(true);
        }
    }

    @FXML
    private void setNewHeight(KeyEvent event) {
        String width = widthTextField.getText(), height = heightTextField.getText();
        if (event.getCode() == KeyCode.ENTER && validateSize(width) && validateSize(height)) {
            SelectedShapeManager.getSelectedShapeManager().resizeSelectedShape((Double.parseDouble(width)), Double.parseDouble(height));
        } else if (event.getCode() == KeyCode.ENTER) {
            errorLabelSize.setManaged(true);
            errorLabelSize.setVisible(true);
        }
    }


    /**
     * This method validates the width inserted by the user. It returns true if
     * it is valid, false otherwise.
     *
     * @param s
     * @return boolean
     */
    private boolean validateSize(String s) {
        double input = 0.0;
        try {
            input = Double.parseDouble(s);
            if (input > MAX_SIZE) {
                return false;
            }
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

}
