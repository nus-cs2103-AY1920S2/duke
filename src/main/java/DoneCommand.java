public class DoneCommand implements Command {
    int taskIndex;
    
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(taskIndex - 1);
            tasks.markTaskAsDone(taskIndex - 1);
            
            ui.showDoneTaskMessage(task);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("Task number must be within the range of current tasks");
        }
    }
    
    public boolean isExit() {
        return false;
    }
}
