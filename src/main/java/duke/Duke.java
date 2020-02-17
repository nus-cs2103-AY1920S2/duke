package duke;

import duke.cli.Cli;
import duke.common.command.Command;
import duke.common.command.ExitCommand;
import duke.common.message.ErrorMessage;
import duke.common.message.Message;
import duke.expense.ExpenseList;
import duke.expense.command.ExpenseCommand;
import duke.task.TaskList;
import duke.task.command.TaskCommand;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private ExpenseList expenses;

    /**
     * Creates a Duke object given the save file path. 
     * The tasklist will obtain the saved task, if available.
     * @param saveFolder The folder of the save file.
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
     * Gets greeting from duke.
     * @return The greeting message.
     */
    public String getGreeting() {
        return Message.GREET;
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
        
        throw new DukeException(ErrorMessage.getRandomCommandErrorMessage());
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