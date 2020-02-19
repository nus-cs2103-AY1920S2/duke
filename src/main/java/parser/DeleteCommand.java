package parser;

/**
 * Presents a command to delete a task from inner-task list.
 */
public class DeleteCommand extends Command {
    private Integer position;

    /**
     * Constructs a {@code DeleteCommand}.
     * @param position A valid position within the bound of the task list indicating the task to remove.
     */
    DeleteCommand(Integer position) {
        this.position = position;
    }

    /**
     * Remove a task from the task list by calling TaskList class.
     * @return response from the TaskList class as a string.
     */
    @Override
    public String execute() {
        assert this.taskList.size() > 0: "task list is empty, cannot delete";
        return this.taskList.remove(this.position);
    }
}
