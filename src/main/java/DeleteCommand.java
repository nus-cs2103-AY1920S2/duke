public class DeleteCommand implements Command {
    int taskIndex;
    
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.removeTask(taskIndex - 1);
            ui.showRemoveTaskMessage(task, tasks.size());
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("Task number must be within the range of current tasks");
        }
    }
    
    public boolean isExit() {
        return false;
    }
}
