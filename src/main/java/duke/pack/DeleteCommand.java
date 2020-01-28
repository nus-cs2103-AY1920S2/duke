package duke.pack;

public class DeleteCommand extends Command {
    protected int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(taskNum);
        storage.save(tasks);
    }

    public Boolean isExit() {
        return false;
    }

}
