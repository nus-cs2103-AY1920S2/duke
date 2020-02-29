package duke;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke object.
     */
    public Duke() {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises execution of Duke application.
     */
    public void run() {
        ui.welcome();

        while (true) {
            String entry = ui.input();
            if (entry.equals("bye")) {
                ui.bye();
                break;
            }
            tasks.run(entry);
        }

    }

    /**
     * Processes user input and returns a string containing response of Duke program.
     * @param input user input.
     * @return string to be printed onto chat box GUI.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            System.exit(0);
            return "See ya, chum.";
        } else {
            return tasks.run(input);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
