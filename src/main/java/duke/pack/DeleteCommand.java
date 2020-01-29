package duke.pack;

public class DeleteCommand extends Command {
    protected int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum > tasks.getSize()) {
            throw new DukeException("    Oh no! That task does not exist!");
        }

        Task task = tasks.deleteTask(taskNum);
        storage.save(tasks);
        ui.showDelete(task);
        ui.showCount(tasks);
    }

    public Boolean isExit() {
        return false;
    }

}
