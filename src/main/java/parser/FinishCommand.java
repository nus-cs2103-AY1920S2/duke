package parser;

import static parser.Parser.FINISH_PATTERN;

/**
 * Presents a command to mark a task from task list as done.
 */
public class FinishCommand extends Command {
    private Integer position;

    /**
     * Constructs a {@code FinishCommand} with a position indicating the task to mark.
     * @param userInput input from user.
     */
    FinishCommand(String userInput) {
        position = this.findIndex(FINISH_PATTERN, userInput);
    }

    /**
     * Mark a task at the inputted position as done.
     * @return response from the TaskList class as a string.
     */
    public String execute() {
        return this.taskList.markTaskAsDone(position);
    }
}
