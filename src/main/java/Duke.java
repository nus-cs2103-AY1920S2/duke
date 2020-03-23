import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.command.*;

import java.util.Scanner;
import java.io.IOException;

/** Duke Task Manager */
public class Duke {

    /**
     * Storage object to load and save tasks to text file.
     */
    private Storage storage;

    /**
     * Task list object to store tasks.
     */
    private TaskList taskList;

    /**
     * Constructs the Duke program.
     */
    protected Duke() {
        try {
            this.storage = new Storage("data/duke.txt");
            this.taskList = this.storage.loadTaskList();
        } catch (DukeException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        assert this.storage != null : "Storage is null!";
        assert this.taskList != null : "Task list is null!";
    }

    /**
     * Starts simulation without GUI.
     */
    protected void run() {
        Ui.printIntro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                Command command = Parser.parseUserInput(input, this.storage, this.taskList);
                command.execute(storage, taskList);
                if (command.isBye()) break;
            } catch (DukeException e) {
                System.err.println(e.toString());
                continue;
            } catch (IOException e) {
                System.err.println(e.toString());
                continue;
            }
        }
    }

    /**
     * Get Duke's response for GUI.
     */
    protected String getResponse(String input) {
        try {
            String message = Parser.parseUserInput(input, storage, taskList).execute(storage, taskList);
            return String.format(message + System.lineSeparator());
        } catch (DukeException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

}