package duke;

import duke.DukeException;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.expense.ExpenseCommand;
import duke.command.task.TaskCommand;
import duke.common.ErrorMessage;
import duke.common.Message;
import duke.expense.ExpenseList;
import duke.task.TaskList;
import duke.cli.Cli;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private ExpenseList expenses;

    /**
     * Creates a Duke object given the save file path. 
     * The tasklist will obtain the saved task, if available.
     * @param filePath The path of the save file.
     */
    public Duke(String saveFolder) {
        this.storage = new Storage(saveFolder);

        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            tasks = new TaskList();
        }

        try {
            expenses = new ExpenseList(storage.loadExpense());
        } catch (DukeException e) {
            expenses = new ExpenseList();
        }
    }

    /**
     * Returns the response to the command input by the user.
     * @param command The user command.
     * @return The response to the command.
     * @throws DukeException Error in command or command execution.
     */
    public String getResponse(Command command) throws DukeException {
        if (command instanceof TaskCommand) {
            return ((TaskCommand) command).execute(tasks, storage);
        } else if (command instanceof ExpenseCommand) {
            return ((ExpenseCommand) command).execute(expenses, storage);
        } else if (command instanceof ExitCommand) {
            return ((ExitCommand) command).execute();
        }
        
        throw new DukeException(ErrorMessage.COMMAND_NOT_FOUND);
    }

    /**
     * Runs the Duke bot using command line interface.
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
                cli.showMessage(getResponse(command));
                isExit = command.isExit();
            } catch (DukeException e) {
                cli.showMessage(e.getMessage());
            }
            cli.newLine();
        }

        cli.close();
    }
}