package duke;

import duke.DukeException;
import duke.command.Command;
import duke.common.Message;
import duke.task.TaskList;
import duke.cli.Cli;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke object given the save file path. 
     * The tasklist will obtain the saved task, if available.
     * @param filePath The path of the save file.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(Command command) throws DukeException {
        return command.execute(tasks, storage);
    }

    /**
     * Runs the Duke bot with an interface.
     * @param cli The command line interface.
     */
    public void run(Cli cli) {
        cli.showWelcome();
        cli.showMessage(Message.GREET);
        cli.newLine();

        boolean isExit = false;
        while (!isExit) {
            try {
                cli.showPrompt();
                String userCommand = cli.readCommand();
                cli.newLine();

                Command command = Parser.parse(userCommand);
                cli.showMessage(command.execute(tasks, storage));
                isExit = command.isExit();
            } catch (DukeException e) {
                cli.showMessage(e.getMessage());
            }
            cli.newLine();
        }

        cli.close();
    }
}