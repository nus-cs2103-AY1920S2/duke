import duke.Duke;
import duke.cli.Cli;
import duke.gui.Gui;

import javafx.application.Application;

/**
 * Executes a new Duke instance.
 */
public class Main {
    public static void main(String[] args) {
        // new Duke("data/tasks.txt").run(new Cli("Duke"));
        Application.launch(Gui.class, args);
    }
}