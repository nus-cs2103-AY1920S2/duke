package duke;

import duke.parser.Parser;
import duke.ui.Ui;
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

import duke.ui.Gui;

import java.net.URL;

/**
 * Main UI method.
 */
public class DukeMain extends Application {
    public Button sendButton;
    public ScrollPane scrollPane;
    public AnchorPane mainLayout;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;



    public static void main(String[] args) {
        Ui ui = new Ui(System.in);
        ui.respond(Ui.greetings);
        DukeHistory.progress(new Duke(ui));
        while (true) {
            feedCommandLine();
        }
    }

    private static void feedCommandLine() {
        Duke main = DukeHistory.getCurrent();
        Duke next = main.getCopy();
        if (main.ui.hasNextLine()) {
            String cmd = main.ui.nextLine();
            main.ui.clearUserInput();
            Parser.parse(cmd).execute(next);
        }
    }

    @FXML
    protected void handleSendButtonAction(MouseEvent event) {
        if(DukeHistory.empty()) {
            DukeHistory.progress(new Duke(new Gui(this.dialogContainer, this.userInput)));
        }
        feedCommandLine();
    }

    @FXML
    protected void handleUserInputOnAction(Event event) {
        if(DukeHistory.empty()) {
            DukeHistory.progress(new Duke(new Gui(this.dialogContainer, this.userInput)));
        }
        feedCommandLine();
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        URL url = DukeMain.class.getResource("/fxml/main.fxml");
        loader.setLocation(url);
        Parent root = loader.load();

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
