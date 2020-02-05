package main.java.duke.gui.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.java.duke.Duke;
import main.java.duke.entity.command.Command;
import main.java.duke.gui.TaskModel;
import main.java.duke.handler.Storage;
import main.java.duke.handler.Ui;
import main.java.duke.parser.CommandParser;

public class TaskListOverviewController {


    @FXML
    private TableView<TaskModel> taskTable;
    @FXML
    private TableColumn<TaskModel, String> taskNameColumn;

    @FXML
    private TableColumn<TaskModel, String> infoColumn;

    @FXML
    private TextField commandField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextArea responseMessageTextArea;



    // Reference to the main application.
    private Duke duke;
    private Ui ui;
    private CommandParser commandParser;
    private Storage storage;
    private ObservableList<TaskModel> taskData;
    /**
     * The constructor. It is called before the initialize() method.
     */
    public TaskListOverviewController() {
        commandParser = new CommandParser();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
        infoColumn.setCellValueFactory(cellData -> cellData.getValue().addedInfoProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     */
    @FXML
    public void setMainApp(Duke duke) {
        this.duke = duke;
        taskData = duke.getTaskData();
        // Add observable list data to the table
        taskTable.setItems(taskData);
    }

    @FXML
    public void handleCommand() {
        String command = commandField.getText();
        Command c = commandParser.parse(command);
        String responseMessage = duke.execute(c, taskData);
        responseMessageTextArea.appendText(responseMessage);
        commandField.setText("");
    }


}