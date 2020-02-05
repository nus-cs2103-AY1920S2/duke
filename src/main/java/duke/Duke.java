package main.java.duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.java.duke.entity.TaskList;
import main.java.duke.entity.command.Command;
import main.java.duke.entity.task.Task;
import main.java.duke.gui.TaskModel;
import main.java.duke.gui.view.TaskListOverviewController;
import main.java.duke.gui.view.UiController;
import main.java.duke.handler.Ui;

import main.java.duke.exception.DirectoryNotFoundException;
import main.java.duke.handler.Storage;
import main.java.duke.parser.CommandParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private UiController uiController;
    private CommandParser commandParser;

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<TaskModel> taskData = FXCollections.observableArrayList();

    public ObservableList<TaskModel> getTaskData() {
        return taskData;
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("TaskListApp");

        initRootLayout();

        showTaskListOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Duke.class.getResource("gui/view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showTaskListOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Duke.class.getResource("gui/view/TaskListOverview.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            TaskListOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }



    public static void main(String[] args) {
        launch(args);
        //new Duke().run();

    }

    public Duke() {


        ui = new Ui();
        uiController = new UiController();
        commandParser = new CommandParser();
        Path path = Paths.get("C:", "Users", "ErnTek", "Desktop");
        try {
            storage = new Storage(path, "TaskList.txt");
            tasks = new TaskList(storage.loadTaskFromMemory());
            List<Task> taskList = tasks.getTasks();
            for (int i = 0; i < taskList.size(); i++) {
                taskData.add(new TaskModel(taskList.get(i)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found!");
            tasks = new TaskList();
        } catch (DirectoryNotFoundException e) {
            System.out.println("Directory not found! No saved tasks found!");
            tasks = new TaskList();
        }
    }



    public void run() {

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = commandParser.parse(fullCommand);
            try {
                c.execute(tasks, ui, storage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            isExit = c.isExit();
            ui.showLine();
        }


    }

    public String execute(Command command, ObservableList<TaskModel> taskData) {

        return uiController.showLine() + command.execute(tasks, ui, storage, taskData, uiController) + uiController.showLine();
    }


}