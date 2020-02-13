import duke.Duke;
import duke.cli.Cli;
import duke.gui.Gui;

import javafx.application.Application;

/**
 * Executes a new Duke instance.
 */
public class Main {
    public static void main(String[] args) {

        Application.launch(Gui.class, args);

        // Uncomment the following line and comment the above line for CLI.
        // new Duke("data").run(new Cli("Duke"));
    }
}