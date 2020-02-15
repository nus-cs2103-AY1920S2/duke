package duke;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import duke.parser.Parser;
import duke.parser.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UiText;
import duke.ui.Ui;
import duke.ui.Gui;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * Main UI method.
 */
public class Duke extends Application {
    public Button sendButton;
    public ScrollPane scrollPane;
    public AnchorPane mainLayout;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    public Storage storage;
    public TaskList tasks;
    public UiText ui;

    /**
     * If no file path specified, default path is assumed
     */
    public Duke() {
        this(true);
    }

    private Duke(boolean gui) {
        this(Storage.DEFAULT_PATH, gui);
    }

    /**
     * constructor to specify file path of the last saved data
     *
     * @param filePath = path of last saved data file
     * @param gui      = using gui?
     */
    public Duke(String filePath, boolean gui) {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage(filePath);
        if (this.storage.fileExist()) {
            try {
                this.tasks = TaskList.fromCSVList(storage.loadCSVList());
            } catch (Exception e) {
                this.ui.respond(Ui.loadingErrorMsg);
                tasks = new TaskList();
            }
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Main method.
     */
    public void run() {
        ui.respond(Ui.greetings);
        Command cmd;
        while (ui.hasNextLine()) {
            cmd = Parser.parse(ui.nextLine());
            cmd.execute(this.tasks, this.ui, this.storage);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            new Duke(args[0], false).run();
        } else {
            new Duke(false).run();
        }
    }

    @FXML
    protected void handleSendButtonAction(MouseEvent event) {
        try {
            this.ui = new Gui(this.dialogContainer, this.userInput);
            if(this.ui.hasNextLine()) {
                Command cmd = Parser.parse(this.ui.nextLine());
                cmd.execute(this.tasks, this.ui, this.storage);
                this.ui.clearUserInput();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleUserInputOnAction(Event event) {
        this.ui = new Gui(this.dialogContainer, this.userInput);
        if(this.ui.hasNextLine()) {
            Command cmd = Parser.parse(this.ui.nextLine());
            cmd.execute(this.tasks, this.ui, this.storage);
            this.ui.clearUserInput();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/java/duke/fxml/main.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
     }
}
