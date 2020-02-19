package parser;

/**
 * Presents a command to mark a task from task list as done.
 */
public class FinishCommand extends Command {
    private Integer position;

    /**
     * Constructs a {@code FinishCommand} with a position indicating the task to mark.
     * @param position indicating the task to mark as done.
     */
    FinishCommand(Integer position) {
        this.position = position;
    }

    /**
     * Mark a task at the inputted position as done.
     * @return response from the TaskList class as a string.
     */
    public String execute() {

        assert this.taskList.size() > 0: "task list is empty, cannot mark any task";
        return this.taskList.markTaskAsDone(position);
    }
}
