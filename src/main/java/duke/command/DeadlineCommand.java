package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.TaskFormatException;
import duke.exception.InvalidTimeFormatException;

/**
 * Represents a deadline command in Duke.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a fresh instance of a deadline command.
     * @param input The user input.
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Executes the Deadline command for the program to create a deadline.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws InvalidCommandException An exception telling ifthe command is invalid.
     * @throws EmptyDescriptionException An exception if the description provided is empty.
     * @throws TaskFormatException An exception if the task format is invalid.
     * @throws InvalidTimeFormatException An exception if the time format is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidCommandException, EmptyDescriptionException, 
            TaskFormatException, InvalidTimeFormatException {
        return tasks.manageDeadline(storage, input);
    }
}