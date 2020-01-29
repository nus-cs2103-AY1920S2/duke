package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.Serializer;
import duke.io.Ui;
import duke.task.TaskList;

public class Duke {

    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    public Duke() {
        ui = new Ui(System.in);
        tasks = new TaskList(Serializer.deserialize());
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.printWelcomeMessage();

        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui);
                shouldExit = command.isExitCommand();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }
}
