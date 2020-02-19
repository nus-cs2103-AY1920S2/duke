package parser;

import static parser.Parser.DELETE_PATTERN;

/**
 * Presents a command to delete a task from inner-task list.
 */
public class DeleteCommand extends Command {
    private int position;

    /**
     * Constructs a {@code DeleteCommand}.
     * @param userInput input from user.
     */
    DeleteCommand(String userInput) {
        position = this.findIndex(DELETE_PATTERN, userInput);
    }

    /**
     * Remove a task from the task list by calling TaskList class.
     * @return response from the TaskList class as a string.
     */
    @Override
    public String execute() {
        return this.taskList.remove(this.position);
    }
}
