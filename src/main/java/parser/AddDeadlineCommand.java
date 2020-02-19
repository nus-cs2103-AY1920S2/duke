package parser;

import exceptions.NoDescriptionException;
import model.DeadLineTask;
import model.Task;

import java.time.LocalDateTime;

/**
 * Presents a command to add deadline task into inner-task list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime at;

    /**
     * Constructs an {@code AddDeadlineCommand}.
     * @param description A valid description for a event task.
     */
    AddDeadlineCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Create a deadline task with the inputted description and add it to inner-tasklist.
     * @return response from the TaskList class as a string.
     * @throws NoDescriptionException If the description is empty.
     */
    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new DeadLineTask(description, at);
        String commandResult = this.taskList.add(taskToAdd);

        assert this.taskList.size() > 0: "task not added";
        return commandResult;
    }
}
