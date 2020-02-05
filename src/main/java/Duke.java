import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

/**
 * Duke is a command line application that lets users store tasks they wish to track.
 * We have here our driver class which initialize other main components of the app.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * A constructor which takes instantiates other components.
     * @param filePath Path name of the file we want to load.
     */
    public Duke(String filePath) {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage(filePath);
        this.tasks = storage.load();
        this.parser = new Parser();
    }

    // feed into parser, have to change our parser to return strings to output.
    public String getResponse(String input) {
        return parser.handleTaskCommand(input, tasks);
    }

    public String getIntro() {
        return ui.getWelcomeMessage();
    }

    public String getExit() {
        return ui.getExitMessage();
    }
}
