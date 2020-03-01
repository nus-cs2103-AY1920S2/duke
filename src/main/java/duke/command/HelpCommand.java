package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.core.Message;
import duke.task.TaskList;

import duke.exception.InvalidCommandException;


public class HelpCommand extends Command {
    private String allCommands;

    public HelpCommand(String input, boolean isExit) {
        super(input, isExit);
        initialise();
    }

    private void initialise() {
        this.allCommands = "1. list\n" + "2. todo <task>\n" 
                + "3. event <task> /at <date>\n" + "4. deadline <task> /by <date>\n"
                + "5. done <index>\n" + "6. delete <index>\n" + "7. find <keyword>\n"
                + "8. update description <index> <new description>\n"
                + "9. update time <index> <new time>\n"
                + "10. bye";   
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (input.trim().compareTo("help") == 0) {
            return Message.HELP + this.allCommands;
        } else {
            throw new InvalidCommandException(Message.COMMAND_ERROR);
        }
    }
}