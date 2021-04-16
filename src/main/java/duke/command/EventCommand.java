package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTimeFormatException;
import duke.exception.TaskFormatException;

/**
 * Represents an event command.
 */
public class EventCommand extends Command {
    /**
     * Constructs a fresh instance of the event command.
     * @param input The user input.
     */
    public EventCommand(String input) {
        super(input);
    }

    /**
     * Executes the Event command for the program to create an Event.
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
            throws InvalidCommandException, EmptyDescriptionException, TaskFormatException, InvalidTimeFormatException {
        return tasks.manageEvent(storage, input);
    }
}