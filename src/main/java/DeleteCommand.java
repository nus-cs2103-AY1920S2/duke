public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(String command, int taskNumber) {
        super(command);
        this.taskNumber = taskNumber;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Remove the task from the task list in tasks
            Task deletedTask = tasks.deleteTask(taskNumber);
            // Update the file by saving it
            storage.saveFile(tasks.getTaskList());
            // Update user on the deletion of the task
            ui.printTaskDeleteSuccess(deletedTask, tasks.getTaskList().size());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
