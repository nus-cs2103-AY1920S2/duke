package parser;

import static parser.Parser.EVENT_PATTERN;

import exceptions.IllegalDateTimeFormatException;
import exceptions.NoDescriptionException;
import model.EventTask;
import model.Task;

import java.time.LocalDateTime;

/**
 * Presents a command to add event task into inner-task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime at;

    /**
     *
     * @param description A valid description for a event task.
     */

    /**
     * Constructs an {@code AddEventCommand}.
     * @param userInput input from user.
     * @throws IllegalDateTimeFormatException If the user input is in invalid format.
     */
    AddEventCommand(String userInput) throws IllegalDateTimeFormatException {
        description = this.findDescription(EVENT_PATTERN, userInput);
        at = this.findDateTime(EVENT_PATTERN, userInput);
    }

    /**
     * Create a event task with the inputted description and add it to inner-tasklist.
     * @return response from the TaskList class as a string.
     * @throws NoDescriptionException If the description is empty.
     */
    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new EventTask(description, at);
        return this.taskList.add(taskToAdd);
    }
}
