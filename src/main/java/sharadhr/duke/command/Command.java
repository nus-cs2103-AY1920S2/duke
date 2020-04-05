package sharadhr.duke.command;

import sharadhr.duke.exception.DukeEmptyDetailException;
import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidDateTimeException;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * Models a possible user command to the Duke program.
 */
public abstract class Command {
    protected CommandName commandName;
    protected String[] argumentTokens;

    /**
     * Creates a {@link Command}, with a {@link String}[] that contains the tokenised arguments
     * to the command, from the user input.
     *
     * @param argumentTokens the tokenised input from the user minus the first token
     */
    public Command(String... argumentTokens) {
        this.argumentTokens = argumentTokens;
    }

    public static CommandName whichCommand(String token) {
        for (CommandName cmdname : CommandName.values()) {
            if (token.equalsIgnoreCase(cmdname.toString())) {
                return cmdname;
            } else if (token.isBlank()) {
                return CommandName.EMPTY;
            }
        }
        return CommandName.INVALID;
    }

    /**
     * Executes this command, by calling appropriate methods from the objects passed to this
     * {@link Command}.
     *
     * @param tasks   A {@link TaskList} to be operated upon
     * @param storage A {@link Storage} object for file I/O
     * @param output  An {@link Output} object to write to the standard output
     * @throws DukeEmptyDetailException     If the detail in this command is empty
     * @throws DukeInvalidDateTimeException If the date in this command cannot be parsed
     * @throws DukeInvalidArgumentException
     */
    public abstract void execute(TaskList tasks, Storage storage, Output output)
        throws DukeEmptyDetailException, DukeInvalidDateTimeException, DukeInvalidArgumentException;

    /**
     * @return
     */
    public boolean willTerminate() {
        return false;
    }

    public enum CommandName {
        TODO, DEADLINE, EVENT, LIST, DONE, FIND, DELETE, FROM, BY, AT, ON, EMPTY, INVALID, BYE
    }
}
