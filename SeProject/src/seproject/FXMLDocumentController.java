package seproject;

import seproject.customComponents.DrawingArea;
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
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import seproject.commands.Invoker;
import javafx.util.converter.NumberStringConverter;
import seproject.tools.PolygonTool;
import seproject.tools.SelectionTool;
import seproject.tools.TextTool;

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
    @FXML
    private ScrollPane scrollPane; 
    @FXML
    private RadioButton addTextButton;
    @FXML
    private RadioButton addPolygonButton;
    @FXML
    private Label errorLabelRotation;
    @FXML
    private TextField rotationTextField;
    @FXML
    private Button leftRotationButton;
    @FXML
    private Button rightRotationButton;
    @FXML
    private ToggleButton gridButton;
    @FXML
    private Spinner<Integer> gridSpinner;

    private final static double MAX_SIZE = 10000;

    private final static double MIN_ZOOM = 1;

    private final static double MAX_ZOOM = 9;

    private ContextMenu contextMenu;

    private MenuItem copy;

    private MenuItem paste;

    private MenuItem cut;

    private MenuItem bringToFront;

    private MenuItem bringToBack;

    private MenuItem deleteShape;

    private Tool selectedTool;

    private FileManager fm;
   
    private DrawingArea drawingPane;
    @FXML
    private Button mirrorVerticalButton;
    @FXML
    private Button mirrorHorizontalButton;
    private TextField streachingTextField;
    @FXML
    private Button verticalStretchingButton;
    @FXML
    private Button horizontalStretchingButton;
    @FXML
    private TextField stretchingTextField;
    @FXML
    private Label errorLabelStretching;
    @FXML
    private Spinner<?> textSpinner;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        /*Grid initialization*/
        
        gridSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1));
        
        gridButton.selectedProperty().setValue(false);
        
        //drawingPane = new DrawingArea(Screen.getMainScreen().getWidth(), Screen.getMainScreen().getHeight());
        drawingPane = new DrawingArea(1920,1080);
        initDrawingArea();
        
        /*
        * Note: this operation is needed because only if the object on witch the scale is performed is in a group the
        *        scrollbars of the scrollpane becames sensibile.
        */
        Group makeingDrawingPaneZoomSensitive = new Group(drawingPane);
       
        scrollPane.setContent(makeingDrawingPaneZoomSensitive);
        
        drawingPane.getContainerOfPaperAndGrid().scaleXProperty().bind(zoomSlider.valueProperty());
        drawingPane.getContainerOfPaperAndGrid().scaleYProperty().bind(zoomSlider.valueProperty());
        
        gridSpinner.getValueFactory().valueProperty().addListener(change->{
            drawingPane.redrawGrid(gridSpinner.getValue());
        });

        DecimalFormat df = new DecimalFormat("##,####,####");
        df.setGroupingUsed(true);
        df.setDecimalSeparatorAlwaysShown(false);

        contextMenuInit();

        for (Node child : toolBar.getItems()) {
            if (child instanceof RadioButton) {
                child.getStyleClass().remove("radio-button");
                child.getStyleClass().add("toggle-button");
            }
        }
        
        addTextButton.getStyleClass().remove("radio-button");
        addTextButton.getStyleClass().add("toggle-button");
        
        for (Node child : sideBar.getItems()) {
            if (child instanceof RadioButton){
                child.getStyleClass().remove("radio-button");
                child.getStyleClass().add("toggle-button");
            }
        }

        fm = new FileManager(drawingPane.getPaper());
        SelectedShapeManager.setSelectedShapeManagerPaper(drawingPane);

        /* Default color picker values */
        fillColorPicker.setValue(Color.WHITE);
        strokeColorPicker.setValue(Color.BLACK);
        ereaseButton.disableProperty().bind(SelectedShapeManager.getSelectedShapeManager().getShapeIsSelectedProperty().not());
        undoButton.disableProperty().bind(Invoker.getInvoker().getUndoIsEnabledProperty().not());

        /* Selecting an initial tool */
        selectedTool = new SelectionTool(drawingPane);

        for (Toggle r : g1.getToggles()) {
            r.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue == false) {
                        //SelectedShapeManager.getSelectedShapeManager().unsetSelectedShape();
                        selectedTool.deselect();
                    }
                }
            });
        }
        
        sideBar.managedProperty().bind(SelectedShapeManager.getSelectedShapeManager().getShapeIsSelectedProperty());
        sideBar.visibleProperty().bind(SelectedShapeManager.getSelectedShapeManager().getShapeIsSelectedProperty());

        /* Text fields' size input validation */
        

        TextFormatter tfWidth = new TextFormatter(this.controlTextField(errorLabelSize)), tfHeight = new TextFormatter(this.controlTextField(errorLabelSize)), 
                tfRotation = new TextFormatter(this.controlTextField(errorLabelRotation)), tfStretching = new TextFormatter(this.controlTextField(errorLabelStretching));
        widthTextField.setTextFormatter(tfWidth);
        heightTextField.setTextFormatter(tfHeight);
        rotationTextField.setTextFormatter(tfRotation);
        stretchingTextField.setTextFormatter(tfStretching);
        errorLabelSize.setManaged(false);
        errorLabelSize.setVisible(false);
        Bindings.bindBidirectional(widthTextField.textProperty(), SelectedShapeManager.getSelectedShapeManager().getWidthProperty(), new NumberStringConverter(df));
        Bindings.bindBidirectional(heightTextField.textProperty(), SelectedShapeManager.getSelectedShapeManager().getHeightProperty(), new NumberStringConverter(df));
        
        /* Zoom slider's settings */
        zoomSlider.setMin(MIN_ZOOM);
        zoomSlider.setMax(MAX_ZOOM);
        
}

    @FXML
    private void saveDrawing(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("bin files", "*.bin"));
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
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("bin files", "*.bin"));
        File f = fc.showOpenDialog(drawingPane.getScene().getWindow());
        try {
            fm.load(f);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void selectShape(ActionEvent event) {
        //selectedTool.selectedProperty.setValue(false);
        selectedTool = new SelectionTool(drawingPane);
    }

    @FXML
    private void ereaseShape(ActionEvent event) {
        SelectedShapeManager.getSelectedShapeManager().deleteSelectedShape();
        selectedTool.deselect();
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
    private void addText(ActionEvent event) {
        selectedTool = new TextTool(drawingPane, strokeColorPicker.valueProperty(), fillColorPicker.valueProperty());
    }

    @FXML
    private void addPolygon(ActionEvent event) {
        selectedTool = new PolygonTool(drawingPane, strokeColorPicker.valueProperty(), fillColorPicker.valueProperty());
    }

    private void clickOnDrawingPane(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            contextMenu.hide();
            selectedTool.onMousePressed(event);
        } else if (event.isSecondaryButtonDown()) {
            contextMenu.show(drawingPane, event.getScreenX(), event.getScreenY());
        }
    }

    private void onMouseDraggedOnDrawingPane(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            selectedTool.onMouseDragged(event);
        }
    }

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
        this.deleteShape = new MenuItem("Delete");
        contextMenu.getItems().addAll(copy, cut, paste, deleteShape, bringToFront, bringToBack);

        SelectedShapeManager ssm = SelectedShapeManager.getSelectedShapeManager();

        /* If nothing is selected no options will be avilable */
        copy.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());
        cut.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());
        bringToFront.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());
        bringToBack.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());
        deleteShape.disableProperty().bind(ssm.getShapeIsSelectedProperty().not());

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

        deleteShape.setOnAction(e -> {
            ssm.deleteSelectedShape();
        });

    }

    @FXML
    private void setNewWidth(KeyEvent event) {
        resizeSelectedShape(event);
    }

    @FXML
    private void setNewHeight(KeyEvent event) {
        resizeSelectedShape(event);
    }
    
    @FXML
    private void leftRotationAction(ActionEvent event) {
        if (!validateSize(rotationTextField.getText())){
            errorLabelRotation.setManaged(true);
            errorLabelRotation.setVisible(true);
        }else{
            errorLabelRotation.setVisible(false);
            errorLabelRotation.setManaged(false);
            double rotationShape = SelectedShapeManager.getSelectedShapeManager().getSelectedShape().getRotate();
            SelectedShapeManager.getSelectedShapeManager().rotationShape((-1*Double.parseDouble(rotationTextField.getText()))+ rotationShape);
        }
        return;
    }

    @FXML
    private void rightRotationAction(ActionEvent event) {
        if (!validateSize(rotationTextField.getText())){
            errorLabelRotation.setManaged(true);
            errorLabelRotation.setVisible(true);
        }else{
            errorLabelRotation.setVisible(false);
            errorLabelRotation.setManaged(false);
            double rotationShape = SelectedShapeManager.getSelectedShapeManager().getSelectedShape().getRotate();            
            SelectedShapeManager.getSelectedShapeManager().rotationShape(Double.parseDouble(rotationTextField.getText())+rotationShape);
        }
        return;
    }

    @FXML
    private void addGrid(ActionEvent event) {
       drawingPane.showGrid(gridButton.selectedProperty().getValue());
    }

    /**
     * This method is a utility method to resize the selected shape acoording to
     * the input inserted by the user.
     *
     * @param event
     */
    private void resizeSelectedShape(KeyEvent event) {
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

    private void initDrawingArea(){
        
        drawingPane.getPaper().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    contextMenu.hide();
                    selectedTool.onMousePressed(event);
                } else if (event.isSecondaryButtonDown()) {
                    contextMenu.show(drawingPane, event.getScreenX(), event.getScreenY());
                }
            }
        });
        
        drawingPane.getPaper().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    selectedTool.onMouseDragged(event);
                }
            }
        });
        
        drawingPane.getPaper().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    selectedTool.onMouseReleased(event);
                }
            }
        });
    }
    
    /**
     * This method allow to control the textField's text and label is 
     * the error's label.
     * @param label
     * @return 
     */
    private UnaryOperator<Change> controlTextField(Label label){
        UnaryOperator<Change> doubleFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^[0-9]*(\\.[0-9]*)?$")) {
                label.setManaged(false);
                label.setVisible(false);
                return change;
            }
            label.setManaged(true);
            label.setVisible(true);
            return null;
        };
        return doubleFilter;
    }
    
    /**
     * The method allow to shape's vertical mirroring
     * @param event 
     */
    @FXML
    private void mirrorVerticalAction(ActionEvent event) {
        SelectedShapeManager.getSelectedShapeManager().mirrorVerticalShape();
    }
    
    /**
     * The method allow to shape's horizontal mirroring
     * @param event 
     */
    @FXML
    private void mirrorHorizontalAction(ActionEvent event) {
        SelectedShapeManager.getSelectedShapeManager().mirrorHorizontalShape();
    }
    
    /**
     * The method allow to vertical stretching on selected shape due to textField's value
     * @param event 
     */
    @FXML
    private void verticalStretchingAction(ActionEvent event) {
        if (!validateSize(stretchingTextField.getText())){
            errorLabelStretching.setManaged(true);
            errorLabelStretching.setVisible(true);
        }else{
            errorLabelStretching.setVisible(false);
            errorLabelStretching.setManaged(false);
            SelectedShapeManager.getSelectedShapeManager().verticalStreachingShape((Double.parseDouble(stretchingTextField.getText())));
        }
        return;

    }

    /**
     * The method allow to horizontal stretching on selected shape due to textField's value
     * @param event 
     */
    @FXML
    private void horizontalStretchingAction(ActionEvent event) {
        if (!validateSize(stretchingTextField.getText())){
            errorLabelStretching.setManaged(true);
            errorLabelStretching.setVisible(true);
        }else{
            errorLabelStretching.setVisible(false);
            errorLabelStretching.setManaged(false);
            SelectedShapeManager.getSelectedShapeManager().horizontalStreachingShape((Double.parseDouble(stretchingTextField.getText())));
        }
        return;
    }
    
}
