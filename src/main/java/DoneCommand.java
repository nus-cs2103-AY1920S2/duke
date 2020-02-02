public class DoneCommand extends Command {
    int taskNumber;
    DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean execute(Storage storage, TaskList taskList, Squirtle ui) throws DukeException {
        Task task = taskList.doneTask(this.taskNumber);
        storage.update(taskList.getTaskList());
        ui.doneMsg(task);
        return true;
    }
}
