public class MarkDoneCommand extends Command {
    private int taskNumber;

    public MarkDoneCommand(String command, int taskNumber) {
        super(command);
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Mark the task as done
            Task taskDone = tasks.markTaskDone(taskNumber);
            // Update the file by saving it
            storage.saveFile(tasks.getTaskList());
            // Update the user
            ui.printMarkDoneSuccess(taskDone);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
