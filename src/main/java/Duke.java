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

    /**
     * Returns a response to MainWindow to display in dialogBox.
     * @param input String representing prompt.
     * @return String to display.
     */
    public String getResponse(String input) {
        return parser.handleTaskCommand(input, tasks);
    }

    /**
     * Returns a String representation of introduction message.
     * @return String representing introduction message.
     */
    public String getIntro() {
        return ui.getWelcomeMessage();
    }

    /**
     * Returns a String representation of exit message.
     * @return String representing exit message.
     */
    public String getExit() {
        return ui.getExitMessage();
    }

    /**
     * Saves our current tasks and inform user about it.
     * @return String saying instructions have been saved.
     */
    public String save() {
        storage.save(tasks);
        return "I have saved your instructions.\n";
    }
}
