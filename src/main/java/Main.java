import duke.Duke;
import duke.ui.cli.Cli;
import duke.ui.gui.Launcher;

/**
 * Executes a new Duke instance.
 */
public class Main {
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run(new Cli("Duke"));
        // new Duke("data/tasks.txt").run(new Launcher());
    }
}