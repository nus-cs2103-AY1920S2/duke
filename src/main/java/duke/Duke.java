package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.Parser;
import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui("     Hello! I'm Duke\n     What can I do for you?",
        "     Bye. Hope to see you again soon!");

    public Duke() {
        String workingDir = System.getProperty("user.dir");
        Path savePath = Paths.get(workingDir, "data", "duke.txt");
        storage = new Storage(savePath);
    }

    public void runUntilExit() {
        //load the tasks from save file
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not read the save file.");
        }

        //print welcome message
        ui.printWelcomeMessage();

        //main loop
        boolean isRunning = true;
        while (isRunning) {
            try {
                //parse command
                Command command = Parser.parseCommand(ui.getUserInput());

                //check if it is exit command
                if (command instanceof ExitCommand) {
                    isRunning = false;
                }

                //execute command
                command.execute(tasks, ui, storage);
            } catch (InvalidCommandException e) {
                ui.printException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runUntilExit();
    }
}
