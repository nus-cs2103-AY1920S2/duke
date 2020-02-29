package duke.ui.gui.parts;

import duke.parser.CommandExecutionExeption;
import duke.ui.UiLogic;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainWindow extends GuiComponent<Stage> {
    private static final String FXML = "dukemain.fxml";

    private Stage mainStage;
    private UiLogic logic;

    @FXML
    private AnchorPane mainLayout;
    @FXML
    private ScrollPane chatArea;

    public MainWindow(Stage stage, UiLogic logic) {
        super(FXML, stage);
        this.mainStage = stage;
        this.logic = logic;
        fillAreas();
    }

    public void show() {
        this.mainStage.show();
    }

    DdialogSection VBoxController;
    DcommandBox flowPaneController;

    void fillAreas() {
        VBoxController = new DdialogSection();
        VBoxController.setDimension(300.0);
        chatArea.setContent(VBoxController.getRoot());

        flowPaneController = new DcommandBox(this::feedCommandToLogic);
        mainLayout.getChildren().add(flowPaneController.getRoot());
    }

    private void feedCommandToLogic(String cmd) {
        try {
            this.logic.executeCommand(cmd);
        } catch (CommandExecutionExeption cmde) {

        }
        clearUserInput();
    }

    /**
     * Clears user input box
     */
    public void clearUserInput() {
        this.flowPaneController.textRegionController.clearText();
    }

    /**
     * Get input by user
     */
    public String nextLine() {
        return this.flowPaneController.textRegionController.getText();
    }

    public void addDialogLabel(String text) {
        this.VBoxController.addDialogLabel(text);
    }

}
