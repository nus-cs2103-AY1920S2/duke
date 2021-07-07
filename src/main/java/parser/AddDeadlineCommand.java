package parser;

import static parser.Parser.DEADLINE_PATTERN;

import exceptions.IllegalDateTimeFormatException;
import exceptions.NoDescriptionException;
import model.DeadLineTask;
import model.Task;

import java.time.LocalDateTime;

/**
 * Presents a command to add deadline task into inner-task list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    /**
     * Constructs an {@code AddDeadlineCommand}.
     * @param userInput input from user.
     * @throws IllegalDateTimeFormatException If the date time is empty or in invalid format.
     */
    AddDeadlineCommand(String userInput) throws IllegalDateTimeFormatException {
        description = this.findDescription(DEADLINE_PATTERN, userInput);
        by = this.findDateTime(DEADLINE_PATTERN, userInput);
    }

    /**
     * Create a deadline task with the inputted description and add it to inner-tasklist.
     * @return response from the TaskList class as a string.
     * @throws NoDescriptionException If the description is empty.
     */
    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new DeadLineTask(description, by);
        return this.taskList.add(taskToAdd);
    }
}
