package duke.command;

import duke.core.Message;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.KeywordNotFoundException;

/**
 * Represents a find keyword command.
 */
public class FindCommand extends Command {
    /**
     * Constructs a fresh instance of the find command.
     * @param input The user input.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes the Find command for the program to find a keyword.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws InvalidCommandException An exception telling ifthe command is invalid.
     * @throws KeywordNotFoundException An exception if the keyword format is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidCommandException, KeywordNotFoundException {
        String[] split = input.split(" ");
        if (split.length != 2) {
            throw new InvalidCommandException(Message.FIND_FORMAT_ERROR);
        }
        return tasks.findKeyword(input);
    }
}